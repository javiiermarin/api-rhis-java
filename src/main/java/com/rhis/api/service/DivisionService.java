package com.rhis.api.service;

import com.rhis.api.dto.DivisionRequestDto;
import com.rhis.api.dto.DivisionResponseDto;
import com.rhis.api.exception.DivisionNotFoundException;
import com.rhis.api.exception.EmpleadoNotFoundException;
import com.rhis.api.mapper.DivisionMapper;
import com.rhis.api.model.Division;
import com.rhis.api.repository.DivisionRespository;
import com.rhis.api.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DivisionService {

    private final DivisionRespository divisionRespository;

    private final DivisionMapper divisionMapper;

    private final EmpleadoRepository empleadoRepository;


    public DivisionService(DivisionRespository divisionRespository, DivisionMapper divisionMapper, EmpleadoRepository empleadoRepository) {
        this.divisionRespository = divisionRespository;
        this.divisionMapper = divisionMapper;
        this.empleadoRepository = empleadoRepository;
    }

    /**
     * Funcion que obtiene todas las divisiones (departamentos dentro de la empresa) disponibles
     *
     * @return listado de divisiones
     */
    public List<DivisionResponseDto> obtenerDivisiones() {
        return divisionRespository.findAllByOrderByCreatedAtAsc()
                .stream()
                .map(divisionMapper::toDto)
                .toList();
    }

    /**
     * metodo para crear una division
     *
     * @param divisionRequestDto
     * @return division creada
     */

    public DivisionResponseDto crearDivision(DivisionRequestDto divisionRequestDto) throws EmpleadoNotFoundException {
        var encargado = empleadoRepository.findById(divisionRequestDto.getEncargado())
                .orElseThrow(() -> new EmpleadoNotFoundException("El encargado no existe"));

        var division = divisionMapper.toEntity(divisionRequestDto);
        division.setEnabled(true);
        division.setEncargado(encargado);

        return divisionMapper.toDto(divisionRespository.save(division));
    }

    /**
     * metodo que nos lista todas las divisiones registradas
     *
     * @param idDivision
     * @return
     */

    private Optional<Division> obtenerDivision(String idDivision) {
        return divisionRespository.findByIdDivision(idDivision);
    }

    /**
     * metodo para modificar los datos de una division
     *
     * @param divisionRequestDto
     * @return
     * @throws DivisionNotFoundException
     */
    public DivisionResponseDto actualizarDivision(DivisionRequestDto divisionRequestDto) throws DivisionNotFoundException, EmpleadoNotFoundException {
        var division = obtenerDivision(divisionRequestDto.getIdDivision())
                .orElseThrow(DivisionNotFoundException::new);
        var encargado = empleadoRepository.findById(divisionRequestDto.getEncargado())
                .orElseThrow(() -> new EmpleadoNotFoundException("El encargado no existe"));

        division.setNombre(divisionRequestDto.getNombre());
        division.setEnabled(divisionRequestDto.isEnabled());
        division.setEncargado(encargado);

        return divisionMapper.toDto(divisionRespository.save(division));
    }
}
