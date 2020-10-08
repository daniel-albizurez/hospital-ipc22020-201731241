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
public class Laboratorista extends Usuario implements Serializable{
    
    private String codigo;
    private String examen;
    private String no_registro;
    private String fecha_inicio;

    public Laboratorista() {
        super.setTipo(3);
    }
    
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getExamen() {
        return examen;
    }
    public void setExamen(String examen) {
        this.examen = examen;
    }

    public String getNo_registro() {
        return no_registro;
    }
    public void setNo_registro(String no_registro) {
        this.no_registro = no_registro;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }
    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
    
}
