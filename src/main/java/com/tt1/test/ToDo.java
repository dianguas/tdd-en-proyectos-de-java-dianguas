package com.tt1.test;

import java.time.LocalDate;

/**
 * Representa una tarea pendiente (To-Do) del sistema.
 * Almacena el nombre, descripción, fecha límite y estado de completado de la tarea.
 */
public class ToDo {
    private String nombre;
    private String descripcion;
    private LocalDate fechaL;
    private boolean completado;

    /**
     * Crea una nueva tarea con todos sus datos.
     *
     * @param n       Nombre identificativo de la tarea.
     * @param desc    Descripción detallada de la tarea.
     * @param fecha   Fecha límite para completar la tarea.
     * @param complet Estado inicial de completado (true si ya está completada).
     */
    public ToDo(String n, String desc, LocalDate fecha, boolean complet) {
        this.nombre = n;
        this.descripcion = desc;
        this.fechaL = fecha;
        this.completado = complet;
    }

    /**
     * Devuelve el nombre de la tarea.
     *
     * @return Nombre de la tarea.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la tarea.
     *
     * @param nombre Nuevo nombre de la tarea.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la descripción de la tarea.
     *
     * @return Descripción de la tarea.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción de la tarea.
     *
     * @param descripcion Nueva descripción de la tarea.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Devuelve la fecha límite de la tarea.
     *
     * @return Fecha límite como {@link LocalDate}.
     */
    public LocalDate getFechaL() {
        return fechaL;
    }

    /**
     * Establece la fecha límite de la tarea.
     *
     * @param fechaL Nueva fecha límite.
     */
    public void setFechaL(LocalDate fechaL) {
        this.fechaL = fechaL;
    }

    /**
     * Indica si la tarea está completada.
     *
     * @return true si la tarea está completada, false en caso contrario.
     */
    public boolean isCompletado() {
        return completado;
    }

    /**
     * Marca la tarea como completada o pendiente.
     *
     * @param completado true para marcarla como completada, false para pendiente.
     */
    public void setCompletado(boolean completado) {
        this.completado = completado;
    }
}