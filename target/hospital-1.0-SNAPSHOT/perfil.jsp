<%-- 
    Document   : perfile
    Created on : Oct 7, 2020, 9:47:15 AM
    Author     : DANIEL
--%>

<%@page import="logica.ControladorSesion"%>
<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!
    Usuario enSesion;
    Usuario usuario;
    boolean puedeModificar;
%>
<html>
    <%
        usuario = ControladorSesion.getByEmail(request.getParameter("u"));
        enSesion = (Usuario) session.getAttribute("user");
        puedeModificar = enSesion != null && (enSesion.getEmail().equals(usuario.getEmail())
                || enSesion.getTipo() == 1);
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Perfil</title>
    </head>
    <body>
        <%            if (usuario != null) {%>
        <form action="<%= (puedeModificar) ? "updatePage/Servlet" : ""%>">
            <fieldset <%= (puedeModificar) ? "" : "disabled"%>>
                <%@include file="../perfiles/perfilUsuario.jsp" %>
                <%
                    switch (usuario.getTipo()) {
                        case 1: %>
                <%@include file="perfiles/perfilAdministrador.jsp"%>
                <%
                        break;
                    case 2:
                %>
                <%@include file="perfiles/perfilMedico.jsp" %>
                <%                        break;
                    case 3:
                %>
                <%@include file="perfiles/perfilLaboratorista.jsp" %>
                <%
                        break;
                    case 4:
                %>
                <%@include file="perfiles/perfilPaciente.jsp" %>

                <%
                            break;
                    } %>
                <%
                    if (puedeModificar) {
                %>
                <label for="password">Contraseña:</label>
                <input type="password" name="password" id="password">
                <br><br>
                <input type="submit" value="Guardar Cambios">
                <%
                    }
                %>
            </fieldset>
        </form>
        <%                } else {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        %>
    </body>
</html>
