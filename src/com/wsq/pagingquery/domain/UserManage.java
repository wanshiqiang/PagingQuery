package com.wsq.pagingquery.domain;

import java.sql.Connection;

import com.wsq.pagingquery.dao.UserDAO;
import com.wsq.pagingquery.model.PageModel;
import com.wsq.pagingquery.model.User;
import com.wsq.pagingquery.util.DBUtility;

public class UserManage {
	 private UserDAO userDao = null;  
	  
	 public UserManage () {  
	     userDao = new UserDAO();  
	 }  

	 public PageModel<User> userSearch(User u, int pageNo,  
	            int pageSize)  {  
	        Connection connection = null;  
	        PageModel<User> pagination = new PageModel<User>();  
	        try {  
	        	connection = DBUtility.getConnection();
	        	DBUtility.setAutoCommit(connection, false);
	            pagination.setList(userDao.getUserList(u, pageNo, pageSize));  
	            pagination.setPageNo(pageNo);  
	            pagination.setPageSize(pageSize);  
	            pagination.setTotalNum(userDao.getTotalNum(u));  
	            DBUtility.commit(connection);
	        } catch (Exception e) {  
	        	DBUtility.rollBack(connection);
	            e.printStackTrace();  
	        } finally {  
	        	DBUtility.closeConnection();
	        }  
	        return pagination;  
	    }  

}
