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
    
    
    public static void leer(InputStream stream){
        List<String> lines = new ArrayList<>();
        List<String> outcome = new ArrayList<>();
        
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
            if (!ControladorRegistros.registrar(line)) {
                outcome.add("Registrado" + line);
            } else {
                outcome.add("Error, verifique que los datos no sean repetidos o tengan el formato incorrecto" + line);
            }
        }
    }
}

    /**
     * Tengo opening tags
     * Closing tags
     * Parent tags
     * Child tags
     * Informacion adentro de las tags
     */