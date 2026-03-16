package com.tt1.test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

/**
 * Capa de lógica de negocio del sistema de tareas.
 * Gestiona la creación, finalización y consulta de tareas,
 * y envía alertas por correo cuando hay tareas vencidas.
 */
public class Servicio {

    private Repositorio repositorio;
    private MailerStub mailer;

    /**
     * Crea un servicio con las dependencias proporcionadas.
     *
     * @param repositorio Repositorio de acceso a datos a utilizar.
     * @param mailer      Servicio de envío de correos a utilizar.
     */
    public Servicio(Repositorio repositorio, MailerStub mailer) {
        this.repositorio = repositorio;
        this.mailer = mailer;
    }

    /**
     * Crea un servicio con dependencias por defecto.
     */
    public Servicio() {
        this.repositorio = new Repositorio();
        this.mailer = new MailerStub();
    }

    /**
     * Crea una nueva tarea y la almacena en el repositorio.
     * Si el nombre es nulo o está vacío, se muestra un error y no se crea la tarea.
     * Tras crear la tarea, comprueba si hay tareas vencidas y alerta por correo.
     *
     * @param nombre Nombre de la nueva tarea. No puede ser nulo ni vacío.
     * @param hora   Hora de creación de la tarea (informativa).
     */
    public void crearServicio(String nombre, LocalTime hora) {
        if (nombre == null || nombre.isBlank()) {
            System.out.println("Error: el nombre no puede estar vacío.");
            return;
        }
        LocalDate fecha = LocalDate.now();
        ToDo nuevo = new ToDo(nombre, "Sin descripción", fecha, false);
        repositorio.agnadirServicio(nuevo);
        alertarTareasVencidas();
    }

    /**
     * Registra una dirección de correo para recibir alertas de tareas vencidas.
     * Si el correo no es válido (nulo o sin '@'), se muestra un error.
     *
     * @param correo Dirección de correo electrónico a registrar.
     */
    public void agnadirCorreo(String correo) {
        if (correo == null || !correo.contains("@")) {
            System.out.println("Error: correo electrónico no válido.");
            return;
        }
        repositorio.agnadirCorreo(correo);
        alertarTareasVencidas();
    }

    /**
     * Marca una tarea como finalizada y comprueba tareas vencidas.
     *
     * @param servicio Tarea a marcar como completada.
     */
    public void finalizarTarea(ToDo servicio) {
        repositorio.completadoServicio(servicio);
        alertarTareasVencidas();
    }

    /**
     * Muestra por consola todas las tareas pendientes (no completadas).
     * También comprueba si hay tareas vencidas y envía alertas.
     */
    public void mostrarTareas() {
        System.out.println("=== Tareas pendientes ===");
        List<ToDo> tareas = repositorio.getTareas();
        boolean hayPendientes = false;
        for (ToDo t : tareas) {
            if (!t.isCompletado()) {
                System.out.println("  - " + t.getNombre() + " (límite: " + t.getFechaL() + ")");
                hayPendientes = true;
            }
        }
        if (!hayPendientes) {
            System.out.println("  (No hay tareas pendientes)");
        }
        alertarTareasVencidas();
    }

    private void alertarTareasVencidas() {
        List<ToDo> tareas = repositorio.getTareas();
        Collection<String> correos = repositorio.getCorreos();

        for (ToDo t : tareas) {
            if (!t.isCompletado() && t.getFechaL().isBefore(LocalDate.now())) {
                for (String correo : correos) {
                    mailer.enviarCorreo(
                        "sistema@todoapp.es",
                        correo,
                        "ALERTA: La tarea '" + t.getNombre() + "' está vencida desde " + t.getFechaL()
                    );
                }
            }
        }
    }
}