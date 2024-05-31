package com.rhis.api.controller;

import com.rhis.api.dto.PermisoRequestDto;
import com.rhis.api.dto.PermisoResponseDto;
import com.rhis.api.exception.EmpleadoNotFoundException;
import com.rhis.api.exception.UnregisterVacationException;
import com.rhis.api.service.PermisoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rhis/permisos")
public class PermisoController {

    private final PermisoService permisoService;

    public PermisoController(PermisoService permisoService) {
        this.permisoService = permisoService;
    }

    /**
     * Peticion que crea un permiso
     * @param permisoRequestDto
     * @return
     */
    @PostMapping
    public ResponseEntity<PermisoResponseDto> crearPermiso(@RequestBody @Valid PermisoRequestDto permisoRequestDto) throws UnregisterVacationException, EmpleadoNotFoundException {
        var permiso = permisoService.crearPermiso(permisoRequestDto);
        return new ResponseEntity<>(permiso, HttpStatus.CREATED);
    }

    /**
     * Peticion que lista todos los permisos
     *
     * @param idEmpleado
     * @return
     */
    @GetMapping
    public ResponseEntity<List<PermisoResponseDto>> obtenerPermisosPorEmpleado(
            @RequestParam(value = "idEmpleado") String idEmpleado) {
        var permiso = permisoService.listarPermisosPorEmpleado(idEmpleado);
        return new ResponseEntity<>(permiso, HttpStatus.OK);
    }

    /**
     * Peticion que modifica el estado de los permisos
     *
     * @param permisoRequestDto
     * @return
     */
    @PutMapping
    public ResponseEntity<PermisoResponseDto> modificarPermiso(@RequestBody
                                                                   @Valid PermisoRequestDto permisoRequestDto){
        var permiso = permisoService.modificarPermisos(permisoRequestDto);

        return new ResponseEntity<>(permiso,HttpStatus.OK);
    }


}
