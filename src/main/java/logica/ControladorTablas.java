/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;

/**
 *
 * @author DANIEL
 */
public class ControladorTablas {

    public static String[] generateRows(ArrayList<String[]> data) {
        String[] rows = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {
            rows[i] = "\n<tr>";
            for (String column : data.get(i)) {
                column = "\n\t<td>" + column + "</td>";
                rows[i] += column;
            }
            rows[i] += "\n</tr>";
        }
        return rows;
    }

    public static String generateTable(String headers, ArrayList<String[]> data) {
        return generateTable(headers.split(","), data);
    }

    public static String generateTable(String[] headers, ArrayList<String[]> data) {
        String table = "<table>\n<thead>\n<tr>";
        if (data == null || data.isEmpty()) {
            table += "<td>No hay resultados</td>";
            table += "\n</tr>\n</thead>\n<table>";
        } else {
            for (String header : headers) {
                table += "\n\t<th>" + header + "</th>";
            }
            table += "\n</tr>\n</thead>\n<tbody>";
            for (String row : generateRows(data)) {
                table += row;
            }
            table += "\n</tbody>\n</table>";
        }
        return table;
    }

}
