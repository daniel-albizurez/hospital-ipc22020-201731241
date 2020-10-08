<%-- 
    Document   : index
    Created on : Oct 5, 2020, 8:35:55 PM
    Author     : DANIEL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%  if (request.getSession().getAttribute("user") != null) {
                request.getRequestDispatcher("main.jsp").forward(request, response);
            } else {
        %> 
        <h1>Correo Electronico o contraseña incorrectos</h1>
        <%@include file="index.html"%>
        <% }%>
    </body>
</html>
