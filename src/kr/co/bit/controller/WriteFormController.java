package kr.co.bit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteFormController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception{
		System.out.println("�� ��� ó�� �޼��� ȣ��");
		return "/jsp/board/writeForm.jsp";
	}

}
