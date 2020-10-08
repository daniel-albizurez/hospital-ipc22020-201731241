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
public class ControladorHTML {

    //Principalmente para medicos y laboratoristas
    public void generateDropdown(ArrayList<String[]> options) {
        String dropdownOption = "";
        //Abrir listado
        for (String[] option : options) {
            //Abrir opcion
            for (String data : option) {
                //Agregar a la opcion
                dropdownOption += "," + data;
            }
            //Agregar la opcion al listado
            //Cerrar la opcion
        }
        //Cerrar el listado
    }

    public static String generateSelectOptions(ArrayList<String[]> options) {
        String selectOptions = "";
        if (options == null || options.isEmpty()) {
            return "<option>No existen opciones para esos parametros</option>\\n";
        }
        for (String[] option : options) {
            String value = option[0];

            selectOptions += "<option value=\"" + value + "\">" + value;
            for (int i = 1; i < option.length; i++) {
                selectOptions += ", "+option[i];
            }
            selectOptions += "</option>\n";
        }
        return selectOptions;
    }
}
