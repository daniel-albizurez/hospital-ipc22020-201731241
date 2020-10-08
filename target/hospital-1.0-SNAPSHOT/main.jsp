<%-- 
    Document   : main
    Created on : Oct 5, 2020, 11:02:47 AM
    Author     : DANIEL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!
    Usuario usuario;
%>
<html style="height: 100%">
    <% usuario = (Usuario) request.getSession().getAttribute("user");%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%    if (usuario != null) {%>
    <body style="height: 100%">
        <h1>Hello World!</h1>
        <div>
            <ul>
                <li><a href="perfil.jsp?u=<%= usuario.getEmail()%>" target="pantalla">Perfil</a></li>
                    <%@include file="menu.jsp" %>
                <li>Reportes <%@include file="menureportes.jsp" %></li>
                <li><a href="logout">Cerrar Sesion</a></li>
            </ul>
        </div>
        <div style="height: 100%">

            <iframe name="pantalla" width="100%" height="100%" allowfullscreen="true"></iframe>
        </div>
    </body>
    <%     } else {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    %>
</html>
