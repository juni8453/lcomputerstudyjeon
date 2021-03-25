package com.lcomputerstudy.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.lcomputerstudy.testmvc.database.DBConnection;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.User;

public class UserDAO {
	
	private static UserDAO dao = null;
    
	private UserDAO() {
		
	}

	public static UserDAO getInstance() {
		if(dao == null) {
			dao = new UserDAO();
		}
		return dao;
	}

	public ArrayList<User> getUsers(int page) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<User> list = null;
		int pageNum = (page-1)*Pagination.perPage;
		
		try {
			conn = DBConnection.getConnection();
			//String query = "select * from user limit ?,3";
			String query = new StringBuilder()
					.append("SELECT 		@ROWNUM := @ROWNUM - 1 AS ROWNUM,\n")
					.append("				ta.*\n")
					.append("FROM 			user ta,\n")
					.append("				(SELECT @rownum := (SELECT	COUNT(*)-?+1 FROM user ta)) tb\n")
					.append("LIMIT			?, 3\n")
					.toString();
	       	pstmt = conn.prepareStatement(query);
	       	pstmt.setInt(1, pageNum); 
	       	pstmt.setInt(2, pageNum);
	        rs = pstmt.executeQuery();
	        list = new ArrayList<User>();

	        while(rs.next()){     
	        	User user = new User();
	        	user.setRownum(rs.getInt("ROWNUM"));
       	       	user.setU_idx(rs.getInt("u_idx"));
       	       	user.setU_id(rs.getString("u_id"));
       	       	user.setU_name(rs.getString("u_name"));
       	       	user.setU_tel(rs.getString("u_tel"));
       	       	user.setU_age(rs.getString("u_age"));
       	       	
       	       	list.add(user);
	        }
		} catch (Exception e) {
			
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public void insertUser(User user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		try {
			conn = DBConnection.getConnection();
			String sql = "insert into user(u_id,u_pw,u_name,u_tel,u_age) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getU_id());
			pstmt.setString(2, user.getU_pw());
			pstmt.setString(3, user.getU_name());
			pstmt.setString(4, user.getU_tel());
			pstmt.setString(5, user.getU_age());
			pstmt.executeUpdate();
		} catch( Exception ex) {
			System.out.println("SQLException : "+ex.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getUsersCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			String query = "SELECT COUNT(*) count FROM user";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt("count");
			}
		} catch (Exception e) {
	
		} finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	public User loginUser(String idx, String pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "SELECT * FROM user WHERE u_id = ? AND u_pw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			
			if(rs.next()){     
				user = new User();
				user.setU_idx(rs.getInt("u_idx"));
	        	user.setU_pw(rs.getString("u_pw"));
	        	user.setU_id(rs.getString("u_id"));
	        	user.setU_name(rs.getString("u_name"));
		   }
		} catch( Exception ex) {
			System.out.println("SQLException : "+ex.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	} 
	
	public User getUser(String u_idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "SELECT * FROM user WHERE u_idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u_idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				user = new User();
				user.setU_idx(rs.getInt("u_idx"));
	        	user.setU_pw(rs.getString("u_pw"));
	        	user.setU_id(rs.getString("u_id"));
	        	user.setU_name(rs.getString("u_name"));
	        	user.setU_tel(rs.getString("u_tel"));
	        	user.setU_age(rs.getString("u_age"));
		   }
		} catch( Exception ex) {
			System.out.println("SQLException : "+ex.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	
}



