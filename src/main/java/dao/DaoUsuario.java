/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Usuario;

/**
 *
 * @author DANIEL
 */
public class DaoUsuario extends Dao<Usuario> {
    
    public static final String TABLE = "usuario";
    
    public static final String EMAIL = "email";
    public static final String NOMBRE = "nombre";
    public static final String DPI = "dpi";
    public static final String TELEFONO = "telefono";
    public static final String TIPO = "tipo";
    public static final String PASSWORD = "password";
    
    private static final String MARKERS = MARKER+COMA+MARKER+COMA+MARKER+COMA+
                                          MARKER+COMA+MARKER+COMA+ " md5(" + MARKER + ")";

    @Override
    protected PreparedStatement prepareInsert(Usuario obj) {
        String query = String.format(INSERT, TABLE, MARKERS);
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, obj.getEmail());
            statement.setString(2, obj.getNombre());
            statement.setString(3, obj.getDpi());
            statement.setString(4, obj.getTelefono());
            statement.setInt(5, obj.getTipo());
            statement.setString(6, obj.getPassword());
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected PreparedStatement prepareUpdate(Usuario obj) {
        String fields = asignValue(EMAIL) + COMA
                + asignValue(NOMBRE) + COMA
                + asignValue(DPI) + COMA
                + asignValue(TELEFONO) + COMA
                + asignValue(TIPO) + COMA
                /* No se saca la contraseña de la BD
                * Si el usuario la cambia no será  null
                */
                + ((obj.getPassword() != null || !obj.getPassword().isBlank()) ? asignValue(PASSWORD) : "" )
                ;
        String query = String.format(UPDATE, TABLE, fields)
                + String.format(WHERE, asignValue(EMAIL));
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, obj.getEmail());
            statement.setString(2, obj.getNombre());
            statement.setString(3, obj.getDpi());
            statement.setString(4, obj.getTelefono());
            statement.setInt(5, obj.getTipo());
            if (statement.getParameterMetaData().getParameterCount() == 7) {
                statement.setString(6, obj.getPassword());
                statement.setString(7, obj.getEmail());
            } else {
                statement.setString(6, obj.getEmail());
            }
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected PreparedStatement prepareDelete(Usuario obj) {
        String query = String.format(DELETE, TABLE)
                + String.format(WHERE, asignValue(EMAIL));
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, obj.getEmail());
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected PreparedStatement prepareSelect(String fields, String condition) {
        String query = String.format(SELECT, fields, TABLE);
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
    protected Usuario setObject(ResultSet result) {
        Usuario found = new Usuario();
        try {
            if (result.first() && result.isLast()) {
                found.setEmail(result.getString(EMAIL));
                found.setNombre(result.getString(NOMBRE));
                found.setDpi(result.getString(DPI));
                found.setTelefono(result.getString(TELEFONO));
                found.setTipo(result.getInt(TIPO));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            found = null;
        }
        return found;
    }

}
