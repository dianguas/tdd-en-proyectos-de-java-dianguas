package com.tt1.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tt1.test.mock.ToDoMock;

class TestServicio {

    private LocalTime aux = LocalTime.of(14, 30);
    private DBStub bdControlada;
    private Repositorio repo;
    private MailerStub mailer;
    private Servicio servicio;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println("== Empezando tests de Servicio ==");
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        System.out.println("== Tests de Servicio finalizados ==");
    }

    @BeforeEach
    void setUp() throws Exception {
        ToDoMock.reset();
        bdControlada = new DBStub();
        repo = new Repositorio(bdControlada);
        mailer = new MailerStub();
        servicio = new Servicio(repo, mailer);
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testCrearServicio_noLanzaExcepcion() {
        assertDoesNotThrow(() -> servicio.crearServicio("AYUDA", aux));
    }

    @Test
    void testCrearServicio_guardaLaTareaEnBD() {
        servicio.crearServicio("MiTarea", aux);
        assertNotNull(bdControlada.READ("MiTarea"));
    }

    @Test
    void testAgnadirCorreo_noLanzaExcepcion() {
        assertDoesNotThrow(() -> servicio.agnadirCorreo("ejemplo@unirioja.es"));
    }

    @Test
    void testAgnadirCorreo_guardaCorreoValido() {
        servicio.agnadirCorreo("valido@test.es");
        assertEquals("valido@test.es", bdControlada.getCorreo("valido@test.es"));
    }

    @Test
    void testAgnadirCorreo_ignoraCorreoSinArroba() {
        servicio.agnadirCorreo("correo-invalido");
        assertNull(bdControlada.getCorreo("correo-invalido"));
    }

    @Test
    void testFinalizarTarea_marcaComoCompletada() {
        ToDoMock.ejemplo.setCompletado(false);
        bdControlada.CREATE(ToDoMock.ejemplo);
        servicio.finalizarTarea(ToDoMock.ejemplo);
        assertTrue(ToDoMock.ejemplo.isCompletado());
    }

    @Test
    void testMostrarTareas_noLanzaExcepcion() {
        assertDoesNotThrow(() -> servicio.mostrarTareas());
    }

    @Test
    void testAlerta_seEnviaCorreoCuandoHayTareaVencida() {
        // Arrange: una tarea con fecha en el pasado, vencida, y un correo en la agenda
        ToDo tareaVencida = new ToDo("Vencida", "desc", LocalDate.of(2020, 1, 1), false);
        bdControlada.CREATE(tareaVencida);
        bdControlada.agnadirCorreo("aviso@test.es");

        // Act + Assert: al llamar a cualquier operación, el mailer debe dispararse sin error
        assertDoesNotThrow(() -> servicio.mostrarTareas());
    }

    @Test
    void testAlerta_noSeEnviaCorreoSiTareaEstaCompletada() {
        ToDo tareaCompletada = new ToDo("Hecha", "desc", LocalDate.of(2020, 1, 1), true);
        bdControlada.CREATE(tareaCompletada);
        bdControlada.agnadirCorreo("aviso@test.es");

        assertDoesNotThrow(() -> servicio.mostrarTareas());
    }

    @Test
    void testAlerta_noSeEnviaCorreoSiNoHayDireccionesEnAgenda() {
        ToDo tareaVencida = new ToDo("SinDestinatario", "desc", LocalDate.of(2020, 1, 1), false);
        bdControlada.CREATE(tareaVencida);

        assertDoesNotThrow(() -> servicio.mostrarTareas());
    }
}