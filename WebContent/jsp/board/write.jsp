<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>

<%
	boolean result = (boolean)request.getAttribute("result");
%>

<script>

<% if(result) {%>
	alert("게시글이 등록되었습니다.");
	location.href="list.do";
<% } %>
</script>