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
public class Medico extends Usuario implements Serializable{
    
    private String codigo;
    private String no_colegiado;
    private String hora_inicio;
    private String hora_fin;
    private String fecha_inicio;

    public Medico() {
        super.setTipo(2);
    }
  
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNo_colegiado() {
        return no_colegiado;
    }
    public void setNo_colegiado(String no_colegiado) {
        this.no_colegiado = no_colegiado;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }
    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_fin() {
        return hora_fin;
    }
    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }
    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
    
}
