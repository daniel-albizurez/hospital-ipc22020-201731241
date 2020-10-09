/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import dao.Dao;
import dao.DaoEspecialidad;
import java.io.Serializable;

/**
 *
 * @author DANIEL
 */
public class Consulta extends Cita implements Serializable{
    
    private String medico;
    private String especialidad;
    private String descripcion;

    public Consulta() {
        super.setTipo(1);
        especialidad = "General";
        super.setCosto(
                (new DaoEspecialidad()).select(
                        DaoEspecialidad.TITULO+Dao.EQUALS+Dao.QUOTE+especialidad+Dao.QUOTE).getCosto()
                        );
    }

    public String getMedico() {
        return medico;
    }
    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
