package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NamedMethodGenerator;

import controler.Operator;

/**
 * Servlet implementation class addbookServlet
 */
@WebServlet("/addbookServlet")
public class addbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addbookServlet() {
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
			//处理上传文件
			String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
			File file = new File(savePath);
			String bookname = null;//初始化图书的各个字段
			String author = null;
			String priceStr = null;
			String fileName = null;
			//判断上传文件的保存目录是否存在
			if (!file.exists() && !file.isDirectory()) {
				System.out.println(savePath+"目录不存在，需要创建");
				//创建目录
				file.mkdir();
              }
			try {
				//使用Apache文件上传组件处理文件上传步骤：
				//1、创建一个DiskFileItemFactory工厂
				DiskFileItemFactory factory = new DiskFileItemFactory();
				//2、创建一个文件上传解析器
				ServletFileUpload upload = new ServletFileUpload(factory);
				//解决上传文件名的中文乱码
				upload.setHeaderEncoding("UTF-8");
				//3、判断提交上来的数据是否是上传表单的数据
				if(!ServletFileUpload.isMultipartContent(request)){
					//按照传统方式获取数据
					return;
				}
				//4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
				List<FileItem> list = upload.parseRequest(request);
				for(FileItem item : list){
					//如果fileitem中封装的是普通输入项的数据
					if(item.isFormField()){
						String name = item.getFieldName();
						//解决普通输入项的数据的中文乱码问题
						String value = item.getString("UTF-8");
						//value = new String(value.getBytes("iso8859-1"),"UTF-8");
						System.out.println(name + "=" + value);
						if(name.equals("bookname")){
							bookname = value;
						}
						else if(name.equals("author")){
							author = value;
						}
						else if(name.equals("price")){
							priceStr = value;
						}
					}else{//如果fileitem中封装的是上传文件
						//得到上传的文件名称
						String filename = item.getName();
						System.out.println(filename);
						if(filename==null || filename.trim().equals("")){
							continue;
						}
						String type = filename.substring(filename.indexOf(".")+1);
						System.out.println(type);
						if(!type.equals("txt")){ //如果文件后缀不是txt，则报错
							request.setAttribute("msg", "上传文件格式错误，请选择txt类型文件");  
				            request.getRequestDispatcher("/addbook.jsp").forward(request, response);  
				            return;
						}
						//注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
						//处理获取到的上传文件的文件名的路径部分，只保留文件名部分
						filename = filename.substring(filename.lastIndexOf("\\")+1);
						fileName = filename;
						//获取item中的上传文件的输入流
						InputStream in = item.getInputStream();
						//创建一个文件输出流
						FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
						 //创建一个缓冲区
						byte buffer[] = new byte[1024];
						//判断输入流中的数据是否已经读完的标识
						int len = 0;
						//循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
						while((len=in.read(buffer))>0){
							//使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
							out.write(buffer, 0, len);
						}
						//关闭输入流
						in.close();
						//关闭输出流
						out.close();
						//删除处理文件上传时生成的临时文件
						item.delete();
					}
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(fileName==null||fileName.trim().isEmpty()||bookname==null||bookname.trim().isEmpty()||author==null||author.trim().isEmpty()||priceStr==null||priceStr.trim().isEmpty()){
	            request.setAttribute("msg", "输入不能为空");  
	            request.getRequestDispatcher("/addbook.jsp").forward(request, response);  
	            return;
	        }
			//String bookname = request.getParameter("bookname");
			//String author = request.getParameter("author");
			//String priceStr =request.getParameter("price");
			float price = Float.parseFloat(priceStr);
			Operator operate = new Operator();
			boolean isSuccess = operate.addBook(bookname, author, price, fileName);
			if(isSuccess){
				request.setAttribute("msg", "增加成功");
				request.getRequestDispatcher("/addbook.jsp").forward(request, response);  
				return;
			}else{
				request.setAttribute("msg", "增加失败");
				request.getRequestDispatcher("/addbook.jsp").forward(request, response);  
				return;
			}
		}else{
			request.setAttribute("msg", "您还未登陆页面，请返回登陆后再进行操作");
			request.getRequestDispatcher("/addbook.jsp").forward(request, response);
			return;
		}
	}

}
