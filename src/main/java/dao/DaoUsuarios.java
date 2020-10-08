/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.Usuario;

/**
 *
 * @author DANIEL
 * @param <T>
 */
public abstract class DaoUsuarios<T extends Usuario> extends Dao<T> {

    private final DaoUsuario daoUsuario = new DaoUsuario();

    @Override
    public boolean insert(T obj) {
        return daoUsuario.insert(obj) && super.insert(obj);
    }

    @Override
    public boolean update(T obj) {
        return daoUsuario.update(obj) && super.update(obj);
    }

    @Override
    public boolean delete(T obj) {
        return daoUsuario.delete(obj) && super.delete(obj);
    }

    @Override
    public T select(String condition) {
        T found = super.select(condition);
        Usuario user = daoUsuario.select(
                "email = '" + found.getEmail() + "'");
        found.setDpi(user.getDpi());
        found.setNombre(user.getNombre());
        found.setTelefono(user.getTelefono());
        found.setTipo(user.getTipo());
        return found;
    }
}
