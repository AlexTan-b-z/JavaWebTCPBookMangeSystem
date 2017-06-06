package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controler.Operator;

/**
 * Servlet implementation class deletebookServlet
 */
@WebServlet("/deletebookServlet")
public class deletebookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deletebookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		request.setCharacterEncoding("utf-8");  
        response.setContentType("text/html;charset=utf-8"); 
		String username = (String)request.getSession().getAttribute("username");
		if(username != null){
			int id = -1;
			String name = "";
			String choice = request.getParameter("choice");
			Operator operator = new Operator();
			if(choice.equals("id")){
				String idStr = request.getParameter("id");
				if(idStr.equals("")){
					request.setAttribute("msg", "删除失败！你输入的编号为空");
					request.getRequestDispatcher("/changebook.jsp").forward(request, response);
					return;
				}
				id = Integer.parseInt(idStr);
				boolean isSuccess = operator.deleteBook(id,name);
				if(isSuccess){
					request.setAttribute("msg", "删除成功");
					request.getRequestDispatcher("/deletebook.jsp").forward(request, response);
					return;
				}else{
					request.setAttribute("msg", "删除失败");
					request.getRequestDispatcher("/deletebook.jsp").forward(request, response);
					return;
				}
			}else if(choice.equals("name")){
				name = request.getParameter("bookname");
				if(name.equals("")){
					request.setAttribute("msg", "修改失败！你输入的书名为空");
					request.getRequestDispatcher("/changebook.jsp").forward(request, response);
					return;
				}
				boolean isSuccess = operator.deleteBook(id,name);
				if(isSuccess){
					request.setAttribute("msg", "删除成功");
					request.getRequestDispatcher("/deletebook.jsp").forward(request, response);
					return;
				}else{
					request.setAttribute("msg", "删除失败");
					request.getRequestDispatcher("/deletebook.jsp").forward(request, response);
					return;
				}
			}
		}else{
			request.setAttribute("msg", "您还未登陆页面，请返回登陆后再进行操作");
			request.getRequestDispatcher("/deletebook.jsp").forward(request, response);
			return;
		}
	}

}
