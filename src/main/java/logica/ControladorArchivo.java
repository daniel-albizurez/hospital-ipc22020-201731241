/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DANIEL
 */
public class ControladorArchivo {

    public static String leer(InputStream stream) {
        List<String> lines = new ArrayList<>();
        List<String[]> outcome = new ArrayList<>();
        int lineNumber = 0;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo");
            System.out.println(e.getMessage());
        }
        for (String line : lines) {
            lineNumber++;
            if (ControladorRegistros.registrar(line)) {
                line = line.replaceAll("<", " ").replaceAll(">", " ");
                outcome.add(new String[]{"Correcto", line, lineNumber + ""});
            } else {
                line = line.replaceAll("<", " ").replaceAll(">", " ");
                outcome.add(new String[]{"Error, verifique que los datos no sean repetidos o tengan el formato incorrecto ", line, lineNumber + ""});
            }
        }
        return ControladorTablas.generateTable("Resultado, Etiqueta, Linea", (ArrayList) outcome);
    }
}

    /**
     * Tengo opening tags
     * Closing tags
     * Parent tags
     * Child tags
     * Informacion adentro de las tags
     */
