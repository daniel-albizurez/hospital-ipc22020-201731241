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
public class Horario implements Serializable{
    
    private String laboratorista;
    private String dia;

    public Horario() {
    }

    public String getLaboratorista() {
        return laboratorista;
    }
    public void setLaboratorista(String laboratorista) {
        this.laboratorista = laboratorista;
    }

    public String getDia() {
        return dia;
    }
    public void setDia(String dia) {
        this.dia = dia;
    }
    
}
