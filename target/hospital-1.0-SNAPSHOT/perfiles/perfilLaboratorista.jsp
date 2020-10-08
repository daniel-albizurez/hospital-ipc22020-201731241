<%-- 
    Document   : perfilLaboratorista
    Created on : Oct 7, 2020, 12:08:08 PM
    Author     : DANIEL
--%>

<%@page import="modelo.Laboratorista"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%! 
    Laboratorista laboratorista;
%>

<%
    laboratorista = (Laboratorista) usuario; %>
<div>
    Codigo: <%= laboratorista.getCodigo() %>
    <br><br>
    <label for="examen">Examen asignado:</label>
    <input type="text" name="examen" id="examen" value="<%= laboratorista.getExamen()%>">
    <br><br>
    <label for="no_registro">Numero de registro:</label>
    <input type="text" name="no_registro" id="no_registro" value="<%= laboratorista.getNo_registro()%>">
    <br><br>
    <label for="fecha_inicio">Fecha de Inicio de labores:</label>
    <input type="date" name="fecha_inicio" id="fecha_inicio" value="<%= laboratorista.getFecha_inicio()%>">
    <br><br>
</div>