/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Horario;

/**
 *
 * @author DANIEL
 */
public class DaoHorario extends Dao<Horario>{
    
    public static final String TABLE = "horario";
    
    public static final String LABORATORISTA = "laboratorista";
    public static final String DIA = "dia";

    public static final String MARKERS = MARKER+COMA+MARKER;

    @Override
    protected PreparedStatement prepareInsert(Horario obj) {
        String query = String.format(INSERT, TABLE, MARKERS);
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, obj.getLaboratorista());
            statement.setString(1, obj.getDia());
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected PreparedStatement prepareUpdate(Horario obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected PreparedStatement prepareDelete(Horario obj) {
                String query  = String.format(DELETE, TABLE)
                + String.format(WHERE, asignValue(LABORATORISTA) + AND + asignValue(DIA));
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, obj.getLaboratorista());
            statement.setString(1, obj.getDia());
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
    protected Horario setObject(ResultSet result) {
        Horario found = new Horario();
        try {
            found.setLaboratorista(result.getString(LABORATORISTA));
            found.setLaboratorista(result.getString(DIA));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            found = null;
        }
        return found;
    }
    
    
}
