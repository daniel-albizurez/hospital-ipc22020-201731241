/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author DANIEL
 */
public class Resultado extends Cita implements Serializable{
    
    private String laboratorista;
    private String examen;
    private String informe;
    private String orden;

    public Resultado() {
        super.setTipo(2);
    }
    
    public String getLaboratorista() {
        return laboratorista;
    }
    public void setLaboratorista(String laboratorista) {
        this.laboratorista = laboratorista;
    }

    public String getExamen() {
        return examen;
    }
    public void setExamen(String examen) {
        this.examen = examen;
    }

    public String getInforme() {
        return informe;
    }
    public void setInforme(String informe) {
        this.informe = informe;
    }

    public String getOrden() {
        return orden;
    }
    public void setOrden(String orden) {
        this.orden = orden;
    }
    
}
