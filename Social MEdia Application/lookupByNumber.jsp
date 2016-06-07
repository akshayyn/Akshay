<%@ page import="java.io.*" %>
<%@ page import="java.net.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<%
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
			
			//////////////////////////////////////////////
			// Display Messages for the user who logged in
			//////////////////////////////////////////////
			      
      Connection con = null;
      Statement stmt = null;
      ResultSet rs = null;
      
      String lookupID = request.getParameter("LookupMemberID");
%>

      <HTML>
      <HEAD>
      <TITLE>Searching for member: lookupID</TITLE>
	  <link rel="stylesheet" href="lookupResults.css"	>
	  

	  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <script>
	function trythis(){
		alert("inside");
	}
  </script>
      </HEAD>
      <BODY BGCOLOR='#B388FF'>
      
	<%
      try {
         Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = DriverManager.getConnection("jdbc:mysql://localhost/contacts?user=kareena&password=kapoor");
         
         stmt = con.createStatement();
         rs = stmt.executeQuery("SELECT * FROM userstable WHERE UserID=" + Integer.parseInt(lookupID));
    %>
         
		<div class="container">
		 <div class="modal fade"  id="myModal1">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h4 class="modal-title">Add to COntacts</h4>
          </div>
          <div class="modal-body">
            <form role="form" class="form-horizontal">
                
                <div class="form-group">
                  <label class="col-sm-12" for="comments">Comments</label>
                  <div class="col-sm-12"><textarea class="form-control" id="comments" rows="5"></textarea></div>
                </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cancel</button> 
            
            <button type="button" class="btn btn-primary " onClick="addToContacts()" data-dismiss="modal">Send <i class="fa fa-arrow-circle-right fa-lg"></i></button>
            
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal compose message -->
		<div class="row">
    
	<%
         int i = 0;
         String formName = "form";
         String buttonName = "button";
         if(null != rs){
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
                    <a href='details.jsp?type=1&data=<%=lookupID%>'><img class="media-object" src="<%=picture%>" alt="..."></a>
                </div>
                <div class="content">
				<h4><a href='details.jsp?type=1&data=<%=lookupID%>'><%=user%></a></h4>
                    <p><%=aboutUser%><br>
                       <%=gender%><br>
					   <%=city%>, <%=state%>, <%=country%></p>
                    <p id="addContBtn"><button type="button" id=<%=buttonName%> class="btn btn-default" onClick="addToContacts()">Add to Contact List</button></p>
					<input type='hidden' value='<%=user%>' id="hiddenUser" name='hiddenUser'>
                 </div>
  
</div>
</form>
</div>   
     <%  
			i++;
	  }}else{
		%>
		</div>
		<p>Sorry no profiles found! Please try again.</p>
        </div>
	<%
	  }
      }
      catch (Exception e) {
         out.println("Sorry no profiles found! Please try again.<P>");
         
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