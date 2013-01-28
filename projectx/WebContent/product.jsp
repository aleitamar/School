<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255" import="il.ac.telhai.finalProject.Product" errorPage="./academic/errorPage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Get Product</title>
<meta name="author" content="Ste Brennan - Code Computerlove - http://www.codecomputerlove.com/" />
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	
	<link href="lib/styles.css" type="text/css" rel="stylesheet" />
	
	<link href="lib/photoswipe.css" type="text/css" rel="stylesheet" />
	
	<script type="text/javascript" src="lib/klass.min.js"></script>
	<script type="text/javascript" src="lib/code.photoswipe-3.0.5.min.js"></script>
	
	
	<script type="text/javascript">

		(function(window, PhotoSwipe){
		
			document.addEventListener('DOMContentLoaded', function(){
			
				var
					options = {
						preventHide: true
					},
					instance = PhotoSwipe.attach( window.document.querySelectorAll('#Gallery a'), options );
					
					instance.show(0);
					
			}, false);
			
			
		}(window, window.Code.PhotoSwipe));
		
	</script>
	
</head>
<body>
	<%
		String[] paths = {  
							"http://localhost:8080/finalProjectV1.0/images/full/001.jpg",
							"http://localhost:8080/finalProjectV1.0/images/full/002.jpg",
							"http://localhost:8080/finalProjectV1.0/images/full/003.jpg",
							"http://localhost:8080/finalProjectV1.0/images/full/004.jpg"
						};
	%>
	
<div id="Header">
	<a href="http://www.codecomputerlove.com"><img src="images/codecomputerlovelogo.gif" width="230" height="48" alt="Code Computerlove" /></a>
</div>

<div id="MainContent">

	<div class="page-content">
		<h1>PhotoSwipe</h1>
	</div>
	
	<ul id="Gallery" class="gallery">
		
		<li><a href=<%=paths[0]%>><img src="images/thumb/001.jpg" alt="Image 001" /></a></li>
		<li><a href=<%=paths[1]%>><img src="images/thumb/002.jpg" alt="Image 002" /></a></li>
		<li><a href=<%=paths[2]%>><img src="images/thumb/003.jpg" alt="Image 003" /></a></li>
		<li><a href=<%=paths[3]%>><img src="images/thumb/004.jpg" alt="Image 004" /></a></li>
		
	</ul>
	
</div>	

	
<div id="Footer">
	<p><small>&copy; 2011 Code Computerlove Ltd - new media agency / digital marketing agency</small></p>

	<div id="SocialLinks">
		<a href="http://blog.codecomputerlove.com/"><img src="images/blogicon.png" width="32" height="32" alt="Blog" /></a>
		<a href="http://www.twitter.com/computerlovers"><img src="images/twittericon.png" width="32" height="32" alt="Twitter" /></a>
		<a href="http://www.facebook.com/CodeComputerlove"><img src="images/facebookicon.png" width="32" height="32" alt="Facebook" /></a>
		<a href="http://www.flickr.com/photos/codecomputerlove"><img src="images/flickricon.png" width="32" height="32" alt="Flickr" /></a>
	</div>
	
</div>
	
	<% 
		// session.getAttribute("cart")
		Product product = (Product) request.getAttribute("product");
	%>
	<%
		
		out.println(product);
	%>
</body>
</html>