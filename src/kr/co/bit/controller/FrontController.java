package kr.co.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {
	
	private HandlerMapping mappings = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		String configName = config.getInitParameter("configName");
		mappings = new HandlerMapping(configName);
	}

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
			// 요청 처리를 위해 req, res 넘겨줌
			Controller controller = mappings.getController(uri);
			String callPage = controller.handleRequest(req, res);
			
			if(callPage!=null){
				RequestDispatcher dispatcher = req.getRequestDispatcher(callPage);
				dispatcher.forward(req, res);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
	}

	
}
