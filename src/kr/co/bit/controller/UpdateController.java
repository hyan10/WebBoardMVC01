package kr.co.bit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.board.dao.BoardDAO;
import kr.co.bit.board.vo.BoardVO;

public class UpdateController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");

		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		BoardDAO dao = new BoardDAO();
		BoardVO board = new BoardVO();

		int no = Integer.parseInt(request.getParameter("no"));
		board.setNo(no);
		board.setTitle(title);
		board.setContent(content);
		board.setWriter(writer);
		
		System.out.println(dao.updateBoard(board));
		
		request.setAttribute("result", dao.updateBoard(board));
		request.setAttribute("no", no);
		
		return "/jsp/board/update.jsp";
	}

}
