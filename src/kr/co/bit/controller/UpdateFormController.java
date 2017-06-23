package kr.co.bit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.board.dao.BoardDAO;
import kr.co.bit.board.vo.BoardVO;

public class UpdateFormController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int no = Integer.parseInt(request.getParameter("no"));

		BoardDAO dao = new BoardDAO();
		BoardVO board = dao.selectByNo(no);

		// 자바 객체를 쓰려면 공유영역에 추가해야함
		request.setAttribute("board", board);
		
		return "/jsp/board/updateForm.jsp";
	}

}
