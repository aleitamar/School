<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"  errorPage="./academic/errorPage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login Page</title>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<%@ include file="jqueryInclude.jsp" %>
<script type="text/javascript"
	src="http://crypto-js.googlecode.com/svn/tags/3.1/build/rollups/md5.js"></script>
</head>
<body>
	<!-- div id="loadingMask" style="width: 100%; height: 100%; position: fixed; background: #fff;">Loading...</div> -->
	<form
		action="http://localhost:8080/finalProjectV1.0/academic/UserLogin/"
		method="post">
		<div data-role="page" id="home">
			<div data-role="header">
				<h1>Login Page</h1>
			</div>
			<div data-role="fieldcontain">
				<label for="name">User-Name:</label> <input type="text" name="User"
					id="User" value="" />
			</div>
			<div data-role="fieldcontain">
				<label for="name">Password:</label> <input type="password"
					id="Password" value="" />
				<input type="hidden"
					name="Password" id="PasswordHash" value="" />
			</div>
			<div data-role="content">
				<p>To login please enter your user-name and password</p>
				<script>
				$("form").submit(function() {
						$("#PasswordHash").val(CryptoJS.MD5($("#Password").val()))
					    return true;
					});
				

				</script>
				<p>
					<input type="submit" value="login" data-transition="pop"
						data-rel="dialog" data-role="button"/>
				</p>
			</div>
			<div style="color: red;" data-role="content">
				<%
				    String comments =(String) request.getAttribute("comments");
					if (comments == null) {
						comments = "";
					}
				%>
				<p><%=comments%></p>
			</div>
			<div data-role="footer">
				<h4>WebStore</h4>
			</div>
		</div>
	</form>

	<%
	    Cookie[] cookies = request.getCookies();
						if (cookies != null) {
						    for (Cookie cookie : request.getCookies())	{
											if (cookie.getName().equals("UserCookie"))	{
	%>
	<script>$("#User").val("<%=cookie.getValue()%>")
	</script>
	<%
	    break;
			    }
			}
	    }
	%>
</body>
=======
<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login Page</title>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<%@ include file="jqueryInclude.jsp" %>
<script type="text/javascript"
	src="http://crypto-js.googlecode.com/svn/tags/3.1/build/rollups/md5.js"></script>
	<%@ taglib uri="WEB-INF/WebStoreJSPTags.tld" prefix="WS" %> 
</head>
<body>
	<!-- div id="loadingMask" style="width: 100%; height: 100%; position: fixed; background: #fff;">Loading...</div> -->
	<form
		action="http://localhost:8080/finalProjectV1.0/academic/UserLogin/"
		method="post">
		<div data-role="page" id="home">
			<div data-role="header">
				<h1>Login Page</h1>
			</div>
			<div data-role="fieldcontain">
				<label for="name">User-Name:</label> <input type="text" name="User"
					id="User" value="" />
			</div>
			<div data-role="fieldcontain">
				<label for="name">Password:</label> <input type="password"
					id="Password" value="" />
				<input type="hidden"
					name="Password" id="PasswordHash" value="" />
			</div>
			<div data-role="content">
				<p>To login please enter your user-name and password</p>
				<script>
				$("form").submit(function() {
						$("#PasswordHash").val(CryptoJS.MD5($("#Password").val()));
					    return true;
					});
				

				</script>
				<p>
					<input type="submit" value="login" data-transition="pop"
						data-rel="dialog" data-role="button"/>
				</p>
			</div>
			<div style="color: red;" data-role="content">
			
			<WS:print attribute="comments"/>
			
			</div>
			<div data-role="footer">
				<h4>WebStore</h4>
			</div>
		</div>
	</form>

	<%
	    Cookie[] cookies = request.getCookies();
						if (cookies != null) {
						    for (Cookie cookie : request.getCookies())	{
											if (cookie.getName().equals("UserCookie"))	{
	%>
	<script>$("#User").val("<%=cookie.getValue()%>")
	</script>
	<%
	    break;
			    }
			}
	    }
	%>
</body>
>>>>>>> branch 'master' of https://github.com/aleitamar/School.git
</html>
