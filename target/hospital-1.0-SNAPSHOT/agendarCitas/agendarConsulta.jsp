<%-- 
    Document   : agendarConsulta
    Created on : Oct 7, 2020, 5:43:58 PM
    Author     : DANIEL
--%>

<%@page import="modelo.Paciente"%>
<%@page import="modelo.Usuario"%>
<%@page import="modelo.Consulta"%>
<%@page import="modelo.Especialidad"%>
<%@page import="dao.DaoVista"%>
<%@page import="dao.DaoMedico"%>
<%@page import="dao.DaoEspecialidad"%>
<%@page import="logica.ControladorHTML"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!
    DaoVista daoVista;
    DaoEspecialidad daoEspecialidad;
    Especialidad especialidad;
    String hora;
    String fecha;
    Consulta consulta;
    Paciente usuario;
%>
<%
    daoVista = new DaoVista();
    daoEspecialidad = new DaoEspecialidad();
    consulta = new Consulta();
    usuario = (Paciente) request.getSession().getAttribute("user");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seleccionar medico</title>
    </head>
    <%
        hora = request.getParameter("hora");
        fecha = request.getParameter("fecha");
        especialidad = daoEspecialidad.select("titulo=\'"
                + request.getParameter("especialidad") + "\'");
    %>
    <body>
        <form action="../agendar?t=1&h=<%=hora%>&f=<%=fecha%>&es=<%=especialidad.getTitulo()%>&p=<%=usuario.getCodigo()%>" method="POST">
            <label for="codigo">Codigo de consulta:</label>
            <input type="text" name="codigo" id="codigo" required>
            <br><br>
            <label for="medico">Medico:</label>
            <select name="medico" id="medico" required>

                <%= ControladorHTML.generateSelectOptions(daoVista.multipleSelect("titulos_medico", "codigo, nombre", "titulo=\'"
                        + especialidad.getTitulo() + "\' AND inicio<" + hora + " AND fin>" + hora))%>
            </select>
            <br><br>
            <label for="costo">Costo:</label>
            <input type="number" name="costo" id="costo" step="0.1" value="<%= especialidad.getCosto()%>"  disabled required>
            <br><br>
            <input type="submit" value="Agendar consulta">
        </form>
    </body>
</html>
