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
public class Administrador extends Usuario implements Serializable{
    
    private String codigo;

    public Administrador() {
        super.setTipo(1);
    }

    public Administrador(String email, String password, String nombre, String dpi, String telefono, int tipo) {
        super(email, password, nombre, dpi, telefono, tipo);
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        if (super.getEmail() == null) {
            super.setEmail(codigo+"@amail.com");
        }
        this.codigo = codigo;
    }
    
}
