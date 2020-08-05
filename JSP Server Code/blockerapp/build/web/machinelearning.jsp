<%-- 
    Document   : machinelearning
    Created on : 29 Jan, 2019, 7:08:47 PM
    Author     : Akhil & Munieshvar
--%>

<%@page import="dictionary.SpellCheck"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*,java.net.*,java.io.*,java.util.*,dictionary.MySpellChecker"%>
<%@page import="org.jsoup.Jsoup,org.jsoup.nodes.Document,org.jsoup.nodes.Element,org.jsoup.select.Elements"%>
<%@page import="java.text.ParseException,java.text.SimpleDateFormat,java.util.Date;"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Anti-Phishing Application</title>
         <link rel="shortcut icon" href="star.png" type="image/x-icon" />
         <%!
             String str,ts,domain;
             int digit=0,length;
             long age;
             int subdomain;
             boolean tld,ips,typosquat,sform;
             String protocol,classes;
             /*to find the substring*/
            int isSubstring(String s1, String s2) 
            { 
                int M = s1.length(); 
                int N = s2.length(); 
                for (int i = 0; i <= N - M; i++) 
                { 
                    int j; 
                    for (j = 0; j < M; j++){ 
                            if (s2.charAt(i + j) != s1.charAt(j)) 
                            {    break; }
                    }
                    if (j == M) 
                    {    return i;} 
                } 
                return -1; 
            }
            /*count digit*/
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
             /*age checker*/
             void checkAge()
             {
                String url="https://www.whois.com/whois/"+ts;
                String s1,s2,s3;
                s2="Creation Date:";
                s3="Domain record activated:";
                SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat formatter1=new SimpleDateFormat("dd-MMM-yyyy");
                Date dates=new Date();
                try{
                    Document document= Jsoup.connect(url).get(); 
                    Element formElement = document.getElementById("registrarData");
                    s1=formElement.text();
                    int res=isSubstring(s2,s1);
                    if(res==-1)
                    {
                        age=0;
                    }
                    else
                    {
                        String dateInString="";
                        for(int i=res+15;i<res+25;i++)
                        {
                            dateInString+=s1.charAt(i);
                        }
                        try{
                            Date date=formatter.parse(dateInString);
                            long diffInMillies = Math.abs(dates.getTime() - date.getTime());
                            age = Math.abs(diffInMillies/(10000*60*60*24));
                        }
                        catch(ParseException ex)
                        {
                            age=0;
                        }
                    }
                }
                catch(IOException e)
                {
                    age=0;
                }
                catch(NullPointerException e)
                {
                    try{
                        Document document= Jsoup.connect(url).get();
                        Element formElement = document.getElementById("registryData");
                        s1=formElement.text();
                        int res=isSubstring(s3,s1);
                        if(res==-1)
                        {
                            age=0;
                        }
                        else
                        {
                            String dateInString="";
                            for(int i=res+28;i<res+40;i++)
                            {
                                dateInString+=s1.charAt(i);
                            }
                            try{
                                Date date=formatter1.parse(dateInString);
                                long diffInMillies = Math.abs(dates.getTime() - date.getTime());
                                age = Math.abs(diffInMillies/(10000*60*60*24));
                            }
                            catch(ParseException ex)
                            {
                                age=0;
                            }
                        }
                    }
                    catch(IOException ex)
                    {
                        age=0;
                    }
                }
             }
             /*tld commonly used*/
             void tldUse()
             {
                 tld=false;
                 if(ts.endsWith(".com") || ts.endsWith(".org") || ts.endsWith(".edu") || ts.endsWith(".gov") || ts.endsWith(".uk") || ts.endsWith(".net") || ts.endsWith(".ca") || ts.endsWith(".de") || ts.endsWith(".jp") || ts.endsWith(".fr") || ts.endsWith(".au") || ts.endsWith(".us") || ts.endsWith(".ru") || ts.endsWith(".ch") || ts.endsWith(".it") || ts.endsWith(".nl")|| ts.endsWith(".se")|| ts.endsWith(".no")|| ts.endsWith(".es")|| ts.endsWith(".mil"))
                 {
                     tld=true;
                 }
             }
             /*count subdomains*/
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
             /*to find the ip is hidden or not*/
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
             /*check if typosquatted or not*/
             void checkTyposquatted()
             {
                if(SpellCheck.spellCheck(ts))
                {
                    typosquat=true;
                }
                else
                {
                    typosquat=false;
                }
             }
             /*check for the protocol*/
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
             /*check for form in html*/
             void checkForm()
             {
                 try
                 {
                    sform=false;
                    Document doc = Jsoup.connect(str).get();
                    Element formElement = doc.getElementById("form");
                    Elements inputElements = formElement.getElementsByTag("input");
                    for(Element elem:inputElements)
                    {
                        String psd=elem.attr("type");
                        if(psd.equals("password"))
                        {
                            sform=true;
                            break;
                        }
                    }
                 }
                 catch(Exception e)
                 {}
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
                 /*domain based*/
                    findIp();
                 /*page based*/
                    protocolType();
                 /*content based*/
                     checkForm();
                     checkAge();
             }
         %>
    </head>
    <body>
       <%
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
                    //int status=0;
                    /*call for url values*/
                    urlProcessing();
                    /*classifier algorithm*/
                    if(ips)
                    {
                        classes="spoofed";
                        Statement statement=con.createStatement();
                        statement.executeUpdate("insert into domaindataset(class,domain) values('"+classes+"','"+ts+"')");
                        statement.close();
                        String site = new String("redirect.jsp");
                        response.setStatus(response.SC_MOVED_TEMPORARILY);
                        response.setHeader("Location", site);
                    }
                    else if(protocol.equalsIgnoreCase("http"))
                    {
                        if((typosquat && !tld && subdomain>2 && digit>4)||(digit>4&& age<10 && sform))
                        {
                            classes="spoofed";
                            Statement statement=con.createStatement();
                            statement.executeUpdate("insert into domaindataset(class,domain) values('"+classes+"','"+ts+"')");
                            statement.close();
                            String site = new String("redirect.jsp");
                            response.setStatus(response.SC_MOVED_TEMPORARILY);
                            response.setHeader("Location", site);
                        }
                        else
                        {
                            classes="legitimate";
                            Statement statement=con.createStatement();
                            statement.executeUpdate("insert into domaindataset(class,domain) values('"+classes+"','"+ts+"')");
                            statement.close();
                            String site = new String(str);
                            response.setStatus(response.SC_MOVED_TEMPORARILY);
                            response.setHeader("Location", site);
                        }
                    }
                    else
                    {
                        classes="legitimate";
                        Statement statement=con.createStatement();
                        statement.executeUpdate("insert into domaindataset(class,domain) values('"+classes+"','"+ts+"')");
                        statement.close();
                        String site = new String(str);
                        response.setStatus(response.SC_MOVED_TEMPORARILY);
                        response.setHeader("Location", site);
                    }
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
       %>
    </body>
</html>
