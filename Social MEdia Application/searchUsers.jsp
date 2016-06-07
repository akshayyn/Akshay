<%@page import="java.io.*" %>
<%@page import="java.net.*" %>
<%@page import="java.sql.*" %>
<%@page import="java.util.*" %>

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
      
      String gender1 = request.getParameter("SeekingGenderID");
      String gender2 = request.getParameter("GenderID");
      int ageMin;
      String age1 = request.getParameter("MinAge");
      int ageMax;
      String age2 = request.getParameter("MaxAge");
      String country = request.getParameter("CountryRegionID");
      String state = request.getParameter("stateName");
      String city = request.getParameter("cityName");
      
		///////////////////////////////////////////////////////////////////////
		//if agemin and age max NOT specified, set agemin=0 and age max=100  //
		//or I could have changed the query not to include between selection //
		///////////////////////////////////////////////////////////////////////

		if ( age1 == null || (age1.trim()).equals("") )
			ageMin = 0;
		else
			ageMin = Integer.parseInt(age1);
		
		if ( age2 == null || (age2.trim()).equals("") )
			ageMax = 100;
		else
			ageMax = Integer.parseInt(age2);
		%>
		
      <HTML>
      <HEAD>
      <TITLE>Searching for member: lookupName</TITLE>
      </HEAD>
      <BODY BGCOLOR='#FFE57F'>
		
		<CENTER>
      
	  <%
      try {
         	Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost/contacts?user=kareena&password=kapoor");
         
         stmt = con.createStatement();
         
         ///////////////////////////////////////////////////////////////////////////////////////
         rs = stmt.executeQuery("SELECT * FROM userstable WHERE (age BETWEEN " + ageMin + " AND " + ageMax + ") AND (Gender='" + gender1 + "' AND SeekingGender='" + gender2 + "') AND (Country='" + country + "' AND State='" + state + "' AND City='" + city + "')");
      %>
		
        <div class="row">
        
		<%
		if (!rs.isBeforeFirst() ) {    
 System.out.println("No data"); 
} else{
	System.out.println("No data"); 
         while (rs.next()) {
            String picture = rs.getString("FileLocation");
            String user = rs.getString("UserName");
            String aboutUser = rs.getString("AboutMe1");
            String gender = rs.getString("Gender");
         %>
          <div class="col-sm-3">
			<form>
           <div class="row">
			<div class="card">
                <canvas class="header-bg" width="250" height="70" id="header-blur"></canvas>
                <div class="avatar">
                    <a href='details.jsp?type=2&data=<%=user%>'><img class="media-object" src="<%=picture%>" alt="..."></a>
                </div>
                <div class="content">
				<h4><a href='details.jsp?type=2&data=<%=user%>'><%=user%></a></h4>
                    <p><%=aboutUser%><br>
                       <%=gender%></p>
                    <p><button type="button" id="addToContacts" class="btn btn-default">Add to Contact List</button></p>
                 </div>
  
</div>
</div>
</form>
</div>
			
		<%
         }
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