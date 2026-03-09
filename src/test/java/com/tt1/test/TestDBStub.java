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
	private DBStub bd=new DBStub();

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
	void testREAD() {
		assertEquals(ToDoMock.ejemplo,bd.READ("AYUDA"));
	}
	
	@Test
	void testCREATE() {
		bd.CREATE(new ToDo("prueba","prueba",LocalDate.of(0, 0, 0), false));
		assertEquals(ToDoMock.ejemplo,bd.READ("prueba"));
	}

	@Test
	void testUPDATE() {
		assertTrue(bd.UPDATE(ToDoMock.ejemplo));
	}

	@Test
	void testDELETE() {
		bd.DELETE(ToDoMock.ejemplo);
		assertNotEquals(ToDoMock.ejemplo,bd.READ("AYUDA"));
	}

}
