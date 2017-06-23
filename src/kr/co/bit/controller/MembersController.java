package kr.co.bit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.board.dao.MemberDAO;
import kr.co.bit.board.vo.MemberVO;

public class MembersController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		MemberDAO dao = new MemberDAO();
		List<MemberVO> members = dao.getMembers();
		
		request.setAttribute("members", members);
		
		return "/jsp/member/members.jsp";
	}

}
