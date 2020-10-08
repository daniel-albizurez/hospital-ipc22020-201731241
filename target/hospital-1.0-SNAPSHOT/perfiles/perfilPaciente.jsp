<%-- 
    Document   : perfilPaciente
    Created on : Oct 7, 2020, 10:51:44 AM
    Author     : DANIEL
--%>

<%@page import="logica.ControladorSesion"%>
<%@page import="dao.Dao"%>
<%@page import="dao.DaoPaciente"%>
<%@page import="modelo.Paciente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%! 
    Paciente paciente;
    boolean femenino;
%>

<%
    paciente = (Paciente) usuario; %>

<div>
    <label for="codigo">Codigo: <%= paciente.getCodigo() %></label>
    <br><br>
    <label for="nacimiento">Fecha de Nacimiento:</label>
    <input type="date" name="nacimiento" id="nacimiento" value="<%= paciente.getNacimiento() %>">
    <br><br>
    <h3>Tipo de sangre</h3>
    <br><br>
    <input type="radio" name="grupo_sanguineo" id="apositivo" value="A+" <%= paciente.getGrupo_sanguineo().equals("A+") ? "checked": "" %> >
    <label for="apositivo">A+</label>

    <input type="radio" name="grupo_sanguineo" id="anegativo" value="A-" <%= paciente.getGrupo_sanguineo().equals("A-") ? "checked": "" %> >
    <label for="anegativo">A-</label>

    <input type="radio" name="grupo_sanguineo" id="bpositivo" value="B+" <%= paciente.getGrupo_sanguineo().equals("B+") ? "checked": "" %> >
    <label for="bpositivo">B+</label>

    <input type="radio" name="grupo_sanguineo" id="bnegativo" value="B-" <%= paciente.getGrupo_sanguineo().equals("B-") ? "checked": "" %> >
    <label for="bnegativo">B-</label>

    <input type="radio" name="grupo_sanguineo" id="abpositivo" value="AB+" <%= paciente.getGrupo_sanguineo().equals("AB+") ? "checked": "" %> >
    <label for="abpositivo">AB+</label>

    <input type="radio" name="grupo_sanguineo" id="abnegativo" value="AB-" <%= paciente.getGrupo_sanguineo().equals("AB-") ? "checked": "" %> >
    <label for="abnegativo">AB-</label>

    <input type="radio" name="grupo_sanguineo" id="opositivo" value="O+" <%= paciente.getGrupo_sanguineo().equals("O+") ? "checked": "" %> >
    <label for="opositivo">O+</label>

    <input type="radio" name="grupo_sanguineo" id="onegativo" value="O-" <%= paciente.getGrupo_sanguineo().equals("O-") ? "checked": "" %> >
    <label for="onegativo">O-</label>

    <h3>Sexo</h3>
    <br><br>
    <%
    switch (paciente.getSexo()) {
        case "F": femenino = true; break;
        case "M": femenino = false; break;
    }
    %>
    
    <input type="radio" name="sexo" id="masculino" value="M" <%= !femenino ? "checked": "" %>>
    <label for="masculino">M</label>

    <input type="radio" name="sexo" id="femenino" value="F" <%= femenino ? "checked": "" %> >
    <label for="femenino">F</label>
    <br><br>
    <label for="peso">Peso (En kg):</label>
    <input type="number" name="peso" id="peso" step="0.1" min="0" value="<%= paciente.getPeso()%>">
    <br><br>
</div>