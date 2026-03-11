package com.tt1.test;

public class MailerStub {

    public boolean enviarCorreo(String correoOr, String correoDe, String mensaje) {
        System.out.println("Correo enviado!");
        System.out.println("  De:      " + correoOr);
        System.out.println("  Para:    " + correoDe);
        System.out.println("  Mensaje: " + mensaje);
        return true;
    }
}
