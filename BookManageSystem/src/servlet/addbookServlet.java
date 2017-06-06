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
			//�����ϴ��ļ�
			String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
			File file = new File(savePath);
			String bookname = null;//��ʼ��ͼ��ĸ����ֶ�
			String author = null;
			String priceStr = null;
			String fileName = null;
			//�ж��ϴ��ļ��ı���Ŀ¼�Ƿ����
			if (!file.exists() && !file.isDirectory()) {
				System.out.println(savePath+"Ŀ¼�����ڣ���Ҫ����");
				//����Ŀ¼
				file.mkdir();
              }
			try {
				//ʹ��Apache�ļ��ϴ���������ļ��ϴ����裺
				//1������һ��DiskFileItemFactory����
				DiskFileItemFactory factory = new DiskFileItemFactory();
				//2������һ���ļ��ϴ�������
				ServletFileUpload upload = new ServletFileUpload(factory);
				//����ϴ��ļ�������������
				upload.setHeaderEncoding("UTF-8");
				//3���ж��ύ�����������Ƿ����ϴ���������
				if(!ServletFileUpload.isMultipartContent(request)){
					//���մ�ͳ��ʽ��ȡ����
					return;
				}
				//4��ʹ��ServletFileUpload�����������ϴ����ݣ�����������ص���һ��List<FileItem>���ϣ�ÿһ��FileItem��Ӧһ��Form����������
				List<FileItem> list = upload.parseRequest(request);
				for(FileItem item : list){
					//���fileitem�з�װ������ͨ�����������
					if(item.isFormField()){
						String name = item.getFieldName();
						//�����ͨ����������ݵ�������������
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
					}else{//���fileitem�з�װ�����ϴ��ļ�
						//�õ��ϴ����ļ�����
						String filename = item.getName();
						System.out.println(filename);
						if(filename==null || filename.trim().equals("")){
							continue;
						}
						String type = filename.substring(filename.indexOf(".")+1);
						System.out.println(type);
						if(!type.equals("txt")){ //����ļ���׺����txt���򱨴�
							request.setAttribute("msg", "�ϴ��ļ���ʽ������ѡ��txt�����ļ�");  
				            request.getRequestDispatcher("/addbook.jsp").forward(request, response);  
				            return;
						}
						//ע�⣺��ͬ��������ύ���ļ����ǲ�һ���ģ���Щ������ύ�������ļ����Ǵ���·���ģ��磺  c:\a\b\1.txt������Щֻ�ǵ������ļ������磺1.txt
						//�����ȡ�����ϴ��ļ����ļ�����·�����֣�ֻ�����ļ�������
						filename = filename.substring(filename.lastIndexOf("\\")+1);
						fileName = filename;
						//��ȡitem�е��ϴ��ļ���������
						InputStream in = item.getInputStream();
						//����һ���ļ������
						FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
						 //����һ��������
						byte buffer[] = new byte[1024];
						//�ж��������е������Ƿ��Ѿ�����ı�ʶ
						int len = 0;
						//ѭ�������������뵽���������У�(len=in.read(buffer))>0�ͱ�ʾin���滹������
						while((len=in.read(buffer))>0){
							//ʹ��FileOutputStream�������������������д�뵽ָ����Ŀ¼(savePath + "\\" + filename)����
							out.write(buffer, 0, len);
						}
						//�ر�������
						in.close();
						//�ر������
						out.close();
						//ɾ�������ļ��ϴ�ʱ���ɵ���ʱ�ļ�
						item.delete();
					}
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(fileName==null||fileName.trim().isEmpty()||bookname==null||bookname.trim().isEmpty()||author==null||author.trim().isEmpty()||priceStr==null||priceStr.trim().isEmpty()){
	            request.setAttribute("msg", "���벻��Ϊ��");  
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
				request.setAttribute("msg", "���ӳɹ�");
				request.getRequestDispatcher("/addbook.jsp").forward(request, response);  
				return;
			}else{
				request.setAttribute("msg", "����ʧ��");
				request.getRequestDispatcher("/addbook.jsp").forward(request, response);  
				return;
			}
		}else{
			request.setAttribute("msg", "����δ��½ҳ�棬�뷵�ص�½���ٽ��в���");
			request.getRequestDispatcher("/addbook.jsp").forward(request, response);
			return;
		}
	}

}
