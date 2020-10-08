/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Especializacion;

/**
 *
 * @author DANIEL
 */
public class DaoEspecializacion extends Dao<Especializacion>{
    
    public static final String TABLE = "especializacion";
    
    public static final String MEDICO = "medico";
    public static final String TITULO = "titulo";
    
    public static final String MARKERS = MARKER+COMA+MARKER;

    @Override
    protected PreparedStatement prepareInsert(Especializacion obj) {
        String query = String.format(INSERT, TABLE, MARKERS);
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, obj.getMedico());
            statement.setString(2, obj.getTitulo());
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected PreparedStatement prepareUpdate(Especializacion obj) {
        String fields = asignValue(TITULO) +  COMA
                ;
        String query = String.format(WHERE, asignValue(MEDICO));
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, obj.getTitulo());
            statement.setString(2, obj.getMedico());
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected PreparedStatement prepareDelete(Especializacion obj) {
       String query  = String.format(DELETE, TABLE)
                + String.format(WHERE, asignValue(MEDICO) + AND + asignValue(TITULO));
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, obj.getMedico());
            statement.setString(1, obj.getTitulo());
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;    }

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
    protected Especializacion setObject(ResultSet result) {
        Especializacion found = new Especializacion();
        try {
            if (result.first() && result.isLast()) {
                found.setMedico(result.getString(MEDICO));
                found.setTitulo(result.getString(TITULO));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            found = null;
        }
        return found;
    }
    
}
