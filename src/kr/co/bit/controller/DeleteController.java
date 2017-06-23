package kr.co.bit.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.board.dao.BoardDAO;
import kr.co.bit.board.vo.BoardFileVO;

public class DeleteController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int no = Integer.parseInt(request.getParameter("no"));

		BoardDAO dao = new BoardDAO();
		List<BoardFileVO> fileList = dao.selectFileByNo(no);
		
		boolean result = dao.deleteBoard(no);
		
		request.setAttribute("result", result);
		
		// 파일 삭제
		for(BoardFileVO fileVO : fileList){
			String root = request.getSession().getServletContext().getRealPath("/");
			String filePath = root + "upload";
			String fileName = fileVO.getFileSaveName();
			File file = new File(filePath,fileName);
			
			System.out.println(file.getPath()+" "+file.getName());
			
			if(file.exists()){
				if(file.delete()){
					System.out.println("삭제 완료");
				}else {
					System.out.println("삭제 실패");
				}
			}else {
				System.out.println("파일이 존재하지 않음");
			}
		}
		
		return "/jsp/board/delete.jsp";
	}

}
