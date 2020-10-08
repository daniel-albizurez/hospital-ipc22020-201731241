/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DANIEL
 */
public class DaoVista extends Dao {

    protected PreparedStatement prepareSelect(String vista, String fields, String condition) {
        String query = String.format(SELECT, fields, vista);
        query += (condition == null || condition.isBlank()) ? "" : String.format(WHERE, condition);
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            return statement;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    protected PreparedStatement prepareSelect(String vista, String condition) {
        return prepareSelect(vista, ALL, condition); 
    }

    @Override
    public ArrayList<String[]> multipleSelect(String vista, String condition) {
        ResultSet result = select(prepareSelect(vista, condition));
        return generateArrayList(result);
    }

    public ArrayList<String[]> generateArrayList(ResultSet result) {
        ArrayList<String[]> rows = new ArrayList<>();
        if (result != null) {

            try {
                int columns = result.getMetaData().getColumnCount();
                while (result.next()) {
                    String[] row = new String[columns];
                    for (int i = 1; i <= columns; i++) {
                        row[i - 1] = (result.getObject(i) != null) ? result.getObject(i).toString() : "";
                        if (String.valueOf(result.getObject(i)).equals("true")) {
                            row[i - 1] = "1";
                        } else if (String.valueOf(result.getObject(i)).equals("false")) {
                            row[i - 1] = "0";
                        }
                    }
                    rows.add(row);
                }
                result.close();
                return rows;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<String[]> multipleSelect(String vista, String conditionField, String conditionValue) {
        ResultSet result = select(prepareSelect(vista, conditionField, conditionValue));
        return generateArrayList(result);
    }

    @Override
    protected PreparedStatement prepareInsert(Object arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected PreparedStatement prepareUpdate(Object arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected PreparedStatement prepareDelete(Object arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object setObject(ResultSet arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
