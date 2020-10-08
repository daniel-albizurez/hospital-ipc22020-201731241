<%-- 
    Document   : perfilAdministrador
    Created on : Oct 7, 2020, 12:07:41 PM
    Author     : DANIEL
--%>

<%@page import="modelo.Administrador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%! 
    Administrador administrador;
%>

<%
    administrador = (Administrador) usuario; %>
<div>
    Codigo: <%= administrador.getCodigo() %>
    <br><br>
</div>