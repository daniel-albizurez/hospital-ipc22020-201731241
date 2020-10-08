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
public class Usuario implements Serializable{
    
    private String email;
    private String nombre;
    private String dpi;
    private String telefono;
    private int tipo;
    private String password;

    public Usuario() {
    }
    
    public Usuario(String email, String password, String nombre, String dpi, String telefono, int tipo) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.dpi = dpi;
        this.telefono = telefono;
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDpi() {
        return dpi;
    }
    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getTipo() {
        return tipo;
    }
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
}
