package com.rhis.api.exception;

public class UnregisterVacationException extends UnprocessableEntityException {
    public UnregisterVacationException() {
        super("No es posible registrar las vacaciones ya que no existe una persona que contenga el rol \"Encargado de Nomina \"");
    }

    public UnregisterVacationException(String message) {
        super(message);
    }
}
