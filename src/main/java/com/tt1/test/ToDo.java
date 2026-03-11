package com.tt1.test;

import java.time.LocalDate;

public class ToDo {
    private String nombre;
    private String descripcion;
    private LocalDate fechaL;
    private boolean completado;

    public ToDo(String n, String desc, LocalDate fecha, boolean complet) {
        this.nombre = n;
        this.descripcion = desc;
        this.fechaL = fecha;
        this.completado = complet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaL() {
        return fechaL;
    }

    public void setFechaL(LocalDate fechaL) {
        this.fechaL = fechaL;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }
}