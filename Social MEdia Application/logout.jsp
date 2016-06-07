<%!
	String PREFIX = "session.login";
	String ACCOUNT = PREFIX + ".account";
%>

<%
	session.removeValue(ACCOUNT);
				
	//send the user to the main page
	response.sendRedirect("index.html");
%>