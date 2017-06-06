package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controler.Operator;
import model.User;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServlet() {
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
        String username = request.getParameter("username");  
        String password = request.getParameter("password");  
        String rpsw = request.getParameter("rpsw");
        String verifyc  = request.getParameter("verifycode"); //�õ������������  
        String svc =(String) request.getSession().getAttribute("sessionverify");
        if(!svc.equalsIgnoreCase(verifyc)){  
            request.setAttribute("msg", "��֤�����");  
            request.getRequestDispatcher("/regist.jsp").forward(request, response);  
            return;  
        }
        if(username==null||username.trim().isEmpty()){  
            request.setAttribute("msg", "�ʺŲ���Ϊ��");  
            request.getRequestDispatcher("/regist.jsp").forward(request, response);  
            return;  
        }  
        if(password==null||password.trim().isEmpty()){  
            request.setAttribute("msg", "���벻��Ϊ��");  
            request.getRequestDispatcher("/regist.jsp").forward(request, response);  
            return;  
        }  
        if(!password.equals(rpsw)){  
            request.setAttribute("msg", "������������벻ͬ");  
            request.getRequestDispatcher("/regist.jsp").forward(request, response);  
            return;  
        }
        Operator operate = new Operator();
        boolean isSuccess = operate.addUser(username, password);
        if(isSuccess){
        	request.setAttribute("msg", "��ϲ��"+username+"��ע��ɹ�");  
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }else{
        	request.setAttribute("msg", "�Բ���"+username+"��ע��ʧ��");  
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
	}

}
