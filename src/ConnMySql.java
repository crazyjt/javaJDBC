import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class ConnMySql {

	public static void main(String[] args) throws Exception {
		//1.��������
		Class.forName("com.mysql.jdbc.Driver");
		try(
			//2.ʹ��DriverManager��ȡ���ݿ�����
			//���з��ص�Connection�ʹ�����Java��������ݿ������
			//��ͬ���ݿ�URLд����ͬ
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testJava","root","CHEN456321");
			//3.����Connection������һ��Statement����
			 Statement statement = connection.createStatement();
			//4.����Statement��executeQuery������ѯ��������һ�������
			 ResultSet rSet =  statement.executeQuery("select *" + "from table1");

		)	
		    {
				 //ResultSet��next�����ƶ�ָ��ָ����Ч�У�����getXXX()������������
				while(rSet.next())
				{
					System.out.println(rSet.getString(1) + "\t" + rSet.getString(2));
				}
			 }
	}

}
