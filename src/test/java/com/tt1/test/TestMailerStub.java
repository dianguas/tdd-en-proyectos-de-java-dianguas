package com.tt1.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestMailerStub {

    private MailerStub mail;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println("== Empezando tests de MailerStub ==");
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        System.out.println("== Tests de MailerStub finalizados ==");
    }

    @BeforeEach
    void setUp() throws Exception {
        mail = new MailerStub();
    }

    @AfterEach
    void tearDown() throws Exception {
        // Nada que limpiar
    }

    @Test
    void testEnviarCorreoDevuelveTrue() {
        // Arrange: tenemos el mail creado en setUp
        // Act: enviamos un correo de prueba
        boolean resultado = mail.enviarCorreo("origen@test.es", "destino@test.es", "Hola!");
        // Assert: debe devolver true, confirmación de éxito
        assertTrue(resultado);
    }

    @Test
    void testEnviarCorreoConMensajeVacio() {
        boolean resultado = mail.enviarCorreo("a@test.es", "b@test.es", "");
        assertTrue(resultado);
    }

    @Test
    void testEnviarCorreoNoLanzaExcepcion() {
        assertDoesNotThrow(() -> {
            mail.enviarCorreo("x@test.es", "y@test.es", "Mensaje de prueba");
        });
    }
}