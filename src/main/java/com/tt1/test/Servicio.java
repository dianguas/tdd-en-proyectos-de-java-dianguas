package com.tt1.test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

public class Servicio {

    private Repositorio repositorio;
    private MailerStub mailer;

    public Servicio(Repositorio repositorio, MailerStub mailer) {
        this.repositorio = repositorio;
        this.mailer = mailer;
    }

    public Servicio() {
        this.repositorio = new Repositorio();
        this.mailer = new MailerStub();
    }

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

    public void agnadirCorreo(String correo) {
        if (correo == null || !correo.contains("@")) {
            System.out.println("Error: correo electrónico no válido.");
            return;
        }
        repositorio.agnadirCorreo(correo);
        alertarTareasVencidas();
    }

    public void finalizarTarea(ToDo servicio) {
        repositorio.completadoServicio(servicio);
        alertarTareasVencidas();
    }

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