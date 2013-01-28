<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255" errorPage="./academic/errorPage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<title>Web Store Home Page</title>
<%@ include file="jqueryInclude.jsp"%>
<%@ include file="displayUserName.jsp"%>
<%@ taglib uri="WEB-INF/WebStoreJSPTags.tld" prefix="WS"%>
</head>
<body>
	<%
		String name = "http://localhost:8080/finalProjectV1.0/images/full/002.jpg";
	%>
	<div style="color: red;" data-role="content">
		<WS:print attribute="comments" />
	</div>
	<form id="first" action="" method="get">
<<<<<<< HEAD
		<input id="productId" name="productId" value="Enter Product ID..." onfocus='$("#productId").val("");'/>
	
		<input id="submitProduct" type="submit" value="Get Product"
			onclick="$('form').attr('action', './academic/sendRequestForProduct/');"/>
		
		<input id="addProduct" type="submit" value="add Product"
		onclick="$('form').attr('action', './academic/addProduct/');"/>
=======
		<h2>Please enter product's ID:</h2>
		<input id="productId" name="productId" /> <input id="submitProduct"
			type="submit" value="Get Product"
			onclick="$('form').attr('action', '/finalProjectV1.0/academic/sendRequestForProduct/');" />

		<input id="addProduct" type="submit" value="Add Product"
			onclick="$('form').attr('action', '/finalProjectV1.0/academic/addProduct/');" />

		<input id="removeProduct" type="submit" value="Remove Product"
			onclick="$('form').attr('action', '/finalProjectV1.0/academic/removeProduct/');" />
>>>>>>> branch 'master' of https://github.com/aleitamar/School.git
	</form>

	<form id="second" action="" method="get">
		<input id="submitProducts" type="submit" value="Get All Products"
			onclick="$('form').attr('action', '/finalProjectV1.0/academic/sendRequestForProducts/');" />

		<input id="showAllMyProducts" type="submit" value="Show My Products"
			onclick="$('form').attr('action', '/finalProjectV1.0/academic/showAllMyProducts/');" />

		<input id="signOut" type="submit" value="Sign out"
			onclick="$('form').attr('action', '/finalProjectV1.0/academic/SignOut/');" />

		<input id="SignIn" type="submit" value="Sign in"
			onclick="$('form').attr('action', '/finalProjectV1.0/academic/SignIn/');" />

		<input id="Camera" type="submit" value="Camera"
			onclick="$('form').attr('action', '/finalProjectV1.0/academic/Camera/');" />
	</form>

	<h1>Try</h1>
	<img src=<%=name%> alt="Big Boat">

	<br> Path:
	<%=name%>
</body>
</html>
