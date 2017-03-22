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
		//ʹ��Properties�������������ļ�
		Properties properties = new Properties();
		properties.load(new FileInputStream(paramFile));
		driver = properties.getProperty("driver");
		url = properties.getProperty("url");
		user = properties.getProperty("user");
		pass = properties.getProperty("pass");
	}
	//����
	public void createTable(String sql) throws Exception
	{
		//��������
		Class.forName(driver);
		//ʹ��try�������Զ��ر�Statement�ȶ���
		try(
				//��ȡ���ݿ�����
				Connection connection = DriverManager.getConnection(url,user,pass);
				//����Statement����
				Statement statement = connection.createStatement()
			)
		{
			statement.executeUpdate(sql);
		}
	}
	
	//��������
	public void insertTable(String sql) throws Exception
	{
		Class.forName(driver);
		try(
				Connection connection1 = DriverManager.getConnection(url,user,pass);
				Statement statement1 = connection1.createStatement();
			)
		{
			statement1.execute(sql);
			System.out.println("��Ӱ�������У�" + statement1.getUpdateCount() + "��");
		}
	}
	
	//��ѯ����
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
		//ע����д��ʽ
		eDdl.insertTable("insert into table3 values(" + "'����','13178848059'),(" + "'����','13556469965');" );
		eDdl.selectTable("select *" + "from table3;");
	}
}
