/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import dao.*;
import java.util.ArrayList;
import modelo.*;

/**
 *
 * @author DANIEL
 */
public class ControladorRegistros {

//  Etiqueta "Padre" abierta actualmente   
    private static String parentTag;
    private static final String parentTags = "admin doctor laboratorista paciente examen reporte resultado cita consulta";
    private static final String[] possibleParentTags = parentTags.split(" ");
// Etiqueta "Hija" abierta pero contiene mas de un dato interno
    private static String childTag;
    private static final String childTags = "ESPECIALIDAD HORARIO TRABAJO";
    private static final String[] possibleChildTags = childTags.split(" ");
    private static Dao dao;
    private static Dao daoExtra;
    private static Object object;
    private static Object objectExtra;
    private static ArrayList extras;
    private static boolean correcto = true;

    public static boolean registrar(String tag) {
        tag = tag.trim();
//        String child = (childTag == null) ? tag.replaceFirst("<", "").replaceFirst(">", "") : "";
        tag = (parentTag == null) ? tag.replaceFirst("<", "").replaceFirst(">", "") : tag;
        if (tag.contains("xml") || tag.contains("version") || tag.contains("hospital>")) {
            return true;
        }
        if (parentTags.contains(tag)) {
            parentTag = tag;
            correcto = true;
        } else if (tag.contains("/" + parentTag)) {
            if (correcto) {
                correcto &= dao.insert(object);
            }
            if (extras != null) {
                for (Object extra : extras) {
                    correcto &= daoExtra.insert(extra);
                }
                extras = null;
            }
            parentTag = null;
            object = null;
        } /*else if (childTags.contains(child)) {
            childTag = child;
        } else if (tag.contains("/"+childTag)) {
            childTag = null;
        } */else {
            correcto &= saveData(tag);
        }
        return correcto;
    }

