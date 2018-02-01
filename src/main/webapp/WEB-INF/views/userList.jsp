<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello</title>
</head>
<body>
  <table border="1" style="border-collapse:collapse;width:50%;height: 34px;text-align: center;" >
	  <c:forEach items="${userList}" var="user">
	  	  <tr>
	  	  	  <td>${user.id}</td>
			  <td>${user.uname}</td>
			  <td>${user.pwd}</td>
		  </tr>
	  
	  </c:forEach>
  </table>
</body>
</html>