package com.rhis.api.enums;

import java.util.Arrays;

public enum EstadoPermiso {
    PENDIENTE, APROBADO;

    public static boolean estadoPermiso(String estadoPermiso){
        return Arrays.stream(EstadoPermiso.values())
                .map(Enum::name)
                .anyMatch(valor -> valor.equalsIgnoreCase(estadoPermiso));
    }

    public static EstadoPermiso agregarEstadoPermiso(String estadoPermiso){
        return EstadoPermiso.valueOf(estadoPermiso);
    }
}
