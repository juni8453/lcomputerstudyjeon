package com.lcomputerstudy.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcomputerstudy.testmvc.service.UserService;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.User;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.vo.Comment;
import com.lcomputerstudy.testmvc.service.BoardService;
import com.lcomputerstudy.testmvc.dao.BoardDAO;


@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		String view = null;
		String pw = null;
		String idx = null;
		HttpSession session = null;
		User user = null;
		Board board = null;
		Comment comment = null;
		command = checkSession(request, response, command);
		BoardService boardService = null;
		UserService userService = null;
		boolean isRedirect = false; 
		//isRedirect 사용하기 위해 기본값 false로 변수 선언

		int usercount = 0;
		int boardcount = 0;
		int page = 1;
		int count = 0;
		int b_idx = 0;

		switch (command) {
		case "/user-list.do":
			String reqPage = request.getParameter("page");
			if(reqPage != null) {
				page = Integer.parseInt(reqPage);
			}
			userService = UserService.getInstance();
			ArrayList<User> list = userService.getUsers(page);
			usercount = userService.getUsersCount();
			Pagination pagination = new Pagination(page, usercount);
			
			request.setAttribute("list", list);
			//(앞 list는 이름(JSP에 적는 값,아무렇게 적어도 상관 없음 ${list}, 뒷 list는 변수 값/마우스 대면 불나옴) 
			request.setAttribute("usercount", usercount);
			request.setAttribute("pagination", pagination);
			
			view = "user/list";
			break;

		case "/user-insert.do":
			view = "user/insert";
			break;

		case "/user-insert-process.do":
			user = new User();
			user.setU_id(request.getParameter("id"));
			user.setU_pw(request.getParameter("pw"));
			user.setU_name(request.getParameter("name"));
			user.setU_tel(request.getParameter("tel1") + "-" + request.getParameter("tel2") + "-"
					+ request.getParameter("tel3"));
			user.setU_age(request.getParameter("age"));

			userService = UserService.getInstance();
			userService.insertUser(user);

			view = "user/insert-result";
			break;
			
		case "/user-login.do":
			view = "user/login";
			break;
			
		case "/user-login-process.do":
			idx = request.getParameter("login_id");
			pw = request.getParameter("login_password");
			
			userService = UserService.getInstance();
			user = userService.loginUser(idx,pw);
			
			if(user != null) {
				session = request.getSession();
//				session.setAttribute("u_idx", user.getU_idx());
//				session.setAttribute("u_id", user.getU_id());
//				session.setAttribute("u_pw", user.getU_pw());
//				session.setAttribute("u_name", user.getU_name());
				session.setAttribute("user", user);
				//setAttribute() 메서드를 호출하여 ${sessionScope.user.user_컬럼명}으로 사용가능하다.

				view = "user/login-result";
			} else {
				view = "user/login-fail";
			}			
			break;
			
		case "/logout.do":
			session = request.getSession();
			session.invalidate();
			view = "user/login";
			break;
			
		case "/access-denied.do":
			view = "user/access-denied";
			break;
			
		case "/user-detail.do":
			idx = request.getParameter("u_idx");
			userService = UserService.getInstance();
			user = null;
			user = userService.getUser(idx);
			request.setAttribute("user", user);
			view = "user/detail";
			break;
			
		case "/board-detail.do":
			b_idx = Integer.parseInt(request.getParameter("b_idx"));
			boardService = BoardService.getInstance();
			board = boardService.getBoard(Integer.toString(b_idx));
			//보드서비스의 getBoard메서드 호출 > dao의 getBoard메서드 호출 > 보드에 name값 받아옴 >> 리턴된 board값 null자리에 저장.
			request.setAttribute("board", board);
			//저장된 board값을 setArttribute로 뽑기
			ArrayList<Comment> boardcomment = boardService.getBoardComments(b_idx);
			//한 게시물에 달려있는 댓글들만 가져와야 하기 때문에 b_idx값이 필요
			request.setAttribute("boardcomment", boardcomment);
			view = "board/detail";
			break;
			
		case "/board-write.do":
			view = "board/write";
			break;
			
		case "/board-write-process.do":
			board = new Board();
			
			board.setB_title(request.getParameter("title"));
			board.setB_content(request.getParameter("content"));
			board.setU_idx(Integer.parseInt(request.getParameter("u_idx")));
			//U_idx는 user 보드와 연결시켜주는 키값이기 때문에 꼭 넣어줘야한다.
			//U_idx는 int 타입이기 때문에 Integer.parseInt로 형변환 해줘야함
			
			boardService = BoardService.getInstance();
			boardService.insertBoard(board);
			view = "board/write-result";
			break;
			
		case "/board-comment-process.do":
			comment = new Comment();
			
			comment.setC_content(request.getParameter("content"));
			comment.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
			comment.setU_idx(Integer.parseInt(request.getParameter("u_idx")));
			
			boardService = BoardService.getInstance();
			boardService.insertComment(comment);
		//	view = "board/comment-result";
			
			isRedirect = true;
			response.sendRedirect("board-detail.do?b_idx="+comment.getB_idx());
			// 리다이렉트 시 댓글을 쓴 게시판에 다시 찾아와 댓글 게시가 이뤄져야하기 때문에 getB_idx 값을 넘겨줌
			// ex) board-detail.do?b_idx=4 (예를 들어,b_idx가 4인 주소에 뿌려줘야하기 때문)
			break;
			
		case "/board-list.do":
			page = 1;
			//user-list에서 page 변수를 사용했기 떄문에 다시 변수 지정
			String reqPage2 = request.getParameter("page");
			if(reqPage2 != null) {
				page = Integer.parseInt(reqPage2);
			}
			boardService = BoardService.getInstance();
			ArrayList<Board> boardlist = boardService.getBoards(page);
			boardcount = boardService.getBoardCount();
			Pagination pagination2 = new Pagination(page, boardcount);
			
			request.setAttribute("boardlist", boardlist);
			// setAttribute(String name, Object value) > 이름이 name인 속성 값을 value로 지정한다.
			// 이름이 boardlist인 속성 값을 boardlist로 지정한다.
			request.setAttribute("boardcount", boardcount);
			request.setAttribute("pagination", pagination2);
			
			view = "board/board-list";
			break;
			
		case "/board-comment-update.do":
			comment = new Comment();
			comment.setC_idx(Integer.parseInt(request.getParameter("c_idx")));
			comment.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
			comment.setC_content(request.getParameter("c_content"));
			
			boardService = BoardService.getInstance();
			boardService.updateComment(comment);
			ArrayList<Comment> commentList = boardService.getBoardComments(comment.getB_idx());
			
			request.setAttribute("boardcomment", commentList);
			
			view = "board/comment-list";
			break;
			
		}
		if (!isRedirect) {
			//isRedircet가 true라면 정상적으로 view+jsp가 실행되도록 설정
			RequestDispatcher rd = request.getRequestDispatcher(view + ".jsp");
			rd.forward(request, response);
		}
	} // end doPost
	
	String checkSession(HttpServletRequest request, HttpServletResponse response, String command) {
		HttpSession session = request.getSession();
		
		String[] authList = {
				"/user-list.do"
				,"/user-insert.do"
				,"/user-insert-process.do"
				,"/user-detail.do"
				,"/user-edit.do"
				,"/user-edit-process.do"
				,"/logout.do"
			};
		
		for (String item : authList) {
			if (item.equals(command)) {
				if (session.getAttribute("user") == null) {
					command = "/access-denied.do";
				}
			}
		}
		return command;
	}
}	

