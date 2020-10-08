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
import logica.ControladorCitas;

/**
 *
 * @author DANIEL
 */
@WebServlet("/agendar")
public class Agendar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Integer.parseInt(req.getParameter("t")) == 1) {
            String codigo = req.getParameter("codigo");
            String fecha = req.getParameter("f");
            String hora = req.getParameter("h");
            req.setAttribute("fecha", fecha);
            req.setAttribute("hora", hora);
            if (ControladorCitas.agendarConsulta(codigo, req.getParameter("medico"), req.getParameter("es"), req.getParameter("p"), hora, fecha)) {
                req.setAttribute("codigo", codigo);
                req.getRequestDispatcher("/agendarCitas/agendada.jsp?succes=1").forward(req, resp);
            } else {
                req.getRequestDispatcher("/agendarCitas/agendada.jsp?succes=0").forward(req, resp);
            }
        }
    }

}
