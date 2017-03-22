import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class ConnMySql {

	public static void main(String[] args) throws Exception {
		//1.加载驱动
		Class.forName("com.mysql.jdbc.Driver");
		try(
			//2.使用DriverManager获取数据库链接
			//其中返回的Connection就代表了Java程序和数据库的连接
			//不同数据库URL写法不同
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testJava","root","CHEN456321");
			//3.利用Connection来创建一个Statement对象
			 Statement statement = connection.createStatement();
			//4.利用Statement的executeQuery方法查询，并返回一个结果集
			 ResultSet rSet =  statement.executeQuery("select *" + "from table1");

		)	
		    {
				 //ResultSet的next方法移动指针指向有效行，并用getXXX()方法读出数据
				while(rSet.next())
				{
					System.out.println(rSet.getString(1) + "\t" + rSet.getString(2));
				}
			 }
	}

}
