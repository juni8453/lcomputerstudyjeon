package com.lcomputerstudy.testmvc.service;

import java.util.ArrayList;
import com.lcomputerstudy.testmvc.dao.BoardDAO;
import com.lcomputerstudy.testmvc.dao.UserDAO;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.User;

public class BoardService {
	
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

	public ArrayList<Board> getBoards(int page) {
		return dao.getBoards(page);
	}

	public Board getBoard(String b_idx) {
		return dao.getBoard(b_idx);
	}
	
	public int getBoardCount() {
		return dao.getBoardCount();
	}
}

