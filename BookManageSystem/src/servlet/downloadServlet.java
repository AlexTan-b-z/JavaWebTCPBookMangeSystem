package servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controler.Operator;

/**
 * Servlet implementation class downloadServlet
 */
@WebServlet("/downloadServlet")
public class downloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public downloadServlet() {
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
        //通过id得到要下载的文件名
        String fileName  = operate.findFilename(id);
        System.out.println(fileName);
        //上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
        String fileSaveRootPath = this.getServletContext().getRealPath("/WEB-INF/upload");
        System.out.println("fileSaveRootPath:"+fileSaveRootPath);
        //通过文件名找出文件的所在目录
        String path = fileSaveRootPath + "\\" + fileName;
        System.out.println("path:"+path);
        //得到要下载的文件
        File file = new File(path);
        //如果文件不存在
        if(!file.exists()){
        	request.setAttribute("msg", "您要下载的资源已被删除！");  
            request.getRequestDispatcher("/findbookServlet").forward(request, response);  
            return;
        }
        String filename = file.getName();// 获取日志文件名称
        InputStream fis = new BufferedInputStream(new FileInputStream(path));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        response.reset();
        // 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.replaceAll(" ", "").getBytes("utf-8"),"iso8859-1"));
        response.addHeader("Content-Length", "" + file.length());
        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        os.write(buffer);// 输出文件
        os.flush();
        os.close();
	}
}
