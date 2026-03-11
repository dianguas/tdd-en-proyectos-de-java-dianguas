package com.tt1.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tt1.test.mock.ToDoMock;

class TestDBStub {

    private DBStub bd;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println("== Empezando tests de DBStub ==");
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        System.out.println("== Tests de DBStub finalizados ==");
    }

    @BeforeEach
    void setUp() throws Exception {
        bd = new DBStub();
        ToDoMock.reset();
    }

    @AfterEach
    void tearDown() throws Exception {
        // Nada que limpiar, la BD está en memoria
    }


    @Test
    void testCREATE_devuelveTrue() {
        // Arrange
        ToDo tarea = new ToDo("Tarea1", "Descripcion", LocalDate.now(), false);
        // Act
        boolean resultado = bd.CREATE(tarea);
        // Assert
        assertTrue(resultado);
    }

    @Test
    void testREAD_encuentraToDoInsertado() {
        // Arrange: insertamos primero
        bd.CREATE(ToDoMock.ejemplo);
        // Act: buscamos por nombre
        ToDo encontrado = bd.READ("AYUDA");
        // Assert: debe ser el mismo objeto
        assertEquals(ToDoMock.ejemplo, encontrado);
    }

    @Test
    void testREAD_devuelveNullSiNoExiste() {
        ToDo resultado = bd.READ("NoExiste");
        assertNull(resultado);
    }

    @Test
    void testUPDATE_modificaToDoExistente() {
        // Arrange: insertamos y luego modificamos
        bd.CREATE(ToDoMock.ejemplo);
        ToDoMock.ejemplo.setDescripcion("Descripcion actualizada");
        // Act
        boolean resultado = bd.UPDATE(ToDoMock.ejemplo);
        // Assert: debe devolver true y el cambio debe estar guardado
        assertTrue(resultado);
        assertEquals("Descripcion actualizada", bd.READ("AYUDA").getDescripcion());
    }

    @Test
    void testUPDATE_devuelveFalseSiNoExiste() {
        ToDo inventado = new ToDo("Fantasma", "No existe", LocalDate.now(), false);
        boolean resultado = bd.UPDATE(inventado);
        assertFalse(resultado);
    }

    @Test
    void testDELETE_eliminaElToDo() {
        // Arrange: insertamos y luego borramos
        bd.CREATE(ToDoMock.ejemplo);
        // Act
        bd.DELETE(ToDoMock.ejemplo);
        // Assert: ya no debe existir
        assertNull(bd.READ("AYUDA"));
    }

    @Test
    void testAgnadirCorreo_devuelveTrue() {
        boolean resultado = bd.agnadirCorreo("ejemplo@unirioja.es");
        assertTrue(resultado);
    }

    @Test
    void testGetCorreo_encuentraCorreoInsertado() {
        bd.agnadirCorreo("ejemplo@unirioja.es");
        String resultado = bd.getCorreo("ejemplo@unirioja.es");
        assertEquals("ejemplo@unirioja.es", resultado);
    }

    @Test
    void testGetCorreo_devuelveNullSiNoExiste() {
        String resultado = bd.getCorreo("noexiste@test.es");
        assertNull(resultado);
    }

    @Test
    void testUpdateCorreo_reemplazaCorreo() {
        // Arrange
        bd.agnadirCorreo("viejo@unirioja.es");
        // Act
        bd.updateCorreo("viejo@unirioja.es", "nuevo@unirioja.es");
        // Assert: el viejo ya no existe, el nuevo sí
        assertNull(bd.getCorreo("viejo@unirioja.es"));
        assertEquals("nuevo@unirioja.es", bd.getCorreo("nuevo@unirioja.es"));
    }

    @Test
    void testDeleteCorreo_eliminaCorreo() {
        bd.agnadirCorreo("borrar@unirioja.es");
        bd.deleteCorreo("borrar@unirioja.es");
        assertNull(bd.getCorreo("borrar@unirioja.es"));
    }
}