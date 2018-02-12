<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
	<%@ include file="jspf/header.jspf"%>
	<%@ include file="jspf/main_menu.jspf"%>
	<p><class="error">${msg}</p>
	<form:form method="post" modelAttribute="loginData">
 		Email: <form:input path="email"/><br />
 		Password: <form:password path="password"/><br />
 		<input type="submit" />
 	<form:errors></form:errors>
 	</form:form>
 
 	<%@ include file="jspf/footer.jspf"%>

</body>
</html>