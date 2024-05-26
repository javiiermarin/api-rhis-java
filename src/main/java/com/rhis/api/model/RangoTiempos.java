package com.rhis.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
public class RangoTiempos {

    private LocalTime inicio;
    private LocalTime fin;

    public boolean contains (LocalTime time) {
        return time.isAfter(inicio) && time.isBefore(fin);
    }
}
