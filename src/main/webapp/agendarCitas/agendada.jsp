<%-- 
    Document   : agendada
    Created on : Oct 7, 2020, 10:15:37 PM
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
        <% if (request.getParameter("succes").equals("1")) {%>
        <div>
            <p>
                Cita agendada existosamente
                <br>
                Codigo de cita: <%= request.getAttribute("codigo")%>
                <br><br>
            </p>
        </div>
        <%
        } else {
        %>
        <div>
            <p>
                No se ha podido agendar la cita
                <br>
                Por favor seleccione otra hora/fecha
                <br>
                Si el problema persiste comnuniquese con el administrador
                <br>
            </p>
        </div>

        <%
            }
        %>
        <div>
            Dia: <%= request.getAttribute("fecha")%> Hora:<%= request.getAttribute("hora")%>
        </div>
    </body>
</html>
