package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controler.Operator;

/**
 * Servlet implementation class changebookServlet
 */
@WebServlet("/changebookServlet")
public class changebookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changebookServlet() {
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
		//doGet(request, response);
		request.setCharacterEncoding("utf-8");  
        response.setContentType("text/html;charset=utf-8"); 
		String username = (String)request.getSession().getAttribute("username");
		if(username != null){
			int id = -1;
			String name = "";
			Operator operator = new Operator();
			String choice = request.getParameter("choice");
			String change = request.getParameter("change");
			if(choice.equals("id")){
				String idStr = request.getParameter("id");
				if(idStr.equals("")){
					request.setAttribute("msg", "�޸�ʧ�ܣ�������ı��Ϊ��");
					request.getRequestDispatcher("/changebook.jsp").forward(request, response);
					return;
				}
				id = Integer.parseInt(idStr);
				if(change.equals("")){
					request.setAttribute("msg", "�޸�ʧ�ܣ���������޸ĵ�����Ϊ��");
					request.getRequestDispatcher("/changebook.jsp").forward(request, response);
					return;
				}else{
					boolean isSuccess = operator.changeBoo(id,name,change);
					if(isSuccess){
						request.setAttribute("msg", "�޸ĳɹ�");
						request.getRequestDispatcher("/changebook.jsp").forward(request, response);
						return;
					}else{
						request.setAttribute("msg", "�޸�ʧ��");
						request.getRequestDispatcher("/changebook.jsp").forward(request, response);
						return;
					}
				}
			}else if(choice.equals("bookname")){
				name = request.getParameter("bookname");
				if(name.equals("")){
					request.setAttribute("msg", "�޸�ʧ�ܣ���������޸ĵ�����Ϊ��");
					request.getRequestDispatcher("/changebook.jsp").forward(request, response);
					return;
				}
				if(change.equals("")){
					request.setAttribute("msg", "�޸�ʧ�ܣ���������޸ĺ������Ϊ��");
					request.getRequestDispatcher("/changebook.jsp").forward(request, response);
					return;
				}else{
					boolean isSuccess = operator.changeBoo(id,name,change);
					if(isSuccess){
						request.setAttribute("msg", "�޸ĳɹ�");
						request.getRequestDispatcher("/changebook.jsp").forward(request, response);
						return;
					}else{
						request.setAttribute("msg", "�޸�ʧ��");
						request.getRequestDispatcher("/changebook.jsp").forward(request, response);
						return;
					}
				}
			}
		}else{
			request.setAttribute("msg", "����δ��½ҳ�棬�뷵�ص�½���ٽ��в���");
			request.getRequestDispatcher("/deletebook.jsp").forward(request, response);
			return;
		}
	}

}
