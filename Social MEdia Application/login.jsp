<%@ page import="java.io.*" %>
<%@ page import="java.net.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>

<%!
	String PREFIX = "session.login";
	String ACCOUNT = PREFIX + ".account";
%>

<%
		session.removeValue(ACCOUNT);
		
		try
		{
			// Get user name and password
			String userName = request.getParameter("user");
			String userPassword = request.getParameter("password");
			String pswd = "";
			
			if (userName == null)
				throw new RuntimeException("No user name was specified");
				
			userName = userName.trim();
			
			if (userName.equals(""))
				throw new RuntimeException("User name cannot be blank");

			if (userPassword == null)
				throw new RuntimeException("No password was specified");
				
			userPassword = userPassword.trim();
			
			if (userPassword.equals(""))
            throw new RuntimeException("Password cannot be blank");
            
            // Validate user password
            
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
      
            Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost/contacts?user=kareena&password=kapoor");
            
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT UserPassword FROM userstable WHERE UserName='" + userName + "'");
              
             if (rs == null)
             	throw new RuntimeException("Sorry No records Found - Check your user name");
             	
             	while (rs.next())
             	{
             		pswd = rs.getString("UserPassword");
             	}
            
            if (!userPassword.equals(pswd))
            	throw new RuntimeException("Please check your password");
            	
            // Everything OK - proceed to main application
            
            Hashtable account = new Hashtable();
            
            account.put("name", userName);
            account.put("password", userPassword);
            
            session.putValue(ACCOUNT, account);
			response.setContentType("application/json");

    response.getWriter().write("{ 'success': true, 'location': 'loggedin.jsp' }");

			
		}
		catch (RuntimeException e)
		{	response.setContentType("application/json");
			response.getWriter().write("{ 'success': false, 'message': 'login failed' }");
			
		}
		catch (Exception e)
		{response.setContentType("application/json");
        response.getWriter().write("{ 'success': false, 'message': 'login failed' }");
      }
%>