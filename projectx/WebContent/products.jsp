<%@page import="il.ac.telhai.finalProject.Product"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Insert title here</title>
</head>
<body>
	<%@ page import="java.util.List" %>
	<h1>Get all Products</h1>
	<%@ include file="displayUserName.jsp" %>
	<%
		try	{
			List<?> products = (List<?>) request.getAttribute("products");
			for (Object product : products){
				out.println(product);
				out.println("</br>");
			}
		} catch (Exception e){
			
		}
	%>
	<form action="http://localhost:8080/finalProjectV1.0/academic/HomePage/" method="get">
	<input id="HomePage" name="HomePage" type="submit" value="Home Page"/>
	</form>
</body>
</html>