package com.rhis.api.exception;

/**
 * Exepcion especifica para cuando no haya una division habil
 */

public class DivisionNotFoundException extends NotFoundException {

    public DivisionNotFoundException() {
        super("NO HAY DIVISIONES HABILES");
    }
}
