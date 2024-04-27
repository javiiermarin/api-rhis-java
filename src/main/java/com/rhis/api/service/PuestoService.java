package com.rhis.api.service;

import com.rhis.api.dto.PuestoRequestDto;
import com.rhis.api.dto.PuestoResponseDto;
import com.rhis.api.exception.DivisionNotFoundException;
import com.rhis.api.mapper.PuestoMapper;
import com.rhis.api.repository.DivisionRespository;
import com.rhis.api.repository.PuestoRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuestoService {

    private final PuestoRespository puestoRespository;

    private final PuestoMapper puestoMapper;

    private final DivisionRespository divisionRespository;

    public PuestoService(PuestoRespository puestoRespository, PuestoMapper puestoMapper, DivisionRespository divisionRespository) {
        this.puestoRespository = puestoRespository;
        this.puestoMapper = puestoMapper;
        this.divisionRespository = divisionRespository;
    }

    /**
     * metodo para listar los puestos de una division || si no especificamos una division muestra todos los puestos
     * @param idDivisiom
     * @return
     * @throws DivisionNotFoundException
     */
    public List<PuestoResponseDto> obtenerPuestosPorDivision(String idDivisiom)throws DivisionNotFoundException {

        if (idDivisiom != null){

            var puestoResponseDto = puestoRespository.findAllByDivision_IdDivisionAndIsEnabledTrue(idDivisiom)
                    .stream()
                    .map(puestoMapper::toDto)
                    .toList();
            return puestoResponseDto;

        }

        return puestoRespository.findAll()
                .stream()
                .map(puestoMapper::toDto)
                .toList();
    }

    /**
     * metodo para crear un nuevo puesto a partir de los parametros solicitados
     * @param puestoRequestDto
     * @return
     */
    public PuestoResponseDto crearPuesto(PuestoRequestDto puestoRequestDto){
        var division = divisionRespository.findById(puestoRequestDto.getDivision());

        var puesto = puestoMapper.toEntity(puestoRequestDto);
        puesto.setDivision(division.get());
        puesto.setIsEnabled(true);

        return puestoMapper.toDto(puestoRespository.save(puesto));
    }


}
