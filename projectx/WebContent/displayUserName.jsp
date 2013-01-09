<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Insert title here</title>
</head>
<body>
	<%
		boolean found = false;
		for (Cookie cookie : request.getCookies())	{
			if (cookie.getName().equals("UserCookie"))	{
				found = true;
				%>
				welcome <%=cookie.getValue() %>
				<%
				break;
			}		
		}
		if (! found)	{
		%>
			hello guest
		<%
		}
	%>
	<br>
</body>
</html>