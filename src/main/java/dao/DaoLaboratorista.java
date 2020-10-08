/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Laboratorista;

/**
 *
 * @author DANIEL
 */
public class DaoLaboratorista extends DaoUsuarios<Laboratorista> {

    public static final String TABLE = "laboratorista";

    public static final String CODIGO = "codigo";
    public static final String EXAMEN = "examen";
    public static final String EMAIL = "email";
    public static final String REGISTRO = "registro";
    public static final String INICIO_LABORES = "inicio_labores";

    private static final String MARKERS = MARKER + COMA + MARKER + COMA
            + MARKER + COMA + MARKER + COMA
            + MARKER;

    ;

    @Override
    protected PreparedStatement prepareInsert(Laboratorista obj) {
        String query = String.format(INSERT, TABLE, MARKERS);
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, obj.getCodigo());
            statement.setString(2, obj.getExamen());
            statement.setString(3, obj.getEmail());
            statement.setString(4, obj.getNo_registro());
            statement.setString(5, obj.getFecha_inicio());
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected PreparedStatement prepareUpdate(Laboratorista obj) {
        String fields = asignValue(EXAMEN) + COMA
                + asignValue(EMAIL) + COMA
                + asignValue(REGISTRO) + COMA
                + asignValue(INICIO_LABORES);
        String query = String.format(UPDATE, TABLE, fields)
                + String.format(WHERE, asignValue(CODIGO));
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, obj.getExamen());
            statement.setString(2, obj.getEmail());
            statement.setString(3, obj.getNo_registro());
            statement.setString(4, obj.getFecha_inicio());
            statement.setString(5, obj.getCodigo());
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected PreparedStatement prepareDelete(Laboratorista obj) {
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
    protected Laboratorista setObject(ResultSet result) {
        Laboratorista found = new Laboratorista();
        try {
            if (result.first() && result.isLast()) {
                found.setCodigo(result.getString(CODIGO));
                found.setExamen(result.getString(EXAMEN));
                found.setEmail(result.getString(EMAIL));
                found.setNo_registro(result.getString(REGISTRO));
                found.setFecha_inicio(result.getString(INICIO_LABORES));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            found = null;
        }
        return found;
    }

}
