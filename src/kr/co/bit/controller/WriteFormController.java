package kr.co.bit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteFormController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception{
		System.out.println("글 등록 처리 메서드 호출");
		return "/jsp/board/writeForm.jsp";
	}

}
