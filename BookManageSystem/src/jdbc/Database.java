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
	private static Connection conn=null;//静态代码块（将加载驱动、连接数据库放入静态块中）
	static{
		try {
				//1.加载驱动程序
				Class.forName("com.mysql.jdbc.Driver");
				//2.获得数据库的连接
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
	
	//对外提供一个方法来获取数据库连接
	public static Connection getConnection(){
		try {
			conn = DriverManager.getConnection(URL, NAME, PASSWORD);
			//System.out.println("数据库连接成功！");
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//断开数据库连接
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
	//重载close函数，适用于没有ResultSet的情况
	public static void close(Connection con, Statement stat)
	{
		close(con,stat,null);
	}
}