    private static boolean saveData(String tag) {
        try {
            String closing = (tag.contains("</")) ? tag.substring(tag.indexOf("</")) : "";
            String opening = tag.substring(0, tag.indexOf(">")+1);
            String data = tag.replace(opening, "").replace(closing, "");
            opening = opening.replace("<", "").replace(">", "");
            closing = closing.replace("<", "").replace(">", "");
                if (closing.equals("/"+opening)) {
                    switch (parentTag) {
    //                    Si la etiqueta padre es igual a
                        case "admin":
    //                    Entonces se define el tipo de objeto que se va a almacenra
    //                    Y se instancia vacio
                            object = (object instanceof Administrador) ? object : new Administrador();
    //                    A la vez se define el objeto que se encargara de almacenarlo en la base de datos
                            dao = (dao instanceof DaoAdministrador ) ? dao : new DaoAdministrador() ;
    //                    Segun las etiqueta que le siga se utilizan los setters del objeto
    //                    Para agregar la informacion en la instancia
                            switch (opening) {
                                case "CODIGO":
                                    ((Administrador)object).setCodigo(data);
                                    break;
                                case "DPI":
                                    ((Administrador)object).setDpi(data);
                                    break;
                                case "NOMBRE":
                                    ((Administrador)object).setNombre(data);
                                    break;
                                case "PASSWORD":
                                    ((Administrador)object).setPassword(data);
                                    break;
                                default:
                                    return false;
                            }
                            break;
                        case "doctor":
                            object = (object instanceof Medico) ? object : new Medico();
                            dao = (dao instanceof DaoMedico ) ? dao : new DaoMedico() ;
                            switch (opening) {
                                case "CODIGO":
                                    ((Medico)object).setCodigo(data);
                                    break;
                                case "NOMBRE":
                                    ((Medico)object).setNombre(data);
                                    break;
                                case "COLEGIADO":
                                    ((Medico)object).setNo_colegiado(data);
                                    break;
                                case "DPI":
                                    ((Medico)object).setDpi(data);
                                    break;
                                case "TELEFONO":
                                    ((Medico)object).setTelefono(data);
                                    break;
                                case "TITULO":
                                    if (childTag.equals(possibleChildTags[0])) {
    //                          Algunos objetos hacen referencia a una o mas tuplas de otros objetos
    //                          Estos objetos extra se almacenan en un arraylist
    //                          Se define el objeto encargado de almacenarlos
    //                          Se deine el arrayList que los contendra
    //                          Y el objeto para almacenar la informacion necesaria e insertar en la lista
    //                          Se utilizan los setters para almacenar los datos recibidos
    //                          Antes de esto se evalua que la etiqueta superior sea la correcta
                                        daoExtra = (daoExtra instanceof DaoEspecializacion ) ? daoExtra : new DaoEspecializacion();
                                        extras = (extras != null) ? extras : new ArrayList<Especializacion>();
                                        objectExtra = new Especializacion();                        
                                        ((Especializacion)objectExtra).setMedico(((Medico)object).getCodigo());
                                        ((Especializacion)objectExtra).setTitulo(data);
                                        extras.add(objectExtra);
                                    } else {
                                        return false;
                                    }
                                        break;
                                case "CORREO":
                                    ((Medico)object).setEmail(data);
                                    break;
                                case "INICIO":
                                    if (childTag.equals(possibleChildTags[1])) {
                                        ((Medico)object).setHora_inicio(data.substring(0, 2));
                                    } else {
                                        return false;
                                    }
                                    break;
                                case "FIN":
                                    if (childTag.equals(possibleChildTags[1])) {
                                        ((Medico)object).setHora_fin(data.substring(0, 2));
                                    } else {
                                        return false;
                                    }
                                    break;
                                case "TRABAJO":
                                    ((Medico)object).setFecha_inicio(data);
                                    break;
                                case "PASSWORD":
                                    ((Medico)object).setPassword(data);
                                    break;
                                default:
                                    return false;
                            }
                            break;
                        case "laboratorista":
                            object = (object instanceof Laboratorista) ?  object : new Laboratorista();
                            dao = (dao instanceof DaoLaboratorista ) ? dao : new DaoLaboratorista() ;
                            switch (opening) {
                                case "CODIGO":
                                    ((Laboratorista)object).setCodigo(data);
                                    break;
                                case "NOMBRE":
                                    ((Laboratorista)object).setNombre(data);
                                    break;
                                case "REGISTRO":
                                    ((Laboratorista)object).setNo_registro(data);
                                    break;
                                case "DPI":
                                    ((Laboratorista)object).setDpi(data);
                                    break;
                                case "TELEFONO":
                                    ((Laboratorista)object).setTelefono(data);
                                    break;
                                case "EXAMEN":
                                    ((Laboratorista)object).setExamen(data);
                                    break;
                                case "CORREO":
                                    ((Laboratorista)object).setEmail(data);
                                    break;
                                case "DIA":
                                    if (childTag.equals(possibleChildTags[2])) {
    //                          Algunos objetos hacen referencia a una o mas tuplas de otros objetos
    //                          Estos objetos extra se almacenan en un arraylist
    //                          Se define el objeto encargado de almacenarlos
    //                          Se deine el arrayList que los contendra
    //                          Y el objeto para almacenar la informacion necesaria e insertar en la lista
    //                          Se utilizan los setters para almacenar los datos recibidos
    //                          Antes de esto se evalua que la etiqueta superior sea la correcta
                                        daoExtra = (daoExtra instanceof DaoHorario ) ? daoExtra : new DaoHorario();
                                        extras = (extras != null) ? extras : new ArrayList<Horario>();
                                        objectExtra = new Horario();                        
                                        ((Horario)objectExtra).setLaboratorista(((Laboratorista)object).getCodigo());
                                        ((Horario)objectExtra).setDia(data);
                                        extras.add(objectExtra);
                                    } else {
                                        return false;
                                    }
                                        break;
                                case "TRABAJOF":
                                    ((Laboratorista)object).setFecha_inicio(data);
                                    break;
                                case "PASSWORD":
                                    ((Laboratorista)object).setPassword(data);
                                    break;
                                default:
                                    return false;

                            }
                            break;
                        case "paciente":
                            object = (object instanceof Paciente) ? object : new Paciente();
                            dao = (dao instanceof DaoPaciente ) ? dao : new DaoPaciente() ;
                            switch (opening) {
                                case "CODIGO":
                                    ((Paciente)object).setCodigo(data);
                                    break;
                                case "NOMBRE":
                                    ((Paciente)object).setNombre(data);
                                    break;
                                case "SEXO":
                                    ((Paciente)object).setSexo(data);
                                    break;
                                case "BIRTH":
                                    ((Paciente)object).setNacimiento(data);
                                    break;
                                case "DPI":
                                    ((Paciente)object).setDpi(data);
                                    break;
                                case "TELEFONO":
                                    ((Paciente)object).setTelefono(data);
                                    break;
                                case "PESO":
                                    ((Paciente)object).setPeso(Double.parseDouble(data));
                                    break;
                                case "SANGRE":
                                    ((Paciente)object).setGrupo_sanguineo(data);
                                    break;
                                case "CORREO":
                                    ((Paciente)object).setEmail(data);
                                    break;
                                case "PASSWORD":
                                    ((Paciente)object).setPassword(data);
                                    break;
                                default:
                                    return false;

                            }
                            break;
                        case "examen":
                            object = (object instanceof Examen) ? object : new Examen();
                            dao = (dao instanceof DaoExamen ) ? dao : new DaoExamen() ;
                            switch (opening) {
                                case "CODIGO":
                                    ((Examen)object).setCodigo(data);
                                    break;
                                case "NOMBRE":
                                    ((Examen)object).setNombre(data);
                                    break;
                                case "ORDEN":
                                    ((Examen)object).setRequiere_orden(Boolean.parseBoolean(data));
                                    break;
                                case "DESCRIPCION":
                                    ((Examen)object).setDescripcion(data);
                                    break;
                                case "COSTO":
                                    ((Examen)object).setCosto(Double.parseDouble(data));
                                    break;
                                case "INFORME":
                                    ((Examen)object).setTipo_informe(data);
                                    break;
                                default:
                                    return false;    
                            }
                            break;
                        case "cita":
                        case "reporte":
                            object = (object instanceof Consulta) ?  object : new Consulta() ;
                            dao = (dao instanceof DaoConsulta ) ? dao : new DaoConsulta() ;
                            switch (opening) {
                                case "CODIGO":
                                    ((Consulta)object).setCodigo(data);
                                    break;
                                case "PACIENTE":
                                    ((Consulta)object).setPaciente(data);
                                    break;
                                case "MEDICO":
                                    ((Consulta)object).setMedico(data);
                                    break;
                                case "INFORME":
                                    ((Consulta)object).setDescripcion(data);
                                    break;
                                case "FECHA":
                                    ((Consulta)object).setFecha(data);
                                    break;
                                case "HORA":
                                    ((Consulta)object).setHora(data.substring(0, 2));
                                    break;
                                case "ESPECIALIDAD":
                                    ((Consulta)object).setEspecialidad(data);
                                    daoExtra = new DaoEspecialidad();
                                    objectExtra = daoExtra.select("titulo='"+data+"'");
                                    ((Consulta)object).setCosto(((Especialidad)objectExtra).getCosto());
                                    daoExtra = null;
                                    objectExtra = null;
                                    break;
                            }
                            break;
                        case "resultado":
                            object = (object instanceof Resultado) ?  object : new Resultado();
                            dao = (dao instanceof DaoResultado ) ? dao : new DaoResultado() ;
                            switch (opening) {
                                case "CODIGO":
                                    ((Resultado)object).setCodigo(data);
                                    break;
                                case "PACIENTE":
                                    ((Resultado)object).setPaciente(data);
                                    break;
                                case "EXAMEN":
                                    ((Resultado)object).setExamen(data);
                                    daoExtra = new DaoExamen();
                                    objectExtra = daoExtra.select("codigo='"+data+"'");
                                    childTag = ((Examen)objectExtra).getTipo_informe() + (((Examen)objectExtra).requiere_orden() ? "ORDEN" : null);
                                    ((Resultado)object).setCosto(((Examen)objectExtra).getCosto());
                                    daoExtra = null;
                                    objectExtra = null;
                                    break;
                                case "LABORATORISTA":
                                    ((Resultado)object).setLaboratorista(data);
                                    break;
                                case "ORDEN":
                                    if (childTag.contains(opening)) {
                                        ((Resultado)object).setOrden(data);
                                    }
                                    break;
                                case "INFORME":
                                     ((Resultado)object).setInforme(data);
                                    if (childTag.contains("IMG")) {
                                        return (data.contains(".png") ||
                                                data.contains(".jpg")||
                                                data.contains(".jpge")
                                                ); 
                                    } else {
                                        return (data.contains(".pdf"));
                                    }
                                case "FECHA":
                                    ((Resultado)object).setFecha(data);
                                    break;
                                case "HORA":
                                    ((Resultado)object).setHora(data.substring(0, 2));
                                    break;
                                default:
                                    return false;    
                            }
                            break;
                        case "consulta":
                            object = (object instanceof Especialidad) ?  object : new Especialidad();
                            dao = (dao instanceof DaoEspecialidad ) ? dao : new DaoEspecialidad();
                            switch (opening) {
                                case "TIPO":
                                    ((Especialidad)object).setTitulo(data);
                                    break;
                                case "COSTO":
                                    ((Especialidad)object).setCosto(Double.parseDouble(data));
                                    break;
                                default:
                                    return false;    
                            }
                            break;
                        default:
                            return false;
                    }
                    return true;
                } else if (tag.contains("ESPECIALIDAD")) {
                    childTag = (closing.equals("/ESPECIALIDAD")) ? null : possibleChildTags[0] ;
                } else if (tag.contains("HORARIO")) {
                    childTag = (closing.equals("/HORARIO")) ? null : possibleChildTags[1] ;
                } else if (tag.contains("TRABAJO")) {
                    childTag = (closing.equals("/TRABAJO")) ? null : possibleChildTags[2] ;
                } else {
                    return false;
                }
        } catch (Exception e) {
            System.out.println("Error en>  " + tag);
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

}
