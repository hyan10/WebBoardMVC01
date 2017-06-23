package kr.co.bit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.bit.board.dao.MemberDAO;
import kr.co.bit.board.vo.MemberVO;

public class MypageController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MemberDAO dao = new MemberDAO();

		HttpSession session = request.getSession();
		
		MemberVO member = dao.getMember(((MemberVO)session.getAttribute("member")).getId());
		
		request.setAttribute("member", member);
		
		return "/jsp/member/mypage.jsp";
	}

}
