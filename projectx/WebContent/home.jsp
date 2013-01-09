<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"	content="text/html; charset=windows-1255">
<title>Insert title here</title>
</head>
<body>
	<%
		String name="http://localhost:8080/finalProjectV1.0/images/full/002.jpg";
	%>
	<%@ include file="displayUserName.jsp" %> 
	<form action="http://localhost:8080/finalProjectV1.0/academic/sendRequestForProduct/" method="get">
	product id<input id="productId" name="productId"/>
	<input id="submitProduct" name="submitProduct" type="submit" value="Get Product"/>
	</form>
	<form action="http://localhost:8080/finalProjectV1.0/academic/sendRequestForProducts/" method="get">
	<input id="submitProducts" name="submitProducts" type="submit" value="Get All Products"/>
	</form>
	<form action="http://localhost:8080/finalProjectV1.0/academic/addProduct/" method="get">
	<input id="addProduct" name="addProduct" type="submit" value="add Product"/>
	</form>
	<form action="http://localhost:8080/finalProjectV1.0/academic/showAllMyProducts/" method="get">
	<input id="showAllMyProducts" name="showAllMyProducts" type="submit" value="Show My Products"/>
	</form>
	<form action="http://localhost:8080/finalProjectV1.0/academic/SignOut/" method="get">
	<input id="signOut" name="signOut" type="submit" value="Sign out"/>
	</form>
	<form action="http://localhost:8080/finalProjectV1.0/academic/SignIn/" method="get">
	<input id="SignIn" name="SignIn" type="submit" value="Sign in"/>
	</form>
	<form action="http://localhost:8080/finalProjectV1.0/academic/Camera/" method="get">
	<input id="Camera" name="Camera" type="submit" value="Camera"/>
	</form>
	<h1>Try</h1>
	<img src=<%=name%> alt="Big Boat">
	
	<br>
	Path: <%=name %>
</body>
</html>