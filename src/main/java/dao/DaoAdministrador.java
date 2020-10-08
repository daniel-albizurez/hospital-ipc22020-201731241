/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Administrador;

/**
 *
 * @author DANIEL
 */
public class DaoAdministrador extends DaoUsuarios<Administrador>{
    
    public static final String TABLA = "administrador";
    
    public static final String CODIGO = "codigo";
    public static final String EMAIL = "email";

    private static final String MARKERS = MARKER+COMA+MARKER;
    
    @Override
    protected PreparedStatement prepareInsert(Administrador obj) {
        String query = String.format(INSERT, TABLA, MARKERS);
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, obj.getCodigo());
            statement.setString(2, obj.getEmail());
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected PreparedStatement prepareUpdate(Administrador obj) {
        String fields = asignValue(EMAIL);
        String query = String.format(UPDATE, TABLA, fields)
                + String.format(WHERE, asignValue(CODIGO));
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, obj.getEmail());
            statement.setString(2, obj.getCodigo());
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected PreparedStatement prepareDelete(Administrador obj) {
        String query = String.format(DELETE, TABLA)
                + String.format(WHERE, asignValue(CODIGO));
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, obj.getCodigo());
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected PreparedStatement prepareSelect(String fields, String condition) {
        String query = String.format(SELECT, fields, TABLA);
                query += (condition.isBlank()) ? "" : String.format(WHERE, condition); 
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected Administrador setObject(ResultSet result) {
        Administrador found = new Administrador();
        try {
            if (result.first() && result.isLast()) {
                found.setCodigo(result.getString(CODIGO));
                found.setEmail(result.getString(EMAIL));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            found = null;
        }
        return found;
    }
    
}
