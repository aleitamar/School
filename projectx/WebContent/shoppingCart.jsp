<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255" import="il.ac.telhai.finalProject.*" errorPage="./academic/errorPage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>My shopping cart</title>
<%@ taglib uri="WEB-INF/WebStoreJSPTags.tld" prefix="WS" %> 
<%@ include file="jqueryInclude.jsp" %>
</head>
<body>
<WS:print scope="session" attribute="shoppingCart"/>
</body>
</html>
