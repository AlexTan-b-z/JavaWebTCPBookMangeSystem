package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controler.Operator;
import model.Book;

/**
 * Servlet implementation class findbookServlet
 */
@WebServlet("/findbookServlet")
public class findbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public findbookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username = (String)request.getSession().getAttribute("username");
		if(username != null){
			Operator operator = new Operator();
			ArrayList<Book> booklist = operator.printAllbook();
			request.setAttribute("booklist", booklist);
    		request.getRequestDispatcher("/findbook.jsp").forward(request, response);
    		return;
		}else{
			request.setAttribute("msg", "您还未登陆页面，请返回登陆后再进行操作");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("utf-8");  
        response.setContentType("text/html;charset=utf-8");
        String username = (String)request.getSession().getAttribute("username");
        if(username != null){
        	Operator operator = new Operator();
        	String name = null;
			int id = -1;
			String author = null;
			String dimname = null;
			float minprice = 0;
			float maxprice = -1;
        	String choice = request.getParameter("choice");
        	if(choice.equals("id")){
        		String idStr = request.getParameter("id");
        		id = Integer.parseInt(idStr);
        		ArrayList<Book> booklist = operator.findBoo(id,name,author,dimname,minprice,maxprice);
        		request.setAttribute("booklist", booklist);
        		request.getRequestDispatcher("/findbook.jsp").forward(request, response);
        		return;
        	}else if(choice.equals("bookname")){
        		dimname = request.getParameter("bookname");
        		ArrayList<Book> booklist = operator.findBoo(id,name,author,dimname,minprice,maxprice);
        		request.setAttribute("booklist", booklist);
        		request.getRequestDispatcher("/findbook.jsp").forward(request, response);
        		return;
        	}else if(choice.equals("author")){
        		author = request.getParameter("author");
        		ArrayList<Book> booklist = operator.findBoo(id,name,author,dimname,minprice,maxprice);
        		request.setAttribute("booklist", booklist);
        		request.getRequestDispatcher("/findbook.jsp").forward(request, response);
        		return;
        	}else if(choice.equals("price")){
        		minprice = Float.parseFloat(request.getParameter("minprice"));
        		maxprice = Float.parseFloat(request.getParameter("maxprice"));
        		ArrayList<Book> booklist = operator.findBoo(id,name,author,dimname,minprice,maxprice);
        		request.setAttribute("booklist", booklist);
        		request.getRequestDispatcher("/findbook.jsp").forward(request, response);
        		return;
        	}
        }else{
			request.setAttribute("msg", "您还未登陆页面，请返回登陆后再进行操作");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
	}

}
