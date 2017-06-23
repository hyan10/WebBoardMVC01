<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>목록 조회 서비스</h2>
	<table border="1">
		<tr>
			<th>제목</th>
			<th>작성자</th>
		</tr>
		<c:forEach var="board" items="${list}"> <%-- Controller에서 등록한 requestScope 영역의 list --%>
			<tr>
				<td>${board.title}</td>
				<td>${board.writer}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>