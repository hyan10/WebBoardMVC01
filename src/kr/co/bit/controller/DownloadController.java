package kr.co.bit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.board.dao.BoardDAO;
import kr.co.bit.board.vo.BoardFileVO;

public class DownloadController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		InputStream is = null;
		OutputStream os = null;
		
		 BoardDAO dao = new BoardDAO();
		 BoardFileVO fileVO = dao.selectFile(Integer.parseInt(request.getParameter("no")));
		 
		 String root = request.getSession().getServletContext().getRealPath("/");
		 String savePath = root + "upload";
		 String fileName = fileVO.getFileSaveName();
		 String origFileName = fileVO.getFileOriName();
		 
		 File file = null;
		 
		 try{
		
			 try{
				 
				 file = new File(savePath, fileName); 
				 is = new FileInputStream(file);
				 
			 }catch(Exception e){
				 e.printStackTrace();
			 }
					 
			String client = request.getHeader("User-Agent");
			response.setContentType("application/octet-stream");
			//response.setHeader("Content-Description","JSP Generated Data");
			
			//IE
			if(client.indexOf("MSIE")!=-1){
				response.setHeader("Content-Disposition", "attachment; filename="+new String(origFileName.getBytes("KSC5601"),"ISO8859_1"));	
			}else {
				origFileName = new String(origFileName.getBytes("utf-8"),"iso-8859-1");
				
				response.setHeader("Content-Disposition", "attachment; filename=\""+origFileName+"\"");
			}
	
			
			os = response.getOutputStream();
			byte b[] = new byte[(int)file.length()];
			int len = 0;
			
			while((len = is.read(b))>0){
				os.write(b,0,len);
			}
			
			is.close();
			os.close();
			
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 
		return null;
	}

}
