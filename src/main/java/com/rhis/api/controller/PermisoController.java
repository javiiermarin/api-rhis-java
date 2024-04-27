package com.rhis.api.controller;

import com.rhis.api.dto.PermisoRequestDto;
import com.rhis.api.dto.PermisoResponseDto;
import com.rhis.api.service.PermisoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rhis/permisos")
public class PermisoController {

    private final PermisoService permisoService;

    public PermisoController(PermisoService permisoService) {
        this.permisoService = permisoService;
    }

    @PostMapping
    public ResponseEntity<PermisoResponseDto> crearPermiso(@RequestBody @Valid PermisoRequestDto permisoRequestDto){
        var permiso = permisoService.crearPermiso(permisoRequestDto);
        return new ResponseEntity<>(permiso, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PermisoResponseDto>> obtenerPermisosPorEmpleado(
            @RequestParam(value = "idEmpleado") String idEmpleado) {
        var permiso = permisoService.listarPermisosPorEmpleado(idEmpleado);
        return new ResponseEntity<>(permiso, HttpStatus.OK);

    }


}
