package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private static final String URL="jdbc:mysql://localhost:3306/books";
	private static final String NAME="root";
	private static final String PASSWORD="root";
	private static Connection conn=null;//��̬����飨�������������������ݿ���뾲̬���У�
	static{
		try {
				//1.������������
				Class.forName("com.mysql.jdbc.Driver");
				//2.������ݿ������
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
	
	//�����ṩһ����������ȡ���ݿ�����
	public static Connection getConnection(){
		try {
			conn = DriverManager.getConnection(URL, NAME, PASSWORD);
			//System.out.println("���ݿ����ӳɹ���");
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//�Ͽ����ݿ�����
	public static void close(Connection con, Statement stat, ResultSet rs)
	{
			try {
				if (rs != null)
					rs.close();
				if (stat != null)
					stat.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	//����close������������û��ResultSet�����
	public static void close(Connection con, Statement stat)
	{
		close(con,stat,null);
	}
}
