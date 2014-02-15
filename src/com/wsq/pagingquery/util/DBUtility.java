package com.wsq.pagingquery.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtility {  
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();  
  
    private static String driver = "com.mysql.jdbc.Driver";
	private static String username = "root";
	private static String password = "113613";
	private static String url = "jdbc:mysql://localhost:3306/b2c";
	
    public static Connection getConnection() {  
        Connection conn = null;  
        conn = threadLocal.get();  
        if (conn == null) {  
            try {  
            	Class.forName(driver);
    			conn = DriverManager.getConnection(url,username,password);  
                threadLocal.set(conn);  
            } catch (ClassNotFoundException e) {  
                e.printStackTrace();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
        return conn;  
    }  
  
    // 封装设置Connection自动提交   
    public static void setAutoCommit(Connection conn, Boolean flag) {  
        try {  
            conn.setAutoCommit(flag);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
  
    // 设置事务提交   
    public static void commit(Connection conn) {  
        try {  
            conn.commit();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
  
    // 封装设置Connection回滚   
    public static void rollBack(Connection conn) {  
        try {  
            conn.rollback();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
  
    // 封装关闭Connection、PreparedStatement、ResultSet的函数   
    public static void closeConnection() {  
        Connection conn = threadLocal.get();  
        try {  
            if (conn != null) {  
                conn.close();  
                conn = null;  
                threadLocal.remove();  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
  
    }  
  
    public static void closePreparedStatement(PreparedStatement pstmt) {  
        try {  
            if (pstmt != null) {  
                pstmt.close();  
                pstmt = null;  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
  
    public static void closeResultSet(ResultSet rs) {  
        try {  
            if (rs != null) {  
                rs.close();  
                rs = null;  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
}  
