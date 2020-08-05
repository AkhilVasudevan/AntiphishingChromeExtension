package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Anti-Phishing Application</title>\n");
      out.write("        <link rel=\"shortcut icon\" href=\"star.png\" type=\"image/x-icon\" />\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <p><br><br><br><br><br><br><br></p>\n");
      out.write("        <!--div style=\"background-image: url('background.png');background-size:300px 100px; width:100%;height:100%;\"-->\n");
      out.write("    <center>\n");
      out.write("        <table border=\"1\" width=\"500px\" bordercolor='#808080' bgcolor='#F3F3F3' style=\"border-collapse: collapse\" cellpadding=\"5\" cellspacing=\"3\" >\n");
      out.write("<tr>\n");
      out.write("<td>\n");
      out.write("<form method=\"POST\" action=\"machinelearning.jsp\" name=\"regid\" id=\"regid\">\n");
      out.write("<p><center><font style='font-size: 11pt; font-family: \"Verdana, Arial\";'><b>Antiphishing Tool</b></font></center></p>\n");
      out.write("  <p><font class=\"defaultfont\"><b>Enter domain name</b></font></p>\n");
      out.write("  <p>\n");
      out.write("\t<input type=\"text\" name=\"domains\" size=\"60\">\n");
      out.write("  </p>\n");
      out.write("  <p><input type=\"submit\" value=\"Submit\" name=\"submit\" id=\"submit\">&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"reset\" value=\"Reset\" name=\"reset\"></p>\n");
      out.write("</form>\n");
      out.write("</td>\n");
      out.write("</tr>\n");
      out.write("</table>\n");
      out.write("    </center>\n");
      out.write("        <!--/div-->\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
