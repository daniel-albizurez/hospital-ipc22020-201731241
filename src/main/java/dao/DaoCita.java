/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Cita;

/**
 *
 * @author DANIEL
 */
public class DaoCita extends Dao<Cita>{
    
    public static final String TABLE = "cita";
    
    public static final String CODIGO = "codigo";
    public static final String FECHA = "fecha";
    public static final String HORA = "hora";
    public static final String PACIENTE = "paciente";
    public static final String TIPO = "tipo";
    public static final String COSTO = "costo";
    ;

    private static final String MARKERS = MARKER+COMA+MARKER+COMA+MARKER+COMA
            +                             MARKER+COMA+MARKER+COMA+MARKER
            ;
    
    @Override
    protected PreparedStatement prepareInsert(Cita obj) {
        String query = String.format(INSERT, TABLE, MARKERS);
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, obj.getCodigo());
            statement.setString(2, obj.getFecha());
            statement.setString(3, obj.getHora());
            statement.setString(4, obj.getPaciente());
            statement.setInt(5, obj.getTipo());
            statement.setDouble(6, obj.getCosto());
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected PreparedStatement prepareUpdate(Cita obj) {
        String fields = asignValue(FECHA) + COMA
                + asignValue(HORA) + COMA
                + asignValue(PACIENTE) + COMA
                + asignValue(TIPO) + COMA
                + asignValue(COSTO) + COMA
                ;
        String query = String.format(UPDATE, TABLE, fields)
                + String.format(WHERE, asignValue(CODIGO));
        
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, obj.getFecha());
            statement.setString(2, obj.getHora());
            statement.setString(3, obj.getPaciente());
            statement.setInt(4, obj.getTipo());
            statement.setDouble(5, obj.getCosto());
            statement.setString(6, obj.getCodigo());
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected PreparedStatement prepareDelete(Cita obj) {
        String query = String.format(DELETE, TABLE)
                + String.format(WHERE, asignValue(CODIGO));
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, obj.getCodigo());
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
    protected Cita setObject(ResultSet result) {
        Cita found = new Cita();
        try {
            if (result.first() && result.isLast()) {
                found.setCodigo(result.getString(CODIGO));
                found.setFecha(result.getString(FECHA));
                found.setHora(result.getString(HORA));
                found.setPaciente(result.getString(PACIENTE));
                found.setTipo(result.getInt(TIPO));
                found.setCosto(result.getDouble(COSTO));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            found = null;
        }
        return found;
    }
    
}
