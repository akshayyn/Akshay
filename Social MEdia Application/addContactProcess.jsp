<%@ page import="java.io.*" %>
<%@ page import="java.net.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<%!
	String PREFIX = "session.login";
	String ACCOUNT = PREFIX + ".account";
%>

<%	
		try
		{
			Object accountObject = session.getValue(ACCOUNT);
			
			// If no account object was put in the session, or
			// if one exists but it is not a hashtable, then
			// redirect the user to the original login page
			
			if (accountObject == null)
				throw new RuntimeException("You need to log in to use this service!");
				
			if (!(accountObject instanceof Hashtable))
				throw new RuntimeException("You need to log in to use this service!");
				
			Hashtable account = (Hashtable) accountObject;
			
			String userName = (String) account.get("name");
			int count = 0;
%>

			<HTML>
			<HEAD>
			<TITLE>Contact Added Successfully</TITLE>
			</HEAD>
			<BODY bgcolor="#651FFF">
			<CENTER>
 
<% 
      Connection con = null;
      Statement stmt = null;
                  
      String messageSent = request.getParameter("message");
      String toUser = request.getParameter("hiddenUser");
      
      java.util.Date messageDate = new java.util.Date();
      
      String sql = "INSERT INTO contacts(userName, contactName, comments, dateAdded) VALUES('" + userName + "', '" + toUser + "', '" + messageSent + "', '" + messageDate + "')";

      try
      {
         Class.forName("com.mysql.jdbc.Driver").newInstance();
		 con = DriverManager.getConnection("jdbc:mysql://localhost/contacts?user=kareena&password=kapoor");
         stmt = con.createStatement();
         
         count = stmt.executeUpdate(sql);
    %>
        
		<H2>Message was sent successfully</H2>
      	<H2><a href='loggedin.jsp'>Click Here</a> to go back</H2>
	
	<%
      }
      catch (Exception e) {
         out.println
         ("ERROR - Could not send message.<P>");
         out.println("The error message was");
         out.println("<PRE>");
         out.println(e.getMessage());
         out.println("</PRE>");
      }
      finally
      {
         if (stmt != null) {
            try { stmt.close(); }
            catch (SQLException ignore) {}
         }
         if (con != null) {
            try { con.close(); }
            catch (SQLException ignore) {}
         }
      }

	  out.println("</CENTER>");
      out.println("</BODY>");
      out.println("</HTML>");
			
	}
		catch (RuntimeException e)
		{
			out.println("<script language=\"javascript\">");
			out.println("alert(\"You need to log in to use this service!\");");
			out.println("</script>");
			
			out.println("<a href='index.html'>Click Here</a> to go to the main page.<br><br>");
			
			out.println("Or Click on the button to exit<FORM><INPUT onClick=\"javascipt:window.close()\" TYPE=\"BUTTON\" VALUE=\"Close Browser\" TITLE=\"Click here to close window\" NAME=\"CloseWindow\" STYLE=\"font-family:Verdana, Arial, Helvetica; font-size:smaller; font-weight:bold\"></FORM>");
			
			log(e.getMessage());
			return;
		}
%>
