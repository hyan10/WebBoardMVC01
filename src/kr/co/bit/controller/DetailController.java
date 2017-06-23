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
		
		// �Խù� ��ȸ
		BoardVO board = dao.selectByNo(no);
		
		// ÷������ ��ȸ
		List<BoardFileVO> files = dao.selectFileByNo(no);
		
		request.setAttribute("no", no);
		request.setAttribute("fileList", files);


		// form �±� �� �� ���ΰ�ħ �� ������Ű�� ���� ������ method�� �����ص� �� (post/get) 
		// list.jsp���� ���������� �������� ��쿡�� ��ȸ�� ����
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

		// �ڹ� ��ü�� ������ ���������� �߰��ؾ���
		request.setAttribute("board", board);
		
		return "/jsp/board/detail.jsp";
	}

}
