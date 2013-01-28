<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255" errorPage="./academic/errorPage.jsp"%>
	<%
		boolean found = false;
		Cookie[] temp = request.getCookies();
		for (Cookie cookie : temp)	{
			if (cookie.getName().equals("UserCookie"))	{
			g	found = true;
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
	