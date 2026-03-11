package com.tt1.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import com.tt1.test.mock.ToDoMock;

class TestToDo {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println("== Empezando tests de ToDo ==");
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        System.out.println("== Tests de ToDo finalizados ==");
    }

    @BeforeEach
    void setUp() throws Exception {
        ToDoMock.reset();
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testGetNombre() {
        // Arrange: ToDoMock.ejemplo ya tiene nombre "AYUDA"
        // Act + Assert: comprobamos que getNombre() devuelve "AYUDA"
        assertEquals("AYUDA", ToDoMock.ejemplo.getNombre());
    }

    @Test
    void testSetNombre() {
        // Act: cambiamos el nombre
        ToDoMock.ejemplo.setNombre("ayuda");
        // Assert: el nuevo nombre debe haberse guardado
        assertEquals("ayuda", ToDoMock.ejemplo.getNombre());
    }

    @Test
    void testGetDescripcion() {
        assertEquals("Este texto es un ejemplo", ToDoMock.ejemplo.getDescripcion());
    }

    @Test
    void testSetDescripcion() {
        ToDoMock.ejemplo.setDescripcion("Modificado");
        assertEquals("Modificado", ToDoMock.ejemplo.getDescripcion());
    }

    @Test
    void testIsCompletado() {
        assertTrue(ToDoMock.ejemplo.isCompletado());
    }

    @Test
    void testSetCompletado() {
        ToDoMock.ejemplo.setCompletado(false);
        assertFalse(ToDoMock.ejemplo.isCompletado());
    }

    @Test
    void testConstructorCreaObjetoCorrecto() {
        LocalDate fecha = LocalDate.of(2025, 6, 15);
        ToDo t = new ToDo("Tarea", "Descripcion", fecha, false);

        assertEquals("Tarea", t.getNombre());
        assertEquals("Descripcion", t.getDescripcion());
        assertEquals(fecha, t.getFechaL());
        assertFalse(t.isCompletado());
    }
}