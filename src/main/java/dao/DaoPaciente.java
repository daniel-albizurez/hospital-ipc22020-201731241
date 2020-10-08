/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Paciente;

/**
 *
 * @author DANIEL
 */
public class DaoPaciente extends DaoUsuarios<Paciente> {

    public static final String TABLE = "paciente";

    public static final String CODIGO = "codigo";
    public static final String EMAIL = "email";
    public static final String NACIMIENTO = "nacimiento";
    public static final String GRUPO_SANGUINEO = "grupo_sanguineo";
    public static final String SEXO = "sexo";
    public static final String PESO = "peso";

    private static final String MARKERS = MARKER + COMA + MARKER + COMA + MARKER + COMA
            + MARKER + COMA + MARKER + COMA + MARKER;

    @Override
    protected PreparedStatement prepareInsert(Paciente obj) {
        String query = String.format(INSERT, TABLE, MARKERS);
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, obj.getCodigo());
            statement.setString(2, obj.getEmail());
            statement.setString(3, obj.getNacimiento());
            statement.setString(4, obj.getGrupo_sanguineo());
            statement.setString(5, obj.getSexo());
            statement.setDouble(6, obj.getPeso());
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    @Override
    protected PreparedStatement prepareUpdate(Paciente obj) {
        String fields = asignValue(EMAIL) + COMA
                + asignValue(NACIMIENTO) + COMA
                + asignValue(GRUPO_SANGUINEO) + COMA
                + asignValue(SEXO) + COMA
                + asignValue(PESO);
        String query = String.format(UPDATE, TABLE, fields)
                + String.format(WHERE, asignValue(CODIGO));
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setString(1, obj.getEmail());
            statement.setString(2, obj.getNacimiento());
            statement.setString(3, obj.getGrupo_sanguineo());
            statement.setString(4, obj.getSexo());
            statement.setDouble(5, obj.getPeso());
            statement.setString(6, obj.getCodigo());
            return statement;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected PreparedStatement prepareDelete(Paciente obj) {
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
    protected Paciente setObject(ResultSet result) {
        Paciente found = new Paciente();
        try {
            if (result.first() && result.isLast()) {
                found.setCodigo(result.getString(CODIGO));
                found.setEmail(result.getString(EMAIL));
                found.setNacimiento(result.getString(NACIMIENTO));
                found.setGrupo_sanguineo(result.getString(GRUPO_SANGUINEO));
                found.setSexo(result.getString(SEXO));
                found.setPeso(result.getDouble(PESO));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            found = null;
        }
        return found;
    }

}
