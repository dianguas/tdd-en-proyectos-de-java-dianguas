package com.tt1.test;

public class Repositorio {

    private DBStub bd;

    public Repositorio(DBStub bd) {
        this.bd = bd;
    }

    public Repositorio() {
        this.bd = new DBStub();
    }

    public String buscarServicio(String nombre) {
        ToDo t = bd.READ(nombre);
        if (t != null) {
            return t.getNombre();
        }
        return null;
    }

    public boolean completadoServicio(ToDo servicio) {
        servicio.setCompletado(true);
        return bd.UPDATE(servicio);
    }

    public boolean agnadirServicio(ToDo servicio) {
        return bd.CREATE(servicio);
    }

    public boolean agnadirCorreo(String correo) {
        return bd.agnadirCorreo(correo);
    }

    public java.util.List<ToDo> getTareas() {
        return bd.getServicios();
    }

    public java.util.Collection<String> getCorreos() {
        return bd.getCorreos();
    }
}