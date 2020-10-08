<%-- 
    Document   : reporte
    Created on : Oct 6, 2020, 12:14:06 PM
    Author     : DANIEL
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="logica.ControladorTablas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%= ControladorTablas.generateTable((String[])request.getAttribute("header"), (ArrayList<String[]>) request.getAttribute("reporte")) %>
    </body>
</html>
