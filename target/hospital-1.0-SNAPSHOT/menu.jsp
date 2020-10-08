<%-- 
    Document   : menu
    Created on : Oct 6, 2020, 6:10:26 PM
    Author     : DANIEL
--%>

<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    switch (usuario.getTipo()) {
        case 1: %>
<%@include file="menus/menuAdministrador.html"%>
<%
        break;
    case 2:
%>
<%@include file="menus/menuMedico.html"%>
<%                        break;
    case 3:
%>
<%@include file="menus/menuLaboratorista.html"%>
<%
        break;
    case 4:
%>
<%@include file="menus/menuPaciente.html"%>
<%
            break;
    }
%>

