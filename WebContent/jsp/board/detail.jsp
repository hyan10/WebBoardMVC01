<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 보기</title>
<script>
	function onAction(type){
		switch(type){
		case 'u' :
			location.href = "updateForm.do?no=${param.no}";
			break;
		case 'd' :
			if(confirm("${param.no}번 게시물을 삭제하시겠습니까?"))
				location.href = "delete.do?no=${param.no}";
			break;
		case 'l' :
			location.href = "list.do";
			break;
		}
	}
	
	function download(no){
		if(confirm("다운로드 하시겠습니까?")){
		 	location.href = "<%=request.getContextPath()%>/file/download.do?no="+no;
		}
	}
	
	
</script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css"/>
</head>
<body>
<!-- html5 -->
	<header>
		<!-- xml, include, forward는 /가 Mission-Web/ 까지를 의미 -->
		<jsp:include page="/jsp/include/topMenu.jsp"/>
	</header>
	
	<section>
		<div align="center">
		<br/>
		<hr width="100%"/>
		<h2>상세페이지</h2>
		<hr width="100%"/>
		<br/>
		
		<table width="100%" border="1">
		
		<tr>
			<th width="25%">번호</th>
			<td>${param.no}</td>
		</tr>
		
		<tr>
			<th >제목</th>
			<td><c:out value="${board.title}"/></td>
		</tr>

		<tr>
			<th >작성자</th>
			<td>${board.writer}</td>
		</tr>
		
		<tr>
			<th>내용</th>
			<td><c:out value="${board.content}"/></td>
		</tr>
		
		<tr>
			<th>조회수</th>
			<td>${board.viewCnt}</td>
		</tr>
		
		<tr>
			<th>등록일</th>
			<td>${board.regDate}</td>
		</tr>
		
		<tr>
			<th>첨부파일</th>
			<td>
			<c:forEach var="file" items="${fileList}">
			<!-- <%=request.getContextPath()%>/upload/${file.fileSaveName} -->
				<a href="javascript:download(${file.no})">${file.fileOriName}</a>&nbsp;(${file.fileSize} bytes)<br/>  <!-- .이 getter 역할 -->
			</c:forEach>
			</td>
		</tr>
		
		</table>
		
		<br/><br/>
		<c:if test="${member.name eq board.writer }">
			<input type="button" value="수정" onclick="onAction('u')"/>&nbsp;&nbsp;
		</c:if>
		<c:if test="${member.name eq board.writer or member.type eq 'S'}">
			<input type="button" value="삭제" onclick="onAction('d')" />&nbsp;&nbsp;
		</c:if>
		<input type="button" value="목록" onclick="onAction('l')" />
		</div>
	</section>
	
	<footer>
	<!-- action include 써도 된다.  -->
		<%@ include file="/jsp/include/bottom.jsp" %>
	</footer>
</body>
</html>