<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error Page</title>
<%@ include file="jqueryInclude.jsp" %>
</head>
<body>
	<%-- Log error on server side --%>
	<%
		if (exception != null) {
			System.err.println("Error : " + exception.getMessage());
		}
	%>

	<%-- Display generic error to client --%>
	<b>An error occurred !</b>
</body>
</html>
