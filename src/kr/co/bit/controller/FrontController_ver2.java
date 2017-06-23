package kr.co.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController_ver2 extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("서비스 호출");
		
		String uri = req.getRequestURI();
		System.out.println("요청 uri: "+uri);

		String project = req.getContextPath();  // 프로젝트 이름
		uri = uri.substring(project.length());
//		uri.replace(project, "");
		
		
		System.out.println("요청 uri: "+uri);
		
		try {
			
			String callPage = "";
			Controller controller = null;
			
			switch(uri){
				case "/list.do": //"/Mission-MVC01/list.do":
					System.out.println("목록처리");
					controller = new ListController();
					break;
				case "/write.do":
					System.out.println("글 등록 처리");
					controller = new WriteFormController();
					break;
			}
			
			// 요청 처리를 위해 req, res 넘겨줌
			callPage = controller.handleRequest(req, res);
			RequestDispatcher dispatcher = req.getRequestDispatcher(callPage);
			dispatcher.forward(req, res);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
	}

	
}
