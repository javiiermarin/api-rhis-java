package com.rhis.api.service;

import com.rhis.api.dto.PuestoRequestDto;
import com.rhis.api.dto.PuestoResponseDto;
import com.rhis.api.exception.DivisionNotFoundException;
import com.rhis.api.exception.PuestoNotFoundException;
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
     *
     * @param idDivision
     * @return
     * @throws DivisionNotFoundException
     */
    public List<PuestoResponseDto> obtenerPuestosPorDivision(String idDivision) {

        if (idDivision != null) {
            return puestoRespository.findAllByDivision_IdDivisionAndIsEnabledTrue(idDivision)
                    .stream()
                    .map(puestoMapper::toDto)
                    .toList();
        }
        return puestoRespository.findAll()
                .stream()
                .map(puestoMapper::toDto)
                .toList();
    }

    /**
     * metodo para crear un nuevo puesto a partir de los parametros solicitados
     *
     * @param puestoRequestDto
     * @return
     */
    public PuestoResponseDto crearPuesto(PuestoRequestDto puestoRequestDto) throws DivisionNotFoundException {
        var division = divisionRespository.findById(puestoRequestDto.getIdDivision())
                .orElseThrow(DivisionNotFoundException::new);

        var puesto = puestoMapper.toEntity(puestoRequestDto);
        puesto.setDivision(division);
        puesto.setIsEnabled(true);

        return puestoMapper.toDto(puestoRespository.save(puesto));
    }

    /**
     * metodo para modificar un puesto
     *
     * @param puestoRequestDto
     * @return
     * @throws PuestoNotFoundException
     */

    public PuestoResponseDto modificarPuesto(PuestoRequestDto puestoRequestDto) throws PuestoNotFoundException, DivisionNotFoundException {
        var puesto = puestoRespository.findById(puestoRequestDto.getIdPuesto()).orElseThrow(PuestoNotFoundException::new);
        var division = divisionRespository.findById(puestoRequestDto.getIdDivision())
                .orElseThrow(DivisionNotFoundException::new);

        puesto.setNombre(puestoRequestDto.getNombre());
        puesto.setDivision(division);
        puesto.setSalarioMaximo(puestoRequestDto.getSalarioMaximo());
        puesto.setSalarioMinimo(puesto.getSalarioMinimo());

        return puestoMapper.toDto(puestoRespository.save(puesto));

    }

    /**
     * metodo para eliminar un puesto
     *
     * @param idPuesto
     */
    public void eliminarPuesto(String idPuesto) {
        puestoRespository.deleteById(idPuesto);
    }


}
