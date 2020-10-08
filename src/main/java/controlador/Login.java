/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.ControladorSesion;
import modelo.Usuario;

/**
 *
 * @author DANIEL
 */
@WebServlet("/login")
public class Login extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Usuario usuario = ControladorSesion.iniciarSesion(username, password);
        req.getSession().setAttribute("user", usuario);
        if (usuario == null) {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }else {
            req.getRequestDispatcher("/main.jsp").forward(req, resp);
        }
        System.out.println(((Usuario)req.getSession().getAttribute("user")).getNombre());
    }
    
    
    
}
