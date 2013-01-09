package il.ac.telhai.finalProject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieCounterServlet {
	public static final String COOKIE_NAME = "personalCounter";
	public void doGet(HttpServletRequest request, HttpServletResponse result)
	throws ServletException, IOException {
		Cookie visitorCookie = getCounterCookie(request, result);
		result.setContentType("text/html");
		PrintWriter out = result.getWriter();
		out.println("<HEAD><TITLE> " + "CokieCounterServlet"
				+ "</TITLE></HEAD><BODY>");
		out.println("<h1>CookieCounterServlet</h1>");
		out.println("You have it this page " + visitorCookie.getValue()
				+ " times<p>");
		out.println("</BODY>");
		out.close();
	}
	private Cookie getCounterCookie(HttpServletRequest request,
			HttpServletResponse result) {
		Cookie[] cookies = request.getCookies();
		Cookie cookit = null;
		if (null != cookies)
		{
			for (int i = 0; i < cookies.length; i++)
			{
				if (cookies[i].getName().equals(COOKIE_NAME))
				{
					cookit = (Cookie) cookies[i].clone();
					break;
				}
			}
		}
		int counter = 1;
		if (cookit != null)
		{
			String sval = cookit.getValue();
			counter = Integer.parseInt(sval);
			counter++;
		}
		else
		{
			cookit = new Cookie(COOKIE_NAME, "0");
		}
		cookit.setValue(String.valueOf(counter));
		result.addCookie(cookit);
		return cookit;
	}
}
