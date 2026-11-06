package com.ssafy.shopApp.test;

import java.sql.*;

import com.ssafy.shopApp.util.DBUtil;

public class SelectBasicTest {
	
    public static void main(String[] args) {
        printEmp("SMITH");
//        printEmp("' or '1");
    }
    
//    public static void printEmp(String ename) {
//    	String sql = "select ename, sal, deptno from emp where ename = '"+ename+"'";
//    	try(Connection conn= DBUtil.getConnection();
//    			Statement stmt = conn.createStatement();){    	
//    		ResultSet rs = stmt.executeQuery(sql);
//    		
//    		while(rs.next()) {
//    			System.out.println(rs.getString(1) + ", " + rs.getInt(2) + ", " + rs.getInt("deptno"));
//    		}
//    	} catch (SQLException e) {
//			e.printStackTrace();
//		}	
//    }
    public static void printEmp(String ename) {
    	String sql = "select ename, sal, deptno from emp where ename = ? ";
    	try(Connection conn= DBUtil.getConnection();
    			PreparedStatement stmt = conn.prepareStatement(sql);){ 
    		stmt.setString(1, ename);
    		ResultSet rs = stmt.executeQuery();
    		
    		while(rs.next()) {
    			System.out.println(rs.getString(1) + ", " + rs.getInt(2) + ", " + rs.getInt("deptno"));
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}	
    }
}