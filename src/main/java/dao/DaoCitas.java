/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.Cita;

/**
 *
 * @author DANIEL
 * @param <T>
 */
public abstract class DaoCitas<T extends Cita> extends Dao<T> {

    private final DaoCita daoCita = new DaoCita();

    @Override
    public boolean insert(T obj) {
        boolean success;
        try {
            CONNECTION.setAutoCommit(false);
            if ((success = (daoCita.insert(obj) && super.insert(obj)))) {
                CONNECTION.commit();
            } else {
                CONNECTION.rollback();
            }
            CONNECTION.setAutoCommit(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            success = false;
        }
        return success;
    }

    @Override
    public boolean update(T obj) {
        return daoCita.update(obj) && super.update(obj);
    }

    @Override
    public boolean delete(T obj) {
        return daoCita.delete(obj) && super.delete(obj);
    }

    @Override
    public T select(String condition) {
        T found = super.select(condition);
        Cita appointment = daoCita.select(condition);
        found.setFecha(appointment.getFecha());
        found.setHora(appointment.getHora());
        found.setPaciente(appointment.getPaciente());
        found.setTipo(appointment.getTipo());
        found.setCosto(appointment.getCosto());
        return found;
    }

}
