import java.sql.Statement;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;

public class ExecuteDDL {
	private String driver;
	private String url;
	private String user;
	private String pass;
	
	public void initParam(String paramFile) throws Exception
	{
		//使用Properties类来加载属性文件
		Properties properties = new Properties();
		properties.load(new FileInputStream(paramFile));
		driver = properties.getProperty("driver");
		url = properties.getProperty("url");
		user = properties.getProperty("user");
		pass = properties.getProperty("pass");
	}
	//建表
	public void createTable(String sql) throws Exception
	{
		//加载驱动
		Class.forName(driver);
		//使用try语句可以自动关闭Statement等对象
		try(
				//获取数据库连接
				Connection connection = DriverManager.getConnection(url,user,pass);
				//创建Statement对象
				Statement statement = connection.createStatement()
			)
		{
			statement.executeUpdate(sql);
		}
	}
	
	//加入数据
	public void insertTable(String sql) throws Exception
	{
		Class.forName(driver);
		try(
				Connection connection1 = DriverManager.getConnection(url,user,pass);
				Statement statement1 = connection1.createStatement();
			)
		{
			statement1.execute(sql);
			System.out.println("受影响行数有：" + statement1.getUpdateCount() + "条");
		}
	}
	
	//查询数据
	public void selectTable(String sql) throws Exception
	{
		Class.forName(driver);
		try(
				Connection connection2 = DriverManager.getConnection(url,user,pass);
				Statement statement2 = connection2.createStatement();
				ResultSet resultSet = statement2.executeQuery(sql);
				)
		{
			while(resultSet.next())
			{
				System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2));
			}
		}
	}
	public static void main(String[] args) throws Exception
	{
		ExecuteDDL eDdl = new ExecuteDDL();
		eDdl.initParam("mysql.ini");
		eDdl.createTable("create table table3" + "( name varchar(20) primary key," + "PhoneNumber varchar(20) );");
		//注意书写格式
		eDdl.insertTable("insert into table3 values(" + "'张三','13178848059'),(" + "'李四','13556469965');" );
		eDdl.selectTable("select *" + "from table3;");
	}
}
