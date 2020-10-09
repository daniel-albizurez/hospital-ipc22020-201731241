/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.DaoVista;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Administrador;
import modelo.Laboratorista;
import modelo.Medico;
import modelo.Paciente;
import modelo.Usuario;

/**
 *
 * @author DANIEL
 */
@WebServlet("/reportes")
public class Reportes extends HttpServlet {

    private final static String DATE_BETWEEN = "fecha BETWEEN %s AND %s";

//    Reportes Administrador
    private final static String[] HEADER_TOP_10_MEDICOS = new String[]{"INFORMES", "Codigo de medico", "NOMBRE"};
    private final static String TOP_10_MEDICOS_INFORMES = "top_10_medico_informes";

    private final static String[] HEADER_INGRESOS_MEDICO = new String[]{"Ingresos", "Codigo de medico", "Nombre"};
    private final static String INGRESOS_MEDICO = "ingresos_medico";

    private final static String[] HEADER_MENOS_CITAS = new String[]{"Citas", "Codigo de Medico", "Nombre"};
    private final static String MENOS_CITAS = "menos_citas";

    private final static String[] HEADER_MAS_SOLICITADOS = new String[]{"Cantidad", "Codigo de examen", "Tipo de examen"};
    private final static String EXAMENES_MAS_SOLICITADOS = "examenes_mas_solicitados";

    private final static String[] HEADER_INGRESOS_PACIENTE = new String[]{"Ingresos", "Codigo Paciente", "Nombre de Paciente"};
    private final static String INGRESOS_PACIENTE = "ingresos_paciente";

//    Reportes Medico
    private final static String[] HEADER_HISTORIAL_MEDICO = new String[]{"Codigo de cita", "Codigo Paciente", "Nombre de Paciente", "Tipo de cita", "Codigo del encargado",
        "Nombre del encargado", "Detalle de cita", "Informacion", "Fecha", "Costo"};
    private final static String HISTORIAL_MEDICO = "historial_medico";

    private final static String[] HEADER_CITAS_AGENDADAS = new String[]{"Codigo de cita", "Codigo Paciente", "Nombre de Paciente", "Fecha", "Hora", "Especialidad", "Medico"};
    private final static String CITAS_AGENDADAS = "citas_agendadas";

    private final static String[] HEADER_INFORMES_PACIENTE = new String[]{"Cantidad de informes", "Paciente", "Nombre de Paciente"};
    private final static String INFORMES_PACIENTE = "informes_paciente";

//    Reportes laboratoristas
    private final static String[] HEADER_EXAMENES_LABORATORISTA = new String[]{"Examen", "Nombre de examen", "Fecha", "Codigo de laboratorista", "Ruta de informe"};
    private final static String EXAMENES_LABORATORISTA = "examenes_laboratorista";

    private final static String[] HEADER_EXAMENES_POR_DIA = new String[]{"Cantidad de examenes", "Ffecha", "Codigo de laboratorista"};
    private final static String EXAMENES_POR_DIA = "examenes_por_dia";

//    Reportes Paciente
    private final static String[] HEADER_ULTIMOS_EXAMENES = new String[]{"Examen", "Fecha", "Codigo del Paciente"};
    private final static String ULTIMOS_EXAMENES = "ultimos_examenes";

    private final static String[] HEADER_ULTIMAS_CONSULTAS = new String[]{"Especialidad", "Codigo de Medico", "Medico", "Fecha", "Codigo de paciente"};
    private final static String ULTIMAS_CONSULTAS = "ultimas_consultas";

    private String vistaActual;
    private DaoVista daoVista = new DaoVista();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        vistaActual = req.getParameter("v");
        setParameters(req);
        req.getRequestDispatcher("/reportes_source/reporte.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setParameters(req);
        req.getRequestDispatcher("/reportes_source/reporte.jsp").forward(req, resp);
    }

    private void setParameters(HttpServletRequest req) {
        String condicion = null;
        Usuario actual = (Usuario) req.getSession().getAttribute("user");
        String header[] = {};
        Object date0 = req.getParameter("d0");
        Object date1 = req.getParameter("d1");
        String condicionDate = String.format(DATE_BETWEEN,
                "'" + ((date0 != null) ? date0 : LocalDate.now().format(DateTimeFormatter.ISO_DATE)) + "'",
                "'" + ((date1 != null) ? date1 : LocalDate.now().format(DateTimeFormatter.ISO_DATE)) + "'"
        );

        if (vistaActual != null) {
            if (actual instanceof Administrador) {
                switch (vistaActual) {
                    case TOP_10_MEDICOS_INFORMES:
                        header = HEADER_TOP_10_MEDICOS;
                        break;
                    case INGRESOS_MEDICO:
                        condicion = (req.getParameter("m") != null) ? "codigo = '" + req.getParameter("m") + "'" : null;
                        header = HEADER_INGRESOS_MEDICO;
                        break;
                    case MENOS_CITAS:
                        header = HEADER_MENOS_CITAS;
                        break;
                    case EXAMENES_MAS_SOLICITADOS:
                        header = HEADER_MAS_SOLICITADOS;
                        break;
                    case INGRESOS_PACIENTE:
                        String paciente = req.getParameter("p");
                        condicion = (paciente != null) ? "codigo = '" + paciente + "'" : null;
                        header = HEADER_INGRESOS_PACIENTE;
                        break;
                }
            } else if (actual instanceof Medico) {
                condicion = "medico = '" + ((Medico) actual).getCodigo() + "'";
                switch (vistaActual) {
                    case HISTORIAL_MEDICO:
                        header = HEADER_HISTORIAL_MEDICO;
                        condicion = (req.getParameter("p") != null) ? "paciente = '" + req.getParameter("p") + "'" : null;
                        break;
                    case CITAS_AGENDADAS:
                        condicion += DaoVista.AND + condicionDate;
                        header = HEADER_CITAS_AGENDADAS;
                        break;
                    case INFORMES_PACIENTE:
                        header = HEADER_INFORMES_PACIENTE;
                        condicion = null;
                        break;

                }
            } else if (actual instanceof Laboratorista) {
                condicion = " codigo = '" + ((Laboratorista) actual).getCodigo() + "'";
                switch (vistaActual) {
                    case EXAMENES_LABORATORISTA:
                        condicion += DaoVista.AND + condicionDate;
                        condicion += " AND informe " + ((req.getParameter("a") != null) ? "IS NOT NULL" : "IS NULL");
                        header = HEADER_EXAMENES_LABORATORISTA;
                        break;
                    case EXAMENES_POR_DIA:
                        condicion += ((req.getParameter("l") != null) ? " LIMIT 10" : "");
                        header = HEADER_EXAMENES_POR_DIA;
                        break;
                }
            } else if (actual instanceof Paciente) {
                condicion = "paciente = '" + ((Paciente) actual).getCodigo() + "'";
                switch (vistaActual) {
                    case ULTIMOS_EXAMENES:
//                        Si viniera t seria el tipo de lo contrario solo se meustran 5
                        condicion += (req.getParameter("t") != null) ? " AND nombre = '" + req.getParameter("t") + "'" : " LIMIT 5";
                        header = HEADER_ULTIMOS_EXAMENES;
                        break;
                    case ULTIMAS_CONSULTAS:
                        condicion += (req.getParameter("m") != null) ? " AND medico = '" + req.getParameter("m") + "'" : " LIMIT 5";
                        header = HEADER_ULTIMAS_CONSULTAS;
                        break;
                }

            } else {
                vistaActual = null;
            }
            req.setAttribute("reporte", daoVista.multipleSelect(vistaActual, condicion));
            req.setAttribute("header", header);
        }
    }
}
