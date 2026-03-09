package com.tt1.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		assertEquals("AYUDA","");
	}

	@Test
	void testAgnadirCorreo() {
		fail("Not yet implemented");
	}

	@Test
	void testFinalizarTarea() {
		fail("Not yet implemented");
	}

	@Test
	void testMostrarTareas() {
		fail("Not yet implemented");
	}

}
