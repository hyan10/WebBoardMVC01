package kr.co.bit.controller;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import kr.co.bit.board.dao.BoardDAO;
import kr.co.bit.board.vo.BoardFileVO;
import kr.co.bit.board.vo.BoardVO;
import kr.co.bit.util.BitFileNamePolicy;

public class WriteController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 저장할 웹서버 위치
		String saveFolder = "C:\\hy\\web-workspace\\Mission-MVC01\\WebContent\\upload";
		
		MultipartRequest multi = new MultipartRequest(request, saveFolder, 1024*1024*3,
														"utf-8", new BitFileNamePolicy());
		
		String title = multi.getParameter("title");
		String writer = multi.getParameter("writer");
		String content = multi.getParameter("content");
		
		BoardDAO dao = new BoardDAO();
		
		// 0. 등록할 게시글의 sequence를 추출
		int no = dao.selectNo();
		
		// 1. 게시물 저장 (t_board)
		BoardVO board = new BoardVO();
		board.setNo(no);
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		System.out.println(board);	
		boolean result = dao.insertBoard(board);
		
		// 2. 첨부파일 저장 (t_board_file)
		if(result){
				
			Enumeration files = multi.getFileNames();
			
			while(files.hasMoreElements()){
				String fileName = (String)files.nextElement();
				System.out.println(fileName);
				
				File f = multi.getFile(fileName);
				System.out.println(fileName+": "+f);
				
				if(f!=null){
					
					String fileOriName = multi.getOriginalFileName(fileName);
					String fileSaveName = multi.getFilesystemName(fileName);
					int fileSize = (int)f.length();
					
					BoardFileVO fileVO = new BoardFileVO();
					fileVO.setBoardNo(no);
					fileVO.setFileOriName(fileOriName);
					fileVO.setFileSaveName(fileSaveName);
					fileVO.setFileSize(fileSize);
					
					result = dao.insertFile(fileVO);
						
				}
			}
		}
		
		request.setAttribute("result", result);
		
		return "/jsp/board/write.jsp";
	}

}
