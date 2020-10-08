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
public class Paciente extends Usuario implements Serializable{
    private String codigo;
    private String nacimiento;
    private String grupo_sanguineo;
    private String sexo;
    private double peso;

    public Paciente() {
        super.setTipo(4);
    }

    public Paciente(String email, String password, String nombre, String dpi, String telefono) {
        super(email, password, nombre, dpi, telefono, 4);
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNacimiento() {
        return nacimiento;
    }
    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getGrupo_sanguineo() {
        return grupo_sanguineo;
    }
    public void setGrupo_sanguineo(String grupo_sanguineo) {
        this.grupo_sanguineo = grupo_sanguineo;
    }

    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public double getPeso() {
        return peso;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }
    
}
