package com.rhis.api.enums;

import java.util.Arrays;

public enum EstadoCivil {
    CASADO, SOLTERO, VIUDO, DIVORCIADO, UNIDO, SEPARADO;

    /**
     * funcion que valida que el estado civil sea valido
     * @param estadoCivilNombre valor a evaluar
     * @return true si el valor ingresado es correcto || false si el valor ingresado es incorrecto
     */
    public static boolean estadoCivil(String estadoCivilNombre){
        return Arrays.stream(EstadoCivil.values())
                .map(Enum::name)
                .anyMatch(valor -> valor.equalsIgnoreCase(estadoCivilNombre));
    }
}
