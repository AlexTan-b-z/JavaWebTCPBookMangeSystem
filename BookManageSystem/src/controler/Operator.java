package controler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jdbc.Database;
import model.Book;

public class Operator {
	
	public static  int clearKey = 12345678;
	
	public String findFilename(String id){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String filename = null;
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select filename from book where id=" + id);
			while(rs.next()){
				filename = rs.getString("filename");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Database.close(conn, stmt, rs);
		}
		return filename;
	}
	
	public String findUsername(String username){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String password = null;
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select password from user where username='"+username+"'");
			while(rs.next()){
				password = rs.getString("password");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Database.close(conn, stmt, rs);
		}
		
		return password;
	}
	
	public boolean addUser(String username, String password){
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			String sql = "insert into user(username,password) values('"+username+"','"+password+"')";
			//System.out.println(sql);
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			Database.close(conn, stmt);
		}
	}
	
	public ArrayList<Book> getBookList()
	{
		ArrayList<Book> booklist = new ArrayList<Book>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		//3.通过数据库的连接操作数据库，实现增删改查
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select id,bookname,author,price from book");//选择import java.sql.ResultSet;
			while(rs.next()){//如果对象中有数据，就会循环打印出来
				String bookname = rs.getString("bookname");
				String author = rs.getString("author");
				float price = rs.getFloat("price");
				int ID = rs.getInt("id");
				Book book = new Book(ID,bookname,author,price);
				booklist.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Database.close(conn, stmt, rs);
		}
		return booklist;
	}
	
	public boolean addBook(String bookname,String author,float price,String fileName)
	{
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			String sql = "insert into book(bookname,author,price,filename) values('"+bookname+"','"+author+"',"+price+",'"+fileName+"')";
			//System.out.println(sql);
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			Database.close(conn, stmt);
		}
	}
	
	public boolean deleteBook(int id,String bookname)
	{
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			String sql;
			if(id != -1)
			{
				sql = "delete from book where id ="+id;
			}
			else
			{
				sql = "delete from book where bookname ='"+bookname+"'";
			}
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			Database.close(conn, stmt);
		}
	}
	
	public boolean changeBoo(int id,String bookname,String changename)
	{
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			String sql;
			if (id != -1)
			{
				sql = "update book set bookname='"+changename+"'"+" where id="+id;
				//System.out.println(sql);
			}
			else
			{
				sql = "update book set bookname='"+changename+"'"+" where bookname='"+bookname+"'";
			}
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			Database.close(conn,stmt);
		}
	}
	
	public ArrayList<Book> findBoo(int id,String bookname,String author,String dimname,float minprice,float maxprice)
	{
		ArrayList<Book> booklist = new ArrayList<Book>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			String sql;
			if (id != -1)
			{
				sql = "select id,bookname,author,price from book"+" where id="+id;
				//System.out.println(sql);
			}
			else if(bookname != null)
			{
				sql = "select id,bookname,author,price from book"+" where bookname='"+bookname+"'";
			}
			else if(author != null)
			{
				sql = "select id,bookname,author,price from book"+" where author='"+author+"'";
			}
			else if(dimname != null)
			{
				sql = "select id,bookname,author,price from book"+" where bookname like'%"+dimname+"%'";
			}
			else if(maxprice != 0)
			{
				sql = "select id,bookname,author,price from book where price>="+minprice+" and price<="+maxprice;
			}
			else
			{
				System.out.println("出现未知错误，请联系管理员！");
				sql="";
			}
			rs = stmt.executeQuery(sql);
			while(rs.next()){//如果对象中有数据，就会循环打印出来
				String bookName = rs.getString("bookname");
				String Author = rs.getString("author");
				float Price = rs.getFloat("price");
				int ID = rs.getInt("id");
				Book book = new Book(ID,bookName,Author,Price);
				booklist.add(book);
			}
			/*
			if(rs.next())
			{
				System.out.println("查找成功！您查找的结果为：\n");
				do{//如果对象中有数据，就会循环打印出来
					System.out.println("编号："+rs.getInt("id")+" 书名："+rs.getString("bookname")+",作者："+rs.getString("author")+",价格："+rs.getFloat("price"));
				}while(rs.next());
			}
			else
				System.out.println("未查找到您想要的图书！");
			*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Database.close(conn, stmt,rs);
		}
		return booklist;
	}
	
	public ArrayList<Book> printAllbook()
	{
		ArrayList<Book> booklist = new ArrayList<Book>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		//3.通过数据库的连接操作数据库，实现增删改查
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select id,bookname,author,price from book");//选择import java.sql.ResultSet;
			while(rs.next()){//如果对象中有数据，就会循环打印出来
				String bookName = rs.getString("bookname");
				String Author = rs.getString("author");
				float Price = rs.getFloat("price");
				int ID = rs.getInt("id");
				Book book = new Book(ID,bookName,Author,Price);
				booklist.add(book);
			}
			/*
			if(rs.next())
			{
				do{//如果对象中有数据，就会循环打印出来
					System.out.println("编号："+rs.getInt("id")+" 书名："+rs.getString("bookname")+",作者："+rs.getString("author")+",价格："+rs.getFloat("price"));
				}while(rs.next());
			}
			else
			{
				System.out.println("图书库为空，请添加图书！");
			}
			*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Database.close(conn, stmt, rs);
		}
		return booklist;
	}
	
	public boolean clearBook()
	{
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			String sql = "truncate table book";
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			Database.close(conn, stmt);
		}
	}
}