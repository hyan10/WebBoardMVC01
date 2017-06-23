package kr.co.bit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.board.dao.MemberDAO;
import kr.co.bit.board.vo.MemberVO;

public class JoinController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MemberDAO dao = new MemberDAO();
		
		// (String id, String name, String password, String email_id, String email_domain, String post,
		//		String tel1, String tel2, String tel3, String basic_addr, String detail_addr)
		MemberVO member = new MemberVO (
				request.getParameter("id"),
				request.getParameter("name"),
				request.getParameter("pw"),
				request.getParameter("email_id"),
				request.getParameter("email_domain")
				, request.getParameter("post")
				, request.getParameter("tel1")
				, request.getParameter("tel2")
				, request.getParameter("tel3")
				, request.getParameter("basic_addr")
				, request.getParameter("detail_addr")
				);
		
		boolean result = dao.joinMember(member);
		
		request.setAttribute("result", result);
		
		return "/jsp/member/join.jsp";
	}

}
