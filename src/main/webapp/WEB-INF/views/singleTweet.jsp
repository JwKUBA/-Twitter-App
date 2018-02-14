<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Twitter</title>
</head>
<body>
	<%@ include file = "jspf/header.jspf" %>
	<%@ include file = "jspf/main_menu.jspf" %>
	<%@ include file = "jspf/add_comment.jspf" %>
	
	${msg}
	<h3>Tweet Details</h3>
	Text:${tweet.text}</br></br>
	Author:${tweet.user.username}</br>
	Created:${tweet.created}</br>
	
	
	<h3>Comments</h3>
	<c:forEach items="${comments}" var="comment">
	<p>${comment.text}</p>
	<p>${comment.created}</p>
	<p>${comment.user.username}</p>
	</c:forEach>
	<%@ include file = "jspf/footer.jspf" %>
	
</body>
</html>