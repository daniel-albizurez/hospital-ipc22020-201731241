/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Especialidad;

/**
 *
 * @author DANIEL
 */
public class DaoEspecialidad  extends Dao<Especialidad>{
    
    public static final String TABLE = "especialidad";
    
    public static final String TITULO = "titulo";
    public static final String COSTO = "costo";
    
    public static final String MARKERS = MARKER+COMA+MARKER;
   
    @Override
    protected PreparedStatement prepareInsert(Especialidad obj) {
        String query = String.format(INSERT, TABLE, MARKERS);
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, obj.getTitulo());
            statement.setDouble(2, obj.getCosto());
            return statement; 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected PreparedStatement prepareUpdate(Especialidad obj) {
        String fields = asignValue(COSTO);
        String query = String.format(UPDATE,TABLE,fields)
                + String.format(WHERE, asignValue(TITULO));
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setDouble(1, obj.getCosto());
            statement.setString(2, obj.getTitulo());
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected PreparedStatement prepareDelete(Especialidad obj) {
        String query  = String.format(DELETE, TABLE)
                + String.format(WHERE, asignValue(TITULO));
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, obj.getTitulo());
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected PreparedStatement prepareSelect(String fields, String condition) {
        String query = String.format(SELECT, fields, TABLE);
        query += (condition == null || condition.isBlank()) ? "" : String.format(WHERE, condition); 
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected Especialidad setObject(ResultSet result) {
        Especialidad found = new Especialidad();
        try {
            if (result.first() && result.isLast()) {
                found.setTitulo(result.getString(TITULO));
                found.setCosto(result.getDouble(COSTO));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            found = null;
        }
        return found;
    }
    
}
