<%-- 
    Document   : agendarConsulta
    Created on : Oct 7, 2020, 3:58:02 PM
    Author     : DANIEL
--%>

<%@page import="java.time.LocalDate"%>
<%@page import="dao.DaoEspecialidad"%>
<%@page import="logica.ControladorHTML"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%! 
    DaoEspecialidad dao; %>
<%
    dao = new DaoEspecialidad();
    %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <form action="agendarConsulta.jsp" target="agendar">
                <label for="especialidad">Especialidad:</label>
                <select name="especialidad" id="especialidad">
                    <%= ControladorHTML.generateSelectOptions(dao.multipleSelect(DaoEspecialidad.TITULO, null)) %>
                </select>
                <br><br>
                <label for="fecha">Fecha:</label>
                <input type="date" name="fecha" id="fecha" required min="<%= LocalDate.now() %>">
                <br><br>
                <label for="hora">Hora:</label>
                <input type="number" name="hora" id="hora" min="1" min="24" required>
                <br><br>
                <input type="submit" value="Ver médicos disponibles">
                <br><br>
            </form>
        </div>
        <iframe name="agendar" width="100%" height="100%" allowfullscreen="true"></iframe>
    </body>
</html>
