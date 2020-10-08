<%-- 
    Document   : perfilUsuario
    Created on : Oct 6, 2020, 9:00:42 PM
    Author     : DANIEL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div>
    <label for="nombre">Nombre:</label>
    <input type="text" name="nombre" id="nombre" value="<%=usuario.getNombre()%>">
    <br><br>
    <label for="dpi">DPI:</label>
    <input type="text" name="dpi" id="dpi" value="<%=usuario.getDpi()%>">
    <br><br>
    <label for="telefono">Telefono:</label>
    <input type="text" name="telefono" id="telefono" value="<%=usuario.getTelefono()%>">
    <br><br>
    <label for="email">Email:</label>
    <input type="text" name="email" id="email" value="<%=usuario.getEmail()%>">
    <br><br>
</div>