package com.tt1.test.mock;

import java.time.LocalDate;

import com.tt1.test.ToDo;

public class ToDoMock {

    public static ToDo ejemplo = new ToDo("AYUDA", "Este texto es un ejemplo", LocalDate.of(2025, 1, 1), true);

    public static void reset() {
        ejemplo = new ToDo(
            "AYUDA",
            "Este texto es un ejemplo",
            LocalDate.of(2025, 1, 1),
            true
        );
    }
}