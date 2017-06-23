<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 등록</title>
<script src="<%=request.getContextPath()%>/jsp/js/checkForm.js"></script>
<script>
	function doList(){
		location.href = "/board/list.do";
	}
	
	function doWirte(){
		var w = document.writeForm;
		
		if(isNull(w.title,'제목을 입력하세요.')){
			return false;
		};
		
		if(isNull(w.content,'내용을 입력하세요.')){
			return false;
		};
		
		return true;
	}
	
	function checkFile(){
		var w = document.writeForm;
		// 파일 확장자 체크
		if(checkExt(w.attachfile1)){
			alert('해당 파일은 업로드할 수 없습니다.');
			w.attachfile1.value = "";
		};

		if(checkExt(w.attachfile2)){
			alert('해당 파일은 업로드할 수 없습니다.');
			w.attachfile2.value = "";
		};
	}
	
</script>
</head>
<body>
	<div align="center">
	
	<br/>
	<hr width="80%"/>
		<h2>게시글 등록폼</h2>
	<hr width="80%"/>
	<br/>
	
	<form action="<%=request.getContextPath()%>/board/write.do" method="post" onsubmit="return doWirte()" name="writeForm" enctype="multipart/form-data">
		<input type="hidden" name="writer" value="${sessionScope.member.name}"/>
	
		<table width="80%" border="1">
			<tr>
				<th width="23%">제목</th>
				<td><input type="text" name="title" size="55"/></td> 
			</tr>
			
			<tr>
				<th>작성자</th>
				<td>${sessionScope.member.name}</td>
				<%-- <input type="text" name="writer" size="55"  value="${param.id}"/> --%>
			</tr>
			
			<tr>
				<th>내용</th>
				<td>
					<textarea rows="7" cols="55" name="content"></textarea>
				</td>
			</tr>
			
			<tr>
				<td>첨부파일</td>
				<td>
					<input type="file" name="attachfile1" size="40" onchange="checkFile()"/><br/>
					<input type="file" name="attachfile2" size="40" onchange="checkFile()"/><br/>
				</td>
			</tr>
		</table>
		<br/><br/>
		<input type="submit" value="등록" />&nbsp;&nbsp;
		<input type="button" value="목록" onclick="doList()" />
	</form>
	
	</div>
</body>
</html>