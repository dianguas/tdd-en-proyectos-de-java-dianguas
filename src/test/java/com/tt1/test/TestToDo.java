package com.tt1.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tt1.test.mock.ToDoMock;

class TestToDo {
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetNombre() {
		assertEquals("AYUDA",ToDoMock.ejemplo.getNombre());
	}

	@Test
	void testSetNombre() {
		ToDoMock.ejemplo.setNombre("ayuda");
		assertEquals("ayuda",ToDoMock.ejemplo.getNombre());
	}

	@Test
	void testGetDescripcion() {
		assertEquals("Este texto es un ejemplo",ToDoMock.ejemplo.getDescripcion());
	}

	@Test
	void testSetDescripcion() {
		ToDoMock.ejemplo.setDescripcion("Modificado");
		assertEquals("Modificado",ToDoMock.ejemplo.getDescripcion());
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

}
