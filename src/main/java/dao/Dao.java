package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DANIEL
 */
public abstract class Dao<T> {

    private final String SCHEME = "hospital";
    private final String URL = "jdbc:mysql://localhost:3306/" + SCHEME + "?useSSL=false";
    private final String USER = "root";
    private final String PASSWORD = "root";

    protected static Connection CONNECTION;
    protected final String INSERT = "INSERT INTO %s VALUES (%s)";
    protected final String UPDATE = "UPDATE %s SET %s";
    protected final String DELETE = "DELETE FROM %s";
    protected final String SELECT = "SELECT %s FROM %s";
    protected final String WHERE = " WHERE %s";
    public final static String ALL = "*";

    public final static String SET = "SET ";
    public final static String EQUALS = " = ";
    public final static String QUOTE = "\'";
    public final static String COMA = ",";
    public final static String AND = " AND ";
    public final static String DEFAULT = "default";

    public final static String MARKER = "?";

    protected final String ASIGNMENT = "%s" + EQUALS + MARKER;
    private String generatedKey;
    private String error;

    public Dao() {
        connect();
    }

    public static void close() {
        try {
            if (CONNECTION != null) {
                CONNECTION.close();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getConnection() {
        if (CONNECTION == null) {
            connect();
        }
        return CONNECTION;
    }

    private void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            CONNECTION = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean updateDB(PreparedStatement query) {
        try {
            System.out.println(query);
            query.executeUpdate();

//            ResultSet generatedKeys = query.getGeneratedKeys();
//            if (generatedKeys.first() && generatedKeys.isLast()) {
//                generatedKey = generatedKeys.getString(1);
//            }
            query.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean insert(T obj) {
        return updateDB(prepareInsert(obj));
    }

    public boolean update(T obj) {
        return updateDB(prepareUpdate(obj));
    }

    public boolean delete(T obj) {
        return updateDB(prepareDelete(obj));
    }

    protected ResultSet select(PreparedStatement query) {
        try {
            System.out.println(query);
            return query.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public T select(String condition) {
        PreparedStatement query = prepareSelect(ALL, condition);
        T found = null;
        try ( ResultSet result = select(query)) {
            found = setObject(result);
        } catch (SQLException e) {
            e.getMessage();
        }
        return found;
    }

//    public abstract T select(T obj);
    public ArrayList<String[]> multipleSelect(String fields, String condition) {
        ResultSet result = select(prepareSelect(fields, condition));
        ArrayList<String[]> rows = new ArrayList<>();

        try {
            int columns = result.getMetaData().getColumnCount();
            while (result.next()) {
                String[] row = new String[columns];
                for (int i = 1; i <= columns; i++) {
                    row[i - 1] = result.getObject(i).toString();
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
        }
        return null;
    }

    public String setValue(String value) {
        return (value.isBlank()) ? "N/A" : value;
    }

    public Number setValue(Number value) {
        return (value.intValue() < 0) ? 0.0 : value;
    }

    public String asignValue(String campo) {
        return String.format(ASIGNMENT, campo);
    }

    public String getError() {
        return this.error;
    }

    public String getGeneratedKey() {
        return this.generatedKey;
    }

    protected abstract PreparedStatement prepareInsert(T obj);

    protected abstract PreparedStatement prepareUpdate(T obj);

    protected abstract PreparedStatement prepareDelete(T obj);

    protected abstract PreparedStatement prepareSelect(String fields, String condition);

    protected abstract T setObject(ResultSet result);

//    public abstract String tabla();
}
