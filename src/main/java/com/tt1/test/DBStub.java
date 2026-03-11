package com.tt1.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DBStub {

    private List<ToDo> servicios = new ArrayList<>();
    private Collection<String> correos = new ArrayList<>();

    public boolean CREATE(ToDo servicio) {
        return servicios.add(servicio);
    }

    public ToDo READ(String nombre) {
        for (ToDo t : servicios) {
            if (t.getNombre().equals(nombre)) {
                return t;
            }
        }
        return null;
    }

    public boolean UPDATE(ToDo servicio) {
        for (int i = 0; i < servicios.size(); i++) {
            if (servicios.get(i).getNombre().equals(servicio.getNombre())) {
                servicios.set(i, servicio);
                return true;
            }
        }
        return false;
    }

    // Elimina un ToDo de la lista
    public void DELETE(ToDo servicio) {
        servicios.remove(servicio);
    }

    // Añade un correo a la agenda
    public boolean agnadirCorreo(String correo) {
        return correos.add(correo);
    }

    // Devuelve el correo si existe en la agenda
    public String getCorreo(String correo) {
        for (String c : correos) {
            if (c.equals(correo)) {
                return c;
            }
        }
        return null;
    }

    // Reemplaza un correo viejo por uno nuevo
    public boolean updateCorreo(String correoV, String correoN) {
        if (correos.remove(correoV)) {
            return correos.add(correoN);
        }
        return false;
    }

    // Elimina un correo de la agenda
    public void deleteCorreo(String correo) {
        correos.remove(correo);
    }

    // Devuelve todos los correos (útil para Servicio)
    public Collection<String> getCorreos() {
        return correos;
    }

    // Devuelve todos los ToDos (útil para Servicio)
    public List<ToDo> getServicios() {
        return servicios;
    }
}