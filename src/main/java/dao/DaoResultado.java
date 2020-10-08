/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Resultado;

/**
 *
 * @author DANIEL
 */
public class DaoResultado extends DaoCitas<Resultado> {

    public static final String TABLE = "resultado";

    public static final String CODIGO = "codigo";
    public static final String LABORATORISTA = "laboratorista";
    public static final String EXAMEN = "examen";
    public static final String INFORME = "informe";
    public static final String ORDEN = "orden";

    public static final String MARKERS = MARKER + COMA + MARKER + COMA + MARKER + COMA
            + MARKER + COMA + MARKER;

    @Override
    protected PreparedStatement prepareInsert(Resultado obj) {
        String query = String.format(INSERT, TABLE, MARKERS);
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, obj.getCodigo());
            statement.setString(2, obj.getLaboratorista());
            statement.setString(3, obj.getExamen());
            statement.setString(4, obj.getInforme());
            statement.setString(5, obj.getOrden());
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected PreparedStatement prepareUpdate(Resultado obj) {
        String fields = asignValue(LABORATORISTA) + COMA
                + asignValue(EXAMEN) + COMA
                + asignValue(INFORME) + COMA
                + asignValue(ORDEN);
        String query = String.format(WHERE, TABLE, fields)
                + String.format(WHERE, asignValue(CODIGO));
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, obj.getLaboratorista());
            statement.setString(2, obj.getExamen());
            statement.setString(3, obj.getInforme());
            statement.setString(4, obj.getOrden());
            statement.setString(5, obj.getCodigo());
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected PreparedStatement prepareDelete(Resultado obj) {
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
    protected Resultado setObject(ResultSet result) {
        Resultado found = new Resultado();
        try {
            if (result.first() && result.isLast()) {
                found.setCodigo(result.getString(CODIGO));
                found.setLaboratorista(result.getString(LABORATORISTA));
                found.setExamen(result.getString(EXAMEN));
                found.setInforme(result.getString(INFORME));
                found.setOrden(result.getString(ORDEN));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            found = null;
        }
        return found;
    }

}
