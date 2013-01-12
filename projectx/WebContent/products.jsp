<%@ page import="il.ac.telhai.finalProject.Product,java.util.List"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Store's available products list</title>
<%@ include file="jqueryInclude.jsp" %>
<%@ taglib uri="WEB-INF/WebStoreJSPTags.tld" prefix="WS" %> 
</head>
<body>
	<h1>Get all Products</h1>
	<%@ include file="displayUserName.jsp" %>
	<WS:print attribute="products"/>
	<form action="/finalProjectV1.0/academic/HomePage/" method="get">
	<input id="HomePage" type="submit" value="Home Page"/>
	</form>
</body>
</html>