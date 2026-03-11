package com.tt1.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tt1.test.mock.ToDoMock;

class TestRepositorio {

    private Repositorio repo;
    private DBStub bdControlada;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println("== Empezando tests de Repositorio ==");
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        System.out.println("== Tests de Repositorio finalizados ==");
    }

    @BeforeEach
    void setUp() throws Exception {
        ToDoMock.reset();
        bdControlada = new DBStub();
        repo = new Repositorio(bdControlada);
    }

    @AfterEach
    void tearDown() throws Exception {
        // Nada que limpiar
    }

    @Test
    void testUnidad_buscarServicio_devuelveNombreSiExiste() {
        // Arrange: insertamos en la BD controlada directamente
        bdControlada.CREATE(ToDoMock.ejemplo);
        // Act
        String nombre = repo.buscarServicio("AYUDA");
        // Assert
        assertEquals("AYUDA", nombre);
    }

    @Test
    void testUnidad_buscarServicio_devuelveNullSiNoExiste() {
        String resultado = repo.buscarServicio("NoExiste");
        assertNull(resultado);
    }

    @Test
    void testUnidad_agnadirServicio_devuelveTrue() {
        boolean resultado = repo.agnadirServicio(ToDoMock.ejemplo);
        assertTrue(resultado);
    }

    @Test
    void testUnidad_completadoServicio_marcaComoCompletado() {
        // Arrange: creamos una tarea no completada
        ToDoMock.ejemplo.setCompletado(false);
        bdControlada.CREATE(ToDoMock.ejemplo);
        // Act
        boolean resultado = repo.completadoServicio(ToDoMock.ejemplo);
        // Assert: debe devolver true y la tarea debe estar completada
        assertTrue(resultado);
        assertTrue(ToDoMock.ejemplo.isCompletado());
    }

    @Test
    void testUnidad_agnadirCorreo_devuelveTrue() {
        boolean resultado = repo.agnadirCorreo("ejemplo@unirioja.es");
        assertTrue(resultado);
    }

    @Test
    void testIntegracion_agnadirYBuscarServicio() {
        repo.agnadirServicio(ToDoMock.ejemplo);
        String nombreEncontrado = repo.buscarServicio("AYUDA");
        assertEquals("AYUDA", nombreEncontrado);
    }

    @Test
    void testIntegracion_agnadirYCompletarServicio() {
        ToDoMock.ejemplo.setCompletado(false);
        repo.agnadirServicio(ToDoMock.ejemplo);
        repo.completadoServicio(ToDoMock.ejemplo);
        assertTrue(ToDoMock.ejemplo.isCompletado());
    }

    @Test
    void testIntegracion_agnadirCorreoYVerificarEnBD() {
        repo.agnadirCorreo("integración@unirioja.es");
        assertEquals("integración@unirioja.es",
                     bdControlada.getCorreo("integración@unirioja.es"));
    }
}