/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Examen;

/**
 *
 * @author DANIEL
 */
public class DaoExamen extends Dao<Examen> {

    public static final String TABLE = "examen";

    public static final String CODIGO = "codigo";
    public static final String NOMBRE = "nombre";
    public static final String DESCRIPCION = "descripcion";
    public static final String COSTO = "costo";
    public static final String TIPO_INFORME = "tipo_informe";
    public static final String REQUIERE_ORDEN = "requiere_orden";

    public static final String MARKERS = MARKER + COMA + MARKER + COMA + MARKER + COMA
            + MARKER + COMA + MARKER + COMA + MARKER;

    @Override
    protected PreparedStatement prepareInsert(Examen obj) {
        String query = String.format(INSERT, TABLE, MARKERS);
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, obj.getCodigo());
            statement.setString(2, obj.getNombre());
            statement.setString(3, obj.getDescripcion());
            statement.setDouble(4, obj.getCosto());
            statement.setString(5, obj.getTipo_informe());
            statement.setBoolean(5, obj.requiere_orden());
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected PreparedStatement prepareUpdate(Examen obj) {
        String fields = asignValue(NOMBRE) + COMA
                + asignValue(DESCRIPCION) + COMA
                + asignValue(COSTO) + COMA
                + asignValue(TIPO_INFORME) + COMA
                + asignValue(REQUIERE_ORDEN);
        String query = String.format(UPDATE, TABLE, fields)
                + String.format(WHERE, asignValue(CODIGO));
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, obj.getNombre());
            statement.setString(2, obj.getDescripcion());
            statement.setDouble(3, obj.getCosto());
            statement.setString(4, obj.getTipo_informe());
            statement.setBoolean(5, obj.requiere_orden());
            statement.setString(6, obj.getCodigo());
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected PreparedStatement prepareDelete(Examen obj) {
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
    protected Examen setObject(ResultSet result) {
        Examen found = new Examen();
        try {
            if (result.first() && result.isLast()) {
                found.setCodigo(result.getString(CODIGO));
                found.setNombre(result.getString(NOMBRE));
                found.setDescripcion(result.getString(DESCRIPCION));
                found.setCosto(result.getDouble(COSTO));
                found.setTipo_informe(result.getString(TIPO_INFORME));
                found.setRequiere_orden(result.getBoolean(REQUIERE_ORDEN));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            found = null;
        }
        return found;
    }

}
