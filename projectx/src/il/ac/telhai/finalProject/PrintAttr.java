package il.ac.telhai.finalProject;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.*;
import java.util.List;

public class PrintAttr extends SimpleTagSupport {

	private String attrName = "";
	private String scope = "request";
	
	public void setAttribute(String newAttrName) {
		attrName = newAttrName;
	}

	public void setScope(String newScope) {
		scope = newScope;
	}
	
	public void doTag() throws JspException, IOException {
		try {
			JspWriter out = getJspContext().getOut();
			PageContext pageContext = (PageContext) getJspContext();
			ServletRequest request = pageContext.getRequest();
			Object attr = null;
			
			if (scope.equals("session")) {
				attr = (Object) pageContext.getSession().getAttribute(attrName);
			} else if (scope.equals("request")) {
				attr = (Object) request.getAttribute(attrName);
			}
			
			if (attr == null) {
				return;
			}
			
			if (attr instanceof List) {
				for (Object item : (List<?>) attr) {
					if (item == null) {
						continue;
					}
					out.println(item + "<br>");
				}
			} else {
				out.println(attr + "<br>");
			}

		} catch (NullPointerException err) {
			/* do nothing */
		} catch (Exception err) {
			/* TODO: add exception handling here */
		}
	}
}
