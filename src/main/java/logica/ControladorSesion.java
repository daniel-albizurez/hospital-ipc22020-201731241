/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import dao.*;
import java.util.ArrayList;
import modelo.Medico;
import modelo.Usuario;

/**
 *
 * @author DANIEL
 */
public class ControladorSesion {

    private static final DaoUsuario daoUsuario = new DaoUsuario();
    private static Usuario usuario;
    private static DaoUsuarios daoTipoUsuario;

    public static Usuario iniciarSesion(String email, String pass) {
        String condicion = DaoUsuario.EMAIL + Dao.EQUALS + Dao.QUOTE + email + Dao.QUOTE;
        ArrayList<String[]> datos = daoUsuario.multipleSelect(DaoUsuario.TIPO + DaoUsuario.COMA + DaoUsuario.PASSWORD, condicion);
        if (datos != null && datos.size() > 0) {
            int tipoUsuario = Integer.parseInt(datos.get(0)[0]);
//            usuario = daoUsuario.select(condicion);
            if (!datos.get(0)[1].equals(pass)) {
                usuario = null;
            } else {
                usuario = getByEmail(email);
            }
        }
        return usuario;
    }

    public static Usuario getByEmail(String email) {
        String condicion = DaoUsuario.EMAIL + Dao.EQUALS + Dao.QUOTE + email + Dao.QUOTE;
        ArrayList<String[]> datos = daoUsuario.multipleSelect(DaoUsuario.TIPO + DaoUsuario.COMA + DaoUsuario.PASSWORD, condicion);
        if (datos != null && datos.size() > 0) {
            int tipoUsuario = Integer.parseInt(datos.get(0)[0]);
            switch (tipoUsuario) {
                case 1:
                    daoTipoUsuario = new DaoAdministrador();
                    usuario = daoTipoUsuario.select(condicion);
                    break;
                case 2:
                    daoTipoUsuario = new DaoMedico();
                    usuario = daoTipoUsuario.select(condicion);
                    System.out.println(((Medico) usuario).getCodigo());
                    break;
                case 3:
                    daoTipoUsuario = new DaoLaboratorista();
                    usuario = daoTipoUsuario.select(condicion);
                    break;
                case 4:
                    daoTipoUsuario = new DaoPaciente();
                    usuario = daoTipoUsuario.select(condicion);
                    break;
            }
        }
    return usuario ;

}
}
