/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Consulta;

/**
 *
 * @author DANIEL
 */
public class DaoConsulta extends DaoCitas<Consulta> {

    public static final String TABLE = "consulta";

    public static final String CODIGO = "codigo";
    public static final String MEDICO = "medico";
    public static final String ESPECIALIDAD = "especialidad";
    public static final String DESCRIPCION = "descripcion";

    public static final String MARKERS = MARKER+COMA+MARKER + COMA + MARKER + COMA + MARKER;

    @Override
    protected PreparedStatement prepareInsert(Consulta obj) {
        String query = String.format(INSERT, TABLE, MARKERS);
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, obj.getCodigo());
            statement.setString(2, obj.getMedico());
            statement.setString(3, obj.getDescripcion());
            statement.setString(4, obj.getEspecialidad());
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected PreparedStatement prepareUpdate(Consulta obj) {
        String fields = asignValue(MEDICO) + COMA
                + asignValue(ESPECIALIDAD) + COMA
                + asignValue(DESCRIPCION);
        String query = String.format(UPDATE, TABLE, fields)
                + String.format(WHERE, asignValue(CODIGO));
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, obj.getMedico());
            statement.setString(2, obj.getEspecialidad());
            statement.setString(3, obj.getDescripcion());
            statement.setString(4, obj.getCodigo());
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    @Override
    protected PreparedStatement prepareDelete(Consulta obj) {
        String query = String.format(DELETE, TABLE)
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
    protected Consulta setObject(ResultSet result) {
        Consulta found = new Consulta();
        try {
            if (result.first() && result.isLast()) {
                found.setCodigo(result.getString(CODIGO));
                found.setMedico(result.getString(MEDICO));
                found.setEspecialidad(result.getString(ESPECIALIDAD));
                found.setDescripcion(result.getString(DESCRIPCION));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            found = null;
        }
        return found;
    }

}
