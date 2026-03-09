package com.tt1.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tt1.test.mock.ToDoMock;

class TestServicio {
	
	private LocalTime aux=LocalTime.of(14,30);
	private Servicio servicio=new Servicio();

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
	void testCrearServicio() {
		servicio.crearServicio("AYUDA", aux);
		assertEquals("AYUDA",ToDoMock.ejemplo);
	}

	@Test
	void testAgnadirCorreo() {
		servicio.agnadirCorreo("ejemplo@unirioja.es");
		assertEquals("ejemplo@unirioja.es",null);
	}

	@Test
	void testFinalizarTarea() {
		servicio.finalizarTarea(ToDoMock.ejemplo);
	}

	@Test
	void testMostrarTareas() {
		servicio.mostrarTareas();
	}

}
