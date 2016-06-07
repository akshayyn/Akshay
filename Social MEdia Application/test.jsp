<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%-- same page is being called --%>
        <%-- this is JSP comment line --%>
        <form action="test.jsp" method="post">
            First Name: <input type="text" name="fname"/><br/>
            Last Name: <input type="text" name="lname"/><br/>
            <input type="submit"/>
        </form>
        
        <%-- if you don't perform the following check the page will crash --%>
        <%-- as you tried to read fname and lname from request --%>
        <%-- but when the page loads initially, request will not have these params --%>
        
        <jsp:scriptlet>
            if (request.getMethod().equalsIgnoreCase("post"))
            {
                String first = request.getParameter("fname");
                String last = request.getParameter("lname");
                //normally, you could do out.println() here
                //but just to show that it could be done this way
                //} will be closed at the end
        </jsp:scriptlet>
        
        <hr/>
        Welcome <jsp:expression>first</jsp:expression> <jsp:expression>last</jsp:expression>
        
        <%-- the next scriptlet is just to close the if block --%>
        <%-- we have a missing } from the above scriptlet --%>
        <jsp:scriptlet>
            }   //this closes if block
        </jsp:scriptlet>
    </body>
</html>
