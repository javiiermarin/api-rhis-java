package com.rhis.api.controller;

import com.rhis.api.dto.HoraExtraResponseDto;
import com.rhis.api.service.HoraExtraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rhis/horasExtras")
public class HorasExtrasController {

    private final HoraExtraService horaExtraService;

    public HorasExtrasController(HoraExtraService horaExtraService) {
        this.horaExtraService = horaExtraService;
    }

    @GetMapping
    public ResponseEntity<List<HoraExtraResponseDto>> listarHorasExtras() {
        var horasExtras = horaExtraService.mostrarHorasExtras();
        return new ResponseEntity<>(horasExtras, HttpStatus.OK);
    }
}
