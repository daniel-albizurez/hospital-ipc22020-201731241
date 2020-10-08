/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Medico;

/**
 *
 * @author DANIEL
 */
public class DaoMedico extends DaoUsuarios<Medico>{

    public static final String TABLE = "medico";

    public static final String CODIGO = "codigo";
    public static final String EMAIL = "email";
    public static final String NO_COLEGIADO = "numero_colegiado";
    public static final String HORA_INICIO = "hora_inicio";
    public static final String HORA_FIN = "hora_fin";
    public static final String INICIO_LABORES = "inicio_labores";

    private static final String MARKERS = MARKER + COMA + MARKER + COMA
            + MARKER + COMA + MARKER + COMA
            + MARKER + COMA + MARKER;
    ;

    @Override
    protected PreparedStatement prepareInsert(Medico obj) {
        String query = String.format(INSERT, TABLE, MARKERS);
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, obj.getCodigo());
            statement.setString(2, obj.getEmail());
            statement.setString(3, obj.getNo_colegiado());
            statement.setString(4, obj.getHora_inicio());
            statement.setString(5, obj.getHora_fin());
            statement.setString(6, obj.getFecha_inicio());
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    @Override
    protected PreparedStatement prepareUpdate(Medico obj) {
        String fields = asignValue(EMAIL) + COMA
                + asignValue(NO_COLEGIADO) + COMA
                + asignValue(HORA_INICIO) + COMA
                + asignValue(HORA_FIN) + COMA
                + asignValue(INICIO_LABORES)
                ;
        String query = String.format(UPDATE, TABLE, fields)
                + String.format(WHERE, asignValue(CODIGO));
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, obj.getEmail());
            statement.setString(2, obj.getNo_colegiado());
            statement.setString(3, obj.getHora_inicio());
            statement.setString(4, obj.getHora_fin());
            statement.setString(5, obj.getFecha_inicio());
            statement.setString(6, obj.getCodigo());
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected PreparedStatement prepareDelete(Medico obj) {
        String query  = String.format(DELETE, TABLE)
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
    protected Medico setObject(ResultSet result) {
        Medico found = new Medico();
        try {
            if (result.first() && result.isLast()) {
                found.setCodigo(result.getString(CODIGO));
                found.setEmail(result.getString(EMAIL));
                found.setNo_colegiado(result.getString(NO_COLEGIADO));
                found.setHora_inicio(result.getString(HORA_INICIO));
                found.setHora_fin(result.getString(HORA_FIN));
                found.setFecha_inicio(result.getString(INICIO_LABORES));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            found = null;
        }
        return found;
    }

}
