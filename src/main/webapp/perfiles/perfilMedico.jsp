<%-- 
    Document   : perfilMedico
    Created on : Oct 7, 2020, 12:08:19 PM
    Author     : DANIEL
--%>

<%@page import="modelo.Medico"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%! 
    Medico medico;
%>

<%
    medico = (Medico) usuario; %>
<div>
    Codigo: <%= medico.getCodigo() %>
    <br><br>
    <label for="no_colegiado">Numero de colegiado:</label>
    <input type="text" name="no_colegiado" id="no_colegiado" value="<%= medico.getNo_colegiado()%>">
    <br><br>
    <label for="hora_inicio">Hora de inicio:</label>
    <input type="number" name="hora_inicio" id="hora_inicio" step="1" min="1" max="24" value="<%= medico.getHora_inicio() %>">
    <br><br>
    <label for="hora_fin">Hora de finalizacion:</label>
    <input type="number" name="hora_fin" id="hora_fin" step="1" min="1" max="24" value="<%= medico.getHora_fin()%>">
    <br><br>
    <label for="fecha_inicio">Fecha de Inicio de labores:</label>
    <input type="date" name="fecha_inicio" id="fecha_inicio" value="<%= medico.getFecha_inicio()%>">
    <br><br>
</div>