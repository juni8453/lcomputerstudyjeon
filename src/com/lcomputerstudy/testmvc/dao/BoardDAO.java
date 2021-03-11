package com.lcomputerstudy.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.lcomputerstudy.testmvc.database.DBConnection;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.vo.Comment;

public class BoardDAO {
	
	private static BoardDAO dao = null;
    
	private BoardDAO() {
		
	}

	public static BoardDAO getInstance() {
		if(dao == null) {
			dao = new BoardDAO();
		}
		return dao;
	}
	
	public void insertBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		try {
			conn = DBConnection.getConnection();
			String sql = "insert into board(b_title,b_content,u_idx,b_views) values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getB_title());
			pstmt.setString(2, board.getB_content());
			pstmt.setInt(3, board.getU_idx());
			pstmt.setInt(4, 0);
			//B_date는 DB상에서 기본값을 current_timestamp()로 변경하여 자동으로 찍히게 한다.
			//따라서 로직에서 제외시켜도 됨
			pstmt.executeUpdate();
		} catch(Exception ex) {
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
	
	public void insertComment(Comment comment) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "insert into comment(c_content,b_idx,u_idx) values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getC_content());
			pstmt.setInt(2, comment.getB_idx());
			pstmt.setInt(3, comment.getU_idx());
			pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("SQLException : "+e.getMessage());
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Board> getBoards(int page) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Board> list = null;
		// list를 반환할 변수 선언
		int pageNum = (page-1)*3;
		
		try {
			conn = DBConnection.getConnection();
			String query = new StringBuilder()
					.append("SELECT 		@ROWNUM := @ROWNUM - 1 AS ROWNUM,\n")
					.append("				ta.*\n")
					.append("FROM 			board ta,\n")
					.append("				(SELECT @rownum := (SELECT	COUNT(*)-?+1 FROM board ta)) tb\n")
					.append("LIMIT			?, 3\n")
					.toString();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pageNum);
			pstmt.setInt(2, pageNum);
			rs = pstmt.executeQuery();
			list = new ArrayList<Board>();
			
			while(rs.next()){
				Board board = new Board();
				board.setRownum(rs.getInt("ROWNUM"));
				board.setB_idx(rs.getInt("b_idx"));
				board.setB_title(rs.getString("b_title"));
				board.setB_content(rs.getString("b_content"));
				board.setB_date(rs.getString("b_date"));
				board.setU_idx(rs.getInt("u_idx"));
				board.setB_views(rs.getInt("b_views"));
				
				list.add(board);
				
		   }
		} catch(Exception e) {
		
		} finally {
			try {
				if(rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public Board getBoard(String b_idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "SELECT*FROM board WHERE b_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b_idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// if문 안에 Board board = null 이라고 변수선언을 한다면, 
				// if문이 끝나고 없어지므로 Board getBoard 메서드의 인스턴스 변수로 지정하는 것
				board = new Board();
				board.setB_title(rs.getString("b_title"));
				//vo 패키지의 Board 클래스 private 변수에 값을 저장하기 위해 미리 쿼리를 저장한 ResultSet 객체의 
				//메서드 getString()을 사용해 vo 패키지의 private 변수에 복사한다.
				board.setB_content(rs.getString("b_content"));
				board.setB_date(rs.getString("b_date"));
				board.setU_idx(rs.getInt("u_idx"));
				board.setB_idx(rs.getInt("b_idx"));
				//보드서비스의 getBoard메서드 호출 > dao의 getBoard메서드 호출 > 보드에 name값 받아옴
			}
		} catch(Exception ex) {
			System.out.println("SQLException : "+ex.getMessage());
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return board;
		// 받아온 값들 컨트롤러의 board로 리턴
	}
	
	public int getBoardCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			String query = "SELECT COUNT(*) count FROM board";
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

	public void getBoardViews(String b_idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
	 //	ResultSet rs = null; 데이터 베이스를 실행만 시키면 되기때문에 필요없음
     // int views = 0; 반환값 없어서 딱히 필요없음
		
		try {
			conn = DBConnection.getConnection();
			String query = "UPDATE board SET b_views = b_views+1 WHERE b_idx=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b_idx);
			// 0번째 자리에, 얻어온 b_idx값 지정
			//물음표 값을 지정해야하기 때문에 setString 사용해서 값 지정(b_idx는 String 타입이라서)
			pstmt.executeUpdate();
			
			} catch(Exception e) {
				
			} finally {
				try {
					if(pstmt != null)pstmt.close();
					if(conn != null)conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	public ArrayList<Comment> getBoardComments(int b_idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Comment> commentlist = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "SELECT*FROM comment WHERE b_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_idx);
			rs = pstmt.executeQuery();
			commentlist = new ArrayList<Comment>();
			
			while(rs.next()){
				Comment comment = new Comment();
				comment.setC_idx(rs.getInt("c_idx"));
				comment.setC_content(rs.getString("c_content"));
				comment.setC_date(rs.getString("c_date"));
				comment.setB_idx(rs.getInt("b_idx"));
				comment.setU_idx(rs.getInt("u_idx"));
				
				commentlist.add(comment);
			}
		} catch(Exception e) {
			e.printStackTrace();
				
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return commentlist;
	}

	public void updateCommnet(Comment comment) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "UPDATE comment SET c_content=? WHERE c_idx =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getC_content());
			pstmt.setInt(2, comment.getC_idx());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}
