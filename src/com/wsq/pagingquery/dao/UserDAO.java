package com.wsq.pagingquery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wsq.pagingquery.model.User;
import com.wsq.pagingquery.util.DBUtility;


public class UserDAO {
	public List<User> getUserList(User user,int pageNo,int pageSize){
		List<User> userList= new ArrayList<User>();
		Connection conn = DBUtility.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		sb.append("select name,pwd,age,birthday,sex,aihao,xueli from quser where 1=1");
		if(null != user.getName() && !"".equals(user.getName())){
			sb.append(" AND name like ?	 ");
		}
		if(null != user.getPwd() && !"".equals(user.getPwd())){
			sb.append(" AND pwd like ?	 ");
		}
		if(user.getAge() != -1){
			sb.append(" AND age = ?	 ");
		}
		if(null != user.getBirthday()){
			sb.append(" AND birthday = ?	 ");
		}
		if(null != user.getSex() && !"".equals(user.getSex())){
			sb.append(" AND sex like ?	 ");
		}
		
		if(null != user.getAihaos()){
			sb.append(" AND aihao like ?	 ");
		}
		if(null != user.getXueli() && !"".equals(user.getXueli())){
			sb.append(" AND xueli like ?	 ");
		}
		sb.append(" limit ?,?");
		try {
			ps = conn.prepareStatement(sb.toString());
			int index = 1;
			if(null != user.getName() && !"".equals(user.getName())){
				ps.setString(index++, "%" +user.getName()+"%");
			}
			if(null != user.getPwd() && !"".equals(user.getPwd())){
				ps.setString(index++, "%" +user.getPwd()+"%");
			}
			if(user.getAge() != -1){
				ps.setInt(index++, user.getAge());
			}
			if(null != user.getBirthday()){
				ps.setTimestamp(index++, new Timestamp(user.getBirthday().getTime()));
			}
			if(null != user.getSex() && !"".equals(user.getSex())){
				ps.setString(index++, "%" +user.getSex()+"%");
				System.out.println("查询dao中aihaostring="+user.getSex());
			}
			
			
			if(null != user.getAihaos()){
				String aihaoString = "";
				for(String str : user.getAihaos()){
//					aihaoString+=ConstantMap.getConstantMap().get(str)+",";
					aihaoString+=str+",";
				}
//				System.out.println("查询dao中aihaostring="+aihaoString);
				ps.setString(index++, "%" +aihaoString+"%");
			}
			if(null != user.getXueli() && !"".equals(user.getXueli())){
				ps.setString(index++, "%" +user.getXueli()+"%");
//				System.out.println("查询dao中user.getXueli="+user.getXueli());
			}
			ps.setInt(index++,(pageNo - 1) * pageSize );
			ps.setInt(index++,pageSize);
			
			rs = ps.executeQuery();
			User user2 = null;
			while(rs.next()){
				user2 = new User();
				user2.setName(rs.getString("name"));
				user2.setAge(rs.getInt("age"));
				java.sql.Date sqlDate = rs.getDate("birthday");
				user2.setBirthday(new Date(sqlDate.getTime()));
				user2.setSex(rs.getString("sex"));
				String aihaoStr = rs.getString("aihao");
				String[] aihaos = aihaoStr.split(",");
				user2.setAihaos(aihaos);
				user2.setXueli(rs.getString("xueli"));
				userList.add(user2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtility.closeResultSet(rs);
			DBUtility.closePreparedStatement(ps);
		}
		
		return userList;
	}
	
	public int getTotalNum(User user){
		int count = 0;
		Connection conn = DBUtility.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		sb.append("select count(*) from quser where 1=1");
		if(null != user.getName() && !"".equals(user.getName())){
			sb.append(" AND name like ?	 ");
		}
		if(null != user.getPwd() && !"".equals(user.getPwd())){
			sb.append(" AND pwd like ?	 ");
		}
		if(user.getAge() != -1){
			sb.append(" AND age = ?	 ");
		}
		if(null != user.getBirthday()){
			sb.append(" AND birthday = ?	 ");
		}
		if(null != user.getSex() && !"".equals(user.getSex())){
			sb.append(" AND sex like ?	 ");
		}
		
		if(null != user.getAihaos()){
			sb.append(" AND aihao like ?	 ");
		}
		if(null != user.getXueli() && !"".equals(user.getXueli())){
			sb.append(" AND xueli like ?	 ");
		}
		
		try {
			ps = conn.prepareStatement(sb.toString());
			int index = 1;
			if(null != user.getName() && !"".equals(user.getName())){
				ps.setString(index++, "%" +user.getName()+"%");
			}
			if(null != user.getPwd() && !"".equals(user.getPwd())){
				ps.setString(index++, "%" +user.getPwd()+"%");
			}
			if(user.getAge() != -1){
				ps.setInt(index++, user.getAge());
			}
			if(null != user.getBirthday()){
				ps.setTimestamp(index++, new Timestamp(user.getBirthday().getTime()));
			}
			if(null != user.getSex() && !"".equals(user.getSex())){
				ps.setString(index++, "%" +user.getSex()+"%");
			}
			
			
			if(null != user.getAihaos()){
				String aihaoString = "";
				for(String str : user.getAihaos()){
					aihaoString+=str+",";
				}
				ps.setString(index++, "%" +aihaoString+"%");
			}
			if(null != user.getXueli() && !"".equals(user.getXueli())){
				ps.setString(index++, "%" +user.getXueli()+"%");
			}
			
			rs = ps.executeQuery();
			if(rs.next()){
				count =  rs.getInt(1);  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtility.closeResultSet(rs);  
			DBUtility.closePreparedStatement(ps);
		}
		
		return count;
	}
}
