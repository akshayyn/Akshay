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
			
			//////////////////////////////////////////////
			// Display Messages for the user who logged in
			//////////////////////////////////////////////
%>
	<HTML>
	<HEAD>
		<TITLE>Contacts for <%=userName%></TITLE>
<link href="css/common.css" rel="stylesheet">
	<meta charset="utf-8">
    <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
function createXMLHttpRequest() {
if (window.ActiveXObject) {
xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
}
else if (window.XMLHttpRequest) {
xmlHttp = new XMLHttpRequest();
}
}

function deleteMessages(){
	rows = [];
createXMLHttpRequest();

var queryString = createQueryString();
xmlHttp.open("POST", "delete.jsp", true);
xmlHttp.onreadystatechange = handleStateChange;
xmlHttp.setRequestHeader("Content-Type",
"application/x-www-form-urlencoded;");
xmlHttp.send(queryString);
		}
var selected = [];
var rows = [];
function createQueryString() {
	var checkboxes = document.getElementsByName('msgList');

for (var i=0; i<checkboxes.length; i++) {
    if (checkboxes[i].checked) {
        selected.push(checkboxes[i].value);
		rows.push(checkboxes[i].parentNode.parentNode)
    }
}



var queryString = "msgList=" + selected;
return queryString;
}
function handleStateChange() {
if(xmlHttp.readyState == 4) {
if(xmlHttp.status == 200) {
	for (var i=0; i<rows.length; i++) {
    
       var table =  document.getElementsByName("messagesTable");
	   document.getElementById("messagesTable").deleteRow(rows[i].rowIndex);
    }


}
}
}
var selected = [];
var rows = [];
</script>
	</HEAD>
	<BODY >
	<nav class="navbar navbar-default">
	 
  <div class="navbar-toggleable-xs" id="exCollapsingNavbar2">
    <a class="navbar-brand" href="loggedin.jsp">Welcome <%=userName%></a>
    <ul class="nav navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href='details.jsp?type=2&data=<%=userName%>'>View My Details</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href='update.jsp'>Update My Information</a></li>
		 <li class="nav-item">
        <a class="nav-link" href='viewContacts.jsp'>View My Contacts</a></li>
		<li class="nav-item">
        <a class="nav-link" href='change.jsp'>Change my password</a>
      </li>
      <li class="nav-item pull-xs-right">
        <a class="nav-link" href='logout.jsp'>Logout</a>
      </li>
    </ul>
	</div>
</nav>
	<div class="container">
<%      
      Connection con = null;
      Statement stmt = null;
      ResultSet rs = null;
      try {
         Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = DriverManager.getConnection("jdbc:mysql://localhost/contacts?user=kareena&password=kapoor");
         
         stmt = con.createStatement();
         rs = stmt.executeQuery("SELECT * FROM contacts WHERE userName='" + userName + "' ORDER BY contactID");
%>
<div class="row">
	<form name='deleteContactsForm'>
        <table id="messagesTable" class="table">
		<tr >
			<td ALIGN='center'><B>Contact ID</B></td>
			<td ALIGN='center'><B>Contact Name</B></td>
			<td ALIGN='center'><B>Comment</B></td>
			<td ALIGN='center'><B>Date</B></td>
			<td ALIGN='center'><span class='glyphicon glyphicon-remove' onClick='deleteMessages()'></span>Delete</td>
		</tr>
<%
         int nRows = 0;
         while (rs.next()) {
            nRows++;
            String messageID = rs.getString("contactID");
            String fromUser = rs.getString("contactName");
            String message = rs.getString("comments");
            String messageDate = rs.getString("dateAdded");
%>     
			<tr>
            <td><%=messageID%></td>
            <td><%=fromUser%></td>
            <td><%=message%></td>
            <td><%=messageDate%></td>
            <td><input type='checkbox' name='msgList' value='<%=messageID%>'> Delete</td>
            </tr>
	<%
         }
    %>   

        </table>
        </FORM>
</div>
</div>
	<%
      }
      catch (Exception e) {
         out.println
         ("Could not connect to the users database.<P>");
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