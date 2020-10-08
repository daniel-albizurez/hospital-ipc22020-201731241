<%-- 
    Document   : menureportes
    Created on : Oct 5, 2020, 9:45:50 PM
    Author     : DANIEL
--%>

<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
        <%
            if (usuario != null) {
                switch (usuario.getTipo()) {
                    case 1: %>
        <%@include file="reportes_source/reportesAdministrador.html"%>
        <%
                break;
            case 2:
        %>
        <%@include file="reportes_source/reportesMedico.html" %>
        <%                        break;
            case 3:
        %>

        <%
                break;
            case 4:
        %>
        <%@include file="reportes_source/reportesPaciente.html" %>

        <%
                        break;
                }
            } else {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        %>
