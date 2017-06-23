package kr.co.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController_ver1 extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("���� ȣ��");
		
		String uri = req.getRequestURI();
		System.out.println("��û uri: "+uri);

		String project = req.getContextPath();  // ������Ʈ �̸�
		uri = uri.substring(project.length());
//		uri.replace(project, "");
		
		
		System.out.println("��û uri: "+uri);
		
		try {
			
			String callPage = "";
			
			switch(uri){
				case "/list.do": //"/Mission-MVC01/list.do":
					System.out.println("���ó��");
					ListController list = new ListController();
					// ��û ó���� ���� req, res �Ѱ���
					callPage = list.handleRequest(req, res);
					break;
				case "/write.do":
					System.out.println("�� ��� ó��");
					WriteFormController writeForm = new WriteFormController();
					callPage = writeForm.handleRequest(req, res);
					break;
			}
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(callPage);
			dispatcher.forward(req, res);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
	}

	
}
