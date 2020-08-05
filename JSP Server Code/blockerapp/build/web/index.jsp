<%-- 
    Document   : index
    Created on : 1 Mar, 2019, 1:50:15 PM
    Author     : Akhil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Anti-Phishing Application</title>
        <link rel="shortcut icon" href="star.png" type="image/x-icon" />
    </head>
    <body>
        <p><br><br><br><br><br><br><br></p>
        <!--div style="background-image: url('background.png');background-size:300px 100px; width:100%;height:100%;"-->
    <center>
        <table border="1" width="500px" bordercolor='#808080' bgcolor='#F3F3F3' style="border-collapse: collapse" cellpadding="5" cellspacing="3" >
<tr>
<td>
<form method="POST" action="machinelearning.jsp" name="regid" id="regid">
<p><center><font style='font-size: 11pt; font-family: "Verdana, Arial";'><b>Antiphishing Tool</b></font></center></p>
  <p><font class="defaultfont"><b>Enter domain name</b></font></p>
  <p>
	<input type="text" name="domains" size="60">
  </p>
  <p><input type="submit" value="Submit" name="submit" id="submit">&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="Reset" name="reset"></p>
</form>
</td>
</tr>
</table>
    </center>
        <!--/div-->
    </body>
</html>
