<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255" errorPage="./academic/errorPage.jsp"%>
	<%
		boolean found = false;
		Cookie[] temp = request.getCookies();
		for (Cookie cookie : temp)	{
			if (cookie.getName().equals("UserCookie"))	{
			g	found = true;
				%>
				<h2>welcome <%=cookie.getValue() %></h2>
				<%
				break;
			}		
		}
		if (! found)	{
		%>
			<h2>hello guest</h2>
		<%
		}
	%>
	<br>
	
