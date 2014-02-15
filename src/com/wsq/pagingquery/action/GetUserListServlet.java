package com.wsq.pagingquery.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wsq.pagingquery.domain.UserManage;
import com.wsq.pagingquery.model.PageModel;
import com.wsq.pagingquery.model.User;


public class GetUserListServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetUserListServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));  
		
		String uname = request.getParameter("uname");
		String ageStr = request.getParameter("age");
//		System.out.println("ageStr==="+ageStr);
		int age = -1;
		if(ageStr!=null&&!"".equals(ageStr)){
			age = Integer.parseInt(ageStr);
		}
		
		String dateStr = request.getParameter("birthday");
		Date birthday = null;
		if(dateStr!=null&&!"".equals(dateStr)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				birthday = sdf.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		String sex = request.getParameter("sex");
		
		String aihaoStr=request.getParameter("aihao");
		String[] aihao = null;
		if(aihaoStr!=null&&!"".equals(aihaoStr)){
			aihao = aihaoStr.split(" ");
		}
		System.out.println("aihao==="+aihao);
		if(aihao != null){
			for(String str:aihao){
				System.out.println("aihao==="+str);
			}
		}

		String xueli = request.getParameter("xueli");
		
		User user = new User();
		user.setName(uname);
		user.setAge(age);
		user.setBirthday(birthday);
		user.setSex(sex);
		user.setAihaos(aihao);
		user.setXueli(xueli);
		
		UserManage userManage = new UserManage();
		PageModel<User> pageModel= userManage.userSearch(user, pageNo, 10);
//		List<User> userList = pageModel.getList();
//		request.setAttribute("userList", userList);
		request.setAttribute("pageModel", pageModel);
		request.getRequestDispatcher("userinfo_search.jsp").forward(request, response);
		
		out.flush();
		out.close();
	}

//	public void writeResponse(HttpServletRequest request,  
//            HttpServletResponse response, String result) throws IOException {  
//        response.setContentType("text/xml");  
//        response.setHeader("Cache-Control", "no-cache");  
//        response.setHeader("Content-Type", "text/xml; charset=gb18030");  
//        PrintWriter pw = response.getWriter();  
//        pw.write(result);  
//        pw.close();  
//    }  
	
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
