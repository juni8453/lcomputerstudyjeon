package com.lcomputerstudy.testmvc.service;

import java.util.ArrayList;
import com.lcomputerstudy.testmvc.dao.BoardDAO;
import com.lcomputerstudy.testmvc.dao.UserDAO;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.vo.Comment;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.User;

public class BoardService {
	// getInstance(); 메소드 실행 > service = new BoardService();로 객체 생성 
	
	private static BoardService service = null;
	private static BoardDAO dao = null;
    
	private BoardService() {
	//싱글턴 패턴이기 때문에 생성자의 제어자로 private를 붙여줌
	}

	public static BoardService getInstance() {
		if(service == null) {
			service = new BoardService();
			dao = BoardDAO.getInstance();
		}
		return service;
	}
	
	public void insertBoard(Board board) {
		dao.insertBoard(board);
	}
	
	public void insertComment(Comment comment) {
		dao.insertComment(comment);
	}

	public ArrayList<Board> getBoards(int page, String keyWord) {
		return dao.getBoards(page, keyWord);
	}

	public Board getBoard(String b_idx) {
			   dao.getBoardViews(b_idx);
			   // return 되기 전에 DAO의 getBoardViews(); 메서드 호출
		return dao.getBoard(b_idx);
		//보드서비스의 getBoard메서드 호출 > dao의 getBoard메서드 호출
	}
	
	public int getBoardCount(String keyWord) {
		return dao.getBoardCount(keyWord);
	}

	public ArrayList<Comment> getBoardComments(int b_idx) {
		return dao.getBoardComments(b_idx);
	}

	public void updateComment(Comment comment) {
		dao.updateCommnet(comment);
	}
	
	public void deleteComment(Comment comment) {
		dao.deleteComment(comment);
	}
}

