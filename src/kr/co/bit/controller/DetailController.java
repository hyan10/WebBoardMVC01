package kr.co.bit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.bit.board.dao.BoardDAO;
import kr.co.bit.board.vo.BoardFileVO;
import kr.co.bit.board.vo.BoardVO;
import kr.co.bit.board.vo.MemberVO;

public class DetailController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int no = Integer.parseInt(request.getParameter("no"));

		BoardDAO dao = new BoardDAO();
		
		// 게시물 조회
		BoardVO board = dao.selectByNo(no);
		
		// 첨부파일 조회
		List<BoardFileVO> files = dao.selectFileByNo(no);
		
		request.setAttribute("no", no);
		request.setAttribute("fileList", files);


		// form 태그 쓸 때 새로고침 시 증가시키고 싶지 않으면 method로 구분해도 됨 (post/get) 
		// list.jsp에서 상세페이지로 접속했을 경우에만 조회수 증가
	/*
		String type = request.getParameter("type");
		if(type != null && type.equals("list")){
			dao.updateViewCnt(no);
		}
	*/

		HttpSession session = request.getSession();
		
		if(!((MemberVO)session.getAttribute("member")).getName().equals(board.getWriter())){
			 board.setViewCnt(board.getViewCnt()+1);
			 dao.updateViewCnt(board);
		}	

		// 자바 객체를 쓰려면 공유영역에 추가해야함
		request.setAttribute("board", board);
		
		return "/jsp/board/detail.jsp";
	}

}
