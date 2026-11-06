package com.grupo5.citasmedicas.exception;

public class AppointmentConflictException extends RuntimeException {

    public AppointmentConflictException(String mensaje) {
        super(mensaje);
    }
}