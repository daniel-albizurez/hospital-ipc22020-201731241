/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dao.DaoAdministrador;
import dao.DaoEspecializacion;
import dao.DaoMedico;
import dao.DaoVista;
import java.util.Arrays;
import logica.ControladorCitas;
import logica.ControladorHTML;
import logica.ControladorRegistros;
import logica.ControladorTablas;
import modelo.Administrador;
import modelo.Medico;

/**
 *
 * @author DANIEL
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        Administrador admin =  new Administrador();
//        DaoAdministrador dao = new DaoAdministrador();
//        
//        admin.setCodigo("A-1");
//        admin.setEmail("a1@amail.com");
//        admin.setDpi("01");
//        admin.setNombre("Administrador de Prueba");
//        admin.setTelefono("12");
//        admin.setPassword("holAdmin");
//        
//        dao.insert(admin);
//        
//        Medico m = new Medico();
//        DaoMedico dao = new DaoMedico();
        
//        m.setCodigo("M-2");
//        m = dao.select("codigo='"+m.getCodigo()+"'");
//        m.setEmail("m3@mmail.com");
//        m.setNo_colegiado("503");
//        m.setHora_inicio("08");
//        m.setHora_fin("20");
//        m.setFecha_inicio("2005-12-20");
//        m.setNombre("3ra Prueba");
//        m.setDpi("3");
//        m.setTelefono("10");
//        m.setPassword("Hola");
        
//        dao.update(m);
//        System.out.println(m.getNombre());

//        DaoEspecializacion dao1 = new DaoEspecializacion();
//        DaoMedico dao2 = new DaoMedico();
//        DaoVista daoVista = new DaoVista();
//        System.out.println(ControladorHTML.generateSelectOptions(daoVista.multipleSelect("titulos_medico", "titulo='pediatria'")));

//        ControladorCitas.agendarConsulta("c-6", "m-1", "pediatria", "p-1", "08", "2020/10/07");
//        ControladorCitas.agendarExamen("r-6", "l-1", "e1", "p-1", "10", "2020/10/07", "");


ControladorRegistros.registrar("<admin>");
   ControladorRegistros.registrar("<CODIGO>ADMIN1</CODIGO>");
   ControladorRegistros.registrar("<DPI>69712516133</DPI>");
   ControladorRegistros.registrar("<NOMBRE>Diego Domiguez</NOMBRE>");
   ControladorRegistros.registrar("<PASSWORD>dfDrVH~9a#-7)^w^P9pB</PASSWORD>");
ControladorRegistros.registrar("</admin>");
    }
    
}
