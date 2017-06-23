package kr.co.bit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.board.dao.BoardDAO;
import kr.co.bit.board.vo.BoardVO;

public class ListController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		System.out.println("목록처리를 위한 메서드 호출");
		
	/*	BoardDAO dao = new BoardDAO();
		List<kr.co.bit.board.vo.BoardVO> list = dao.selectAllBoard();
		req.setAttribute("list", list);*/
		
		BoardDAO dao = new BoardDAO();
		List<BoardVO> listAll = dao.selectAllBoard();
		
		String nextNo = req.getParameter("nextNo");
		List<BoardVO> pageList = dao.selectBoard(Integer.parseInt(nextNo!=null?nextNo:"1"));
		
		System.out.println(pageList);
		
		req.setAttribute("listAll", listAll);
		req.setAttribute("list", pageList);
		req.setAttribute("size", 5);
		
		return "/jsp/board/list.jsp";  // forward할 url은 프로젝트이름까지가 /
	}

}
