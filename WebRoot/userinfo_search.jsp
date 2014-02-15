<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.wsq.pagingquery.dao.*,com.wsq.pagingquery.model.*,java.util.*" %>
<%@page import="java.text.SimpleDateFormat"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查询用户</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	
	</script>
  </head>
 
  <body>
  		<%
  			PageModel<User> pageModel = (PageModel<User>)request.getAttribute("pageModel");
  			List<User> userList=null;
  			if(pageModel!=null){
  				 userList= pageModel.getList();
  			}
  			
  		 %>
  		
   		<table border = "1px" align="center">
   				
	   			<tr>
	   					<th>用户名</th>
	   					<th>年龄</th>
	   					<th>性别</th>
	   					<th>出生日期 </th>
	   					<th>爱好 </th>
	   					<th>学历</th>
	   					<th>操作</th>
	   			</tr>
	   			<form action="GetUserListServlet" method="post">
	   			<tr>
	   					<input type="hidden" name="pageNo" value="1">
	   					<td><input type="text" name="uname"></td>
	   					<td><input type="text" name="age"></td>
	   					<td><input type="text" name="sex"></td>
	   					<td><input type="text" name="birthday"> </td>
	   					<td><input type="text" name="aihao"> </td>
	   					<td><input type="text" name="xueli"></td>
	   					<td><input type="submit"  /></td>
	   			</tr>
	   			</form>
   				<% 
   					if(userList!=null){
   						for(User user:userList){		
   				%>
   					<tr>
   						<td><%=user.getName() %></td>
   						<td><%=user.getAge() %></td>
   						<td><%=user.getSex() %></td>
   						<td><%=new SimpleDateFormat("yyyy-MM-dd").format(user.getBirthday()) %></td>
   						<td>
   						<%
	   						for(String str:user.getAihaos()){
	   						%>
	   						<%=str %>&nbsp;
	   						<% 
	   						}
	   						 %>
   						 </td>
   						<td><%=user.getXueli() %></td>
   						<td>
   							<a href="#">编辑</a>&nbsp;
   							<a href="#" >删除</a>
   						</td>
   					</tr>
   				<%
   						}
   					}
   				%>
   				
   				<tr>
   					<td colspan="7" align="center">
   						<%
   							if(pageModel!=null){
   						%>
   								<a href="GetUserListServlet?pageNo=<%=pageModel.getFirstPage()%>">第一页</a>
   								<a href="GetUserListServlet?pageNo=<%=pageModel.getPrePage()%>">上一页</a>
   						<%
   								for(int i = 1 ; i<=pageModel.getTotalPage();i++ ){
   									if(pageModel.getPageNo()==i){
   						%>
   										<a href="GetUserListServlet?pageNo=<%=i%>" style="color: red;"><%=i%></a>
   						<% 		
   									}else{
   						%>
   										<a href="GetUserListServlet?pageNo=<%=i%>"><%=i%></a>
   						<% 
   									}
   						
   								}
   						%>
   								<a href="GetUserListServlet?pageNo=<%=pageModel.getBackPage()%>">下一页</a>
   								<a href="GetUserListServlet?pageNo=<%=pageModel.getLastPage()%>">最后一页</a>
   						<%
   								
   							}
   						 %>
   					</td>
   				</tr>
   		</table>
   		
   		<%
   			if(pageModel!=null){
   		%>
   		getPageNo<%=pageModel.getPageNo() %>
   		getPageSize<%=pageModel.getPageSize() %>
   		getTotalNum<%=pageModel.getTotalNum() %>
   		getTotalPage<%=pageModel.getTotalPage() %>
   		<% 	
   			}
   		 %>
   		

  </body>
</html>
