package com.lcomputerstudy.testmvc.service;

import java.util.ArrayList;
import com.lcomputerstudy.testmvc.dao.BoardDAO;
import com.lcomputerstudy.testmvc.dao.UserDAO;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.vo.Comment;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.Search;
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
		// dao.insertBoard(board) 메소드로 title, content, u_idx, group_id를 얻어오고
		// board.setB_gruop에 셋팅한다.
		
		dao.updateBoard(board);
	}
	
	public void insertReply(Board board) {
		int order = dao.getOrder(board);
		//dao.getOrder(board)에서 order값을 return 받고,
		
		board.setB_order(order);
		//board.setB_order(order)로 order값을 셋팅한 뒤,
		
		dao.insertReply(board);
		//dao.insertReply(board) 호출
	}
	
	public void insertComment(Comment comment) {
		dao.insertComment(comment);
	}

	public ArrayList<Board> getBoards(int page, Search search) {
		return dao.getBoards(page, search);
	}

	public Board getBoard(String b_idx) {
			   dao.getBoardViews(b_idx);
			   // return 되기 전에 DAO의 getBoardViews(); 메서드 호출
		return dao.getBoard(b_idx);
		//보드서비스의 getBoard메서드 호출 > dao의 getBoard메서드 호출
	}
	
	public int getBoardCount(Search search) {
		return dao.getBoardCount(search);
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

