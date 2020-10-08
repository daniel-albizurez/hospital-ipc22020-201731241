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
public class Examen implements Serializable{
    
    private String codigo;
    private String nombre;
    private String descripcion;
    private double costo;
    private String tipo_informe;
    private boolean requiere_orden;

    public Examen() {
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCosto() {
        return costo;
    }
    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getTipo_informe() {
        return tipo_informe;
    }
    public void setTipo_informe(String tipo_informe) {
        this.tipo_informe = tipo_informe;
    }

    public boolean requiere_orden() {
        return requiere_orden;
    }
    public void setRequiere_orden(boolean requiere_orden) {
        this.requiere_orden = requiere_orden;
    }
    
}
