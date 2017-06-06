package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controler.Operator;

/**
 * Servlet implementation class viewServlet
 */
@WebServlet("/viewServlet")
public class viewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewServlet() {
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
        	doPost(request,response);
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
        String id = request.getParameter("id");
        Operator operate = new Operator();
        //通过id得到要预览的文件名
        String fileName  = operate.findFilename(id);
        String fileSaveRootPath = this.getServletContext().getRealPath("/WEB-INF/upload");
        String path = fileSaveRootPath + "\\" + fileName;
        String encoding="GBK";
        File file=new File(path);
        if(!file.exists()){
        	request.setAttribute("msg", "您要预览的文件已被删除！");  
            request.getRequestDispatcher("/findbookServlet").forward(request, response);  
            return;
        }
        InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
        BufferedReader bufferedReader = new BufferedReader(read);
        String TXT = null;
        String line = null;
        int i = 0; //read计数
        while((line = bufferedReader.readLine()) != null){
            TXT = TXT + line;
            i++;
            if(i == 50){
            	break;
            }
            //System.out.println(TXT);
        }
        read.close();
        TXT = TXT.split("null")[1];
        //System.out.println(TXT);
        request.setAttribute("text", TXT); 
        request.getRequestDispatcher("/view.jsp").forward(request, response);
        return;
	}
}
