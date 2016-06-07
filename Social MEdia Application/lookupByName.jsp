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
		      
      Connection con = null;
      Statement stmt = null;
      ResultSet rs = null;
      
      String lookupName = request.getParameter("LookupMemberName");
%>
      
     <HTML>
      <HEAD>
      <TITLE>Searching for member: <%=lookupName%></TITLE>
      </HEAD>
      <BODY BGCOLOR='#F8BBD0'>
      <link rel="stylesheet" href="lookupResults.css"	>
	  

	  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
     

<%
      try {
         Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = DriverManager.getConnection("jdbc:mysql://localhost/contacts?user=kareena&password=kapoor");
         
         stmt = con.createStatement();
         rs = stmt.executeQuery("SELECT * FROM userstable WHERE UserName='" + lookupName + "'");
		 %>
         
         <div class="row">
         
         <%
         int i = 0;
         String formName = "form";
         String buttonName = "button";
         
         while (rs.next()) {
            String picture = rs.getString("FileLocation");
            String user = rs.getString("UserName");
            String city = rs.getString("City");
            String state = rs.getString("State");
            String country = rs.getString("Country");
            String aboutUser = rs.getString("AboutMe1");
            String gender = rs.getString("Gender");
            
            formName += i;
            buttonName += i;
            %>
			<div class="col-sm-3">
			<form name='<%=formName%>'>
           <div class="row">
			<div class="card">
                <canvas class="header-bg" width="250" height="70" id="header-blur"></canvas>
                <div class="avatar">
                    <a href='details.jsp?type=2&data=<%=lookupName%>'><img class="media-object" src="<%=picture%>" alt="..."></a>
                </div>
                <div class="content">
				<h4><a href='details.jsp?type=2&data=<%=lookupName%>'><%=user%></a></h4>
                    <p><%=aboutUser%><br>
                       <%=gender%><br>
					   <%=city%>, <%=state%>, <%=country%></p>
                    <p id="addContBtn"><button type="button" id=<%=buttonName%> class="btn btn-default" onClick="addToContacts()">Add to Contact List</button></p>
					<input type='hidden' value='<%=user%>' id="hiddenUser" name='hiddenUser'>
                 </div>
  
</div>
</div>
</form>
</div>
            <%
            i++;
         }
         out.println("</div>");
      }
      catch (Exception e) {
         out.println("Could not connect to the users database.<P>");
         out.println("The error message was");
         out.println("<PRE>");
         out.println(e.getMessage());
         out.println("</PRE>");
      }
      finally {
         if (rs != null) {
            try { rs.close(); }
            catch (SQLException ignore) {}
         }
         if (stmt != null) {
            try { stmt.close(); }
            catch (SQLException ignore) {}
         }
         if (con != null) {
            try { con.close(); }
            catch (SQLException ignore) {}
         }
      }

	 
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