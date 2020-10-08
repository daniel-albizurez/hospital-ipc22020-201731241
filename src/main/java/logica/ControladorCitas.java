/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import dao.DaoCita;
import dao.DaoConsulta;
import dao.DaoEspecialidad;
import dao.DaoExamen;
import dao.DaoLaboratorista;
import dao.DaoMedico;
import dao.DaoPaciente;
import dao.DaoResultado;
import java.util.ArrayList;
import modelo.Consulta;
import modelo.Laboratorista;
import modelo.Medico;
import modelo.Paciente;
import modelo.Resultado;

/**
 *
 * @author DANIEL
 */
public class ControladorCitas {

    private static Consulta consulta;
    private static Resultado resultado;

    private static Paciente paciente;
    private static Medico medico;

    private static Laboratorista laboratorista;

    private static DaoCita daoCita = new DaoCita();
    private static DaoConsulta daoConsulta = new DaoConsulta();
    private static DaoResultado daoResultado = new DaoResultado();

    private static DaoPaciente daoPaciente = new DaoPaciente();
    private static DaoMedico daoMedico = new DaoMedico();
    private static DaoEspecialidad daoEspecialidad = new DaoEspecialidad();

    private static DaoExamen daoExamen = new DaoExamen();
    private static DaoLaboratorista daoLaboratorista = new DaoLaboratorista();

    public static boolean agendarConsulta(String codigo, String codigoMedico, String especialidad, String codigoPaciente, String hora, String fecha) {
        /**
         * Hay que tener al paciente Hay que tener al medico Hay que asegurar
         * que el medico existe Hay que tener la fecha Hay que tener la hora Hay
         * que ver que la hora este dentro del horario del medico
         */
        consulta = new Consulta();
        consulta.setCodigo(codigo);
        consulta.setFecha(fecha);
        consulta.setHora(hora);
        consulta.setMedico(codigoMedico);
        consulta.setPaciente(codigoPaciente);
        consulta.setEspecialidad(especialidad);

        consulta.setCosto(
                Double.parseDouble(
                        daoEspecialidad.multipleSelect("costo", "titulo = '" + especialidad + "'").get(0)[0])
        );

        paciente = daoPaciente.select("codigo='" + codigoPaciente + "'");
        medico = daoMedico.select("codigo='" + codigoMedico + "'");
        boolean libre;
        if (libre = (paciente != null && medico != null)) {
            ArrayList<String[]> ocupadas = daoCita.multipleSelect("hora", "tipo = 1 AND fecha='" + fecha + "'");
            for (String[] ocupada : ocupadas) {
                for (String horaOcupada : ocupada) {
                    libre &= !hora.substring(0, 2).equals(horaOcupada.substring(0, 2));
                }
            }
        }
        return (libre) ? daoConsulta.insert(consulta) : libre;
    }

    public static boolean agendarExamen(String codigo, String codigoLaboratorista, String examen, String codigoPaciente, String hora, String fecha, String orden) {
        resultado = new Resultado();
        resultado.setCodigo(codigo);
        resultado.setFecha(fecha);
        resultado.setHora(hora);
        resultado.setLaboratorista(codigoLaboratorista);
        resultado.setPaciente(codigoPaciente);
        resultado.setExamen(examen);
        resultado.setOrden((orden == null) ? "":orden);

        ArrayList<String[]> detalles = daoExamen.multipleSelect("costo", "codigo = '" + examen + "'");

        resultado.setCosto(
                Double.parseDouble(
                        detalles.get(0)[0])
        );

        paciente = daoPaciente.select("codigo='" + codigoPaciente + "'");
        laboratorista = daoLaboratorista.select("codigo='" + codigoLaboratorista + "'");

        return (paciente != null && laboratorista != null) ? daoResultado.insert(resultado) : false;
    }

}
