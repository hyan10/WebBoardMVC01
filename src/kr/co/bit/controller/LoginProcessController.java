package kr.co.bit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.bit.board.dao.MemberDAO;
import kr.co.bit.board.vo.MemberVO;

public class LoginProcessController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String password = request.getParameter("pw");
		String msg = "";
		String url = "";
		
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPassword(password);
		
		MemberDAO dao = new MemberDAO();
		MemberVO result = dao.loginMember(member);
		
		if(result==null){
			msg = "아이디 또는 패스워드가 잘못되었습니다.";	
			url = request.getContextPath()+"/member/loginForm.do";
			
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("member", result);

			switch(result.getType()){
				case "S":
					msg = "관리자님 환영합니다.";
					break;
				case "U":
					msg = result.getName()+"("+result.getId()+")님 환영합니다.";
					break;
			}
			
			String detail_url = request.getParameter("url");
			
			System.out.println(detail_url);
			
			if(detail_url.equals("")){
				url = request.getContextPath();		
			}else if(detail_url.contains("detail")){
				String no = (String)request.getParameter("no");
				url = request.getContextPath()+"/"+detail_url+".do?no="+no;
			}
			
			System.out.println(url);
			
		}
		
//		System.out.println(msg);
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return "/jsp/member/login.jsp";
	}

}
