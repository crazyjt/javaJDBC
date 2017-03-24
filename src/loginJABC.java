import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class loginJABC {
	private JFrame jFrame = new JFrame("Login");
	private JTextField user = new JTextField();
	private JTextField password = new JTextField();
	private JButton login = new JButton("Login");


	
	private String driver;
	private String url;
	private String USE;
	private String PASS;
	private boolean isFind = false;
	
	public void initParam(String sql) throws Exception
	{
		Properties properties = new Properties();
		properties.load(new FileInputStream(sql));
		driver = properties.getProperty("driver");
		url = properties.getProperty("url");
		USE = properties.getProperty("user");
		PASS = properties.getProperty("pass");
	}
	
	public void config(String sql) throws Exception
	{
		
		initParam(sql);
		Class.forName(driver);
		try(
				Connection connection = DriverManager.getConnection(url,USE,PASS);
				PreparedStatement preparedStatement = connection.prepareStatement("select * from table2 where user=? and password=?")
				)
		{
			preparedStatement.setString(1, user.getText());
			preparedStatement.setString(2, password.getText());
			try(
					ResultSet rs = preparedStatement.executeQuery();
					)
			
					{
						if(rs.next())
							isFind = true;
						else
							isFind = false;
					}
			  
			catch (Exception e) {
				e.printStackTrace();
			}
			
			
			if(isFind)
			{
				//弹出消息窗口
				JOptionPane.showMessageDialog(jFrame, "登录成功！");
			}
			else
			{
				JOptionPane.showMessageDialog(jFrame, "登录失败！");
			}
		}
		
	}
	public void init(String sql) throws Exception
	{

		
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
						try {
							config(sql);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
			}
		});
		jFrame.add(user,BorderLayout.NORTH);
		jFrame.add(password);
		jFrame.add(login,BorderLayout.SOUTH);
		jFrame.pack();
		jFrame.setVisible(true);
		
	}
	public static void main(String[] args) throws Exception
	{
		loginJABC lg = new loginJABC();
		lg.init("mysql.ini");
	}
}
