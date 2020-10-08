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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Administrador;
import modelo.Medico;
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
        String condicion;
        Usuario actual = (Usuario) req.getSession().getAttribute("user");
        if (vistaActual != null) {
            if (actual instanceof Administrador) {
                switch (vistaActual) {
                    case TOP_10_MEDICOS_INFORMES:
                        Object date0 = req.getAttribute("d0");
                        Object date1 = req.getAttribute("d1");
                        condicion = String.format(DATE_BETWEEN,
                                ((date0 != null) ? date0 : LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)),
                                ((date1 != null) ? date1 : LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE))
                        );
                        req.setAttribute("reporte", daoVista.multipleSelect(vistaActual, null));
                        req.setAttribute("header", HEADER_TOP_10_MEDICOS);
                        break;
                    case INGRESOS_MEDICO:
                        req.setAttribute("reporte", daoVista.multipleSelect(vistaActual, null));
                        req.setAttribute("header", HEADER_INGRESOS_MEDICO);
                        break;
                    case MENOS_CITAS:
                        req.setAttribute("reporte", daoVista.multipleSelect(vistaActual, null));
                        req.setAttribute("header", HEADER_MENOS_CITAS);
                        break;
                    case EXAMENES_MAS_SOLICITADOS:
                        req.setAttribute("reporte", daoVista.multipleSelect(vistaActual, null));
                        req.setAttribute("header", HEADER_MAS_SOLICITADOS);
                        break;
                    case INGRESOS_PACIENTE:
                        String paciente = req.getParameter("p");
                        condicion = (paciente != null) ? "codigo = '" + paciente + "'" : null;
                        req.setAttribute("reporte", daoVista.multipleSelect(vistaActual, condicion));
                        req.setAttribute("header", HEADER_INGRESOS_PACIENTE);
                        break;
                }
            } else if (actual instanceof Medico) {
                switch (vistaActual) {
                    case HISTORIAL_MEDICO:
                        req.setAttribute("reporte", daoVista.multipleSelect(vistaActual, null));
                        req.setAttribute("header", HEADER_HISTORIAL_MEDICO);
                        break;
                    case CITAS_AGENDADAS:
                        Object date0 = req.getAttribute("d0");
                        Object date1 = req.getAttribute("d1");
                        condicion = String.format(DATE_BETWEEN,
                                "'"+((date0 != null) ? date0 : LocalDate.now().format(DateTimeFormatter.ISO_DATE))+"'",
                                "'"+((date1 != null) ? date1 : LocalDate.now().format(DateTimeFormatter.ISO_DATE))+"'"
                        ) + " AND medico = '"+ ((Medico) actual).getCodigo() +"'";
                        req.setAttribute("reporte", daoVista.multipleSelect(vistaActual, condicion));
                        req.setAttribute("header", HEADER_TOP_10_MEDICOS);
                        break;
                    case MENOS_CITAS:
                        req.setAttribute("reporte", daoVista.multipleSelect(vistaActual, null));
                        req.setAttribute("header", HEADER_MENOS_CITAS);
                        break;
                    case EXAMENES_MAS_SOLICITADOS:
                        req.setAttribute("reporte", daoVista.multipleSelect(vistaActual, null));
                        req.setAttribute("header", HEADER_MAS_SOLICITADOS);
                        break;
                    case INGRESOS_PACIENTE:
                        String paciente = req.getParameter("p");
                        condicion = (paciente != null) ? "codigo = '" + paciente + "'" : null;
                        req.setAttribute("reporte", daoVista.multipleSelect(vistaActual, condicion));
                        req.setAttribute("header", HEADER_INGRESOS_PACIENTE);
                        break;
                }
            }

        }
    }
}
