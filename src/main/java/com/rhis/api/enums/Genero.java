package com.rhis.api.enums;

import java.util.Arrays;

public enum Genero {
    MASCULINO, FEMENINO;

    /**
     * Funcion que valida que un genero sea valido
     *
     * @param nombreGenero valor a evaluar
     * @return true si el genero es valido || false si el genero no es valido
     */
    public static boolean genero(String nombreGenero) {
        return Arrays.stream(Genero.values())
                .map(Enum::name)
                .anyMatch(valor -> valor.equalsIgnoreCase(nombreGenero));
    }

    public static Genero agregarGenero(String genero){
        return Genero.valueOf(genero);
    }

}
