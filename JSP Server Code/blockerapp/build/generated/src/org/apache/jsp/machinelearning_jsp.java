package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import dictionary.SpellCheck;
import java.sql.*;
import java.net.*;
import java.io.*;
import java.util.*;
import dictionary.MySpellChecker;

public final class machinelearning_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


             String str,ts,domain;
             int digit=0,length;
             int subdomain;
             boolean tld,ips,typosquat;
             String protocol;
             void digitCount()
             {
                 for(int i=0;i<ts.length();i++)
                 {
                     if(Character.isDigit(ts.charAt(i)))
                     {
                         digit++;
                     }
                 }
             }
             void tldUse()
             {
                 tld=false;
                 if(ts.endsWith(".com") || ts.endsWith(".org") || ts.endsWith(".edu") || ts.endsWith(".gov") || ts.endsWith(".uk") || ts.endsWith(".net") || ts.endsWith(".ca") || ts.endsWith(".de") || ts.endsWith(".jp") || ts.endsWith(".fr") || ts.endsWith(".au") || ts.endsWith(".us") || ts.endsWith(".ru") || ts.endsWith(".ch") || ts.endsWith(".it") || ts.endsWith(".nl")|| ts.endsWith(".se")|| ts.endsWith(".no")|| ts.endsWith(".es")|| ts.endsWith(".mil"))
                 {
                     tld=true;
                 }
             }
             void subDomain()
             {
                 subdomain=-1;
                 for(int i=0;i<ts.length();i++)
                 {
                     if(ts.charAt(i)=='.')
                     {
                         subdomain++;
                     }
                 }
             }
             void findIp()
             {
                 try
                 {
                     InetAddress ip=InetAddress.getByName(new URL(str).getHost());
                     ips=false;
                 }
                 catch(Exception e)
                 {
                     ips=true;
                 }
             }
             void checkTyposquatted()
             {
                SpellCheck.spellCheck(ts);
             }
             void protocolType()
             {
                 String ps="";
                 try
                 {
                    URL url=new URL(str);
                    ps=url.getProtocol();
                 }
                 catch(MalformedURLException e)
                 {
                     
                 }
                 finally
                 {
                     if(ps == "https")
                     {
                         protocol=ps;
                     }
                     else
                     {
                         protocol="http";    
                     }
                 }
             }
             void urlProcessing()
             {
                 /*url based*/
                    /*digitcount*/
                       digitCount(); 
                    /*length of url*/
                        length=ts.length();
                    /*typosquatted*/
                        checkTyposquatted();
                    /*tld is commonly used*/
                        tldUse();
                    /*no of subdomains*/
                        subDomain();
                    /*registrant hidden*/
                        findIp();
                 /*domain based*/
                 /*page based*/
                    protocolType();
                 /*content based*/
                 /*insert in db*/
             }
             boolean urlClassify()
             {
                 boolean classx=false;
                 /*classify algorithm*/
                 return classx;
             }
         
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
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Anti-Phishing Application</title>\n");
      out.write("         <link rel=\"shortcut icon\" href=\"star.png\" type=\"image/x-icon\" />\n");
      out.write("         ");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("       ");

           /*Get url*/
           str=request.getParameter("domains");
           try
           {
               /*domain name*/
               URI uri = new URI(str);
               domain = uri.getHost();
               ts=domain.startsWith("www.")?domain.substring(4):domain;
               /*check for db*/
               Class.forName("org.apache.derby.jdbc.ClientDriver");
               Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/phishingdataset","akhil","akhil@1997");
               Statement stmt=con.createStatement();
               Statement stmt1=con.createStatement();
               ResultSet rs=stmt.executeQuery("select * from domaindataset where domain='"+ts+"' and class='legitimate'");  
               ResultSet rs1=stmt1.executeQuery("select * from domaindataset where domain='"+ts+"' and class='spoofed'");
               if(rs.next())
               {
                   /*legitimate*/
                    String site = new String(str);
                    response.setStatus(response.SC_MOVED_TEMPORARILY);
                    response.setHeader("Location", site); 
               }
               else if(rs1.next())
               {
                   /*spoofed*/
                    String site = new String("redirect.jsp");
                    response.setStatus(response.SC_MOVED_TEMPORARILY);
                    response.setHeader("Location", site);
               }
               else
               {
                    int status=0;
                    /*call for url values*/
                    urlProcessing();
                    /*display attribute values*/
                    out.println("<table border='1'>");
                    out.println("<tr><th>ATTRIBUTE</th><th>VALUE</th></tr>");
                    out.println("<tr><td>Domain</td><td>"+ts+"</td></tr>");
                    out.println("<tr><td>TLD Commonly used?</td><td>"+tld+"</td></tr>");
                    out.println("<tr><td>No. of subdomains</td><td>"+subdomain+"</td></tr>");
                    out.println("<tr><td>Length of domain</td><td>"+length+"</td></tr>");
                    out.println("<tr><td>No. of Digits in domain</td><td>"+digit+"</td></tr>");
                    out.println("<tr><td>Is the URL typosquatted?</td><td>"+typosquat+"</td></tr>");
                    out.println("<tr><td>Age of domain</td><td>&nbsp;</td></tr>");
                    out.println("<tr><td>Is the registrant ip hidden?</td><td>"+ips+"</td></tr>");
                    out.println("<tr><td>Is the form available in the URL?</td><td>&nbsp;</td></tr>");
                    out.println("<tr><td>Is the form has authentication data?</td><td>&nbsp;</td></tr>");
                    out.println("<tr><td>Protocol used</td><td>"+protocol+"</td></tr>");
                    out.println("<tr><td>Type of URL</td><td>&nbsp;</td></tr>");
                    out.println("</table>");
                    /*call machine learning*/
                    urlClassify();
               }
               rs.close();
               stmt.close();
               rs1.close();
               stmt1.close();
               con.close();
           }
           catch(Exception e)
           {
               out.println(e);
           }
       
      out.write("\n");
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
