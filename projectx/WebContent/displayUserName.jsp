<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
	<%
		boolean found = false;
		Cookie[] temp = request.getCookies();
		for (Cookie cookie : temp)	{
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
	