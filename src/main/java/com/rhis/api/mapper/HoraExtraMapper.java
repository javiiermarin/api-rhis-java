package com.rhis.api.mapper;

import com.rhis.api.dto.HoraExtraRequestDto;
import com.rhis.api.dto.HoraExtraResponseDto;
import com.rhis.api.model.HoraExtra;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class HoraExtraMapper {

    private final ModelMapper modelMapper;

    public HoraExtraMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public HoraExtraResponseDto toDto(HoraExtra horaExtra) {
        return modelMapper.map(horaExtra, HoraExtraResponseDto.class);
    }

    public HoraExtra toEntity(HoraExtraRequestDto horaExtraRequestDto) {
        return modelMapper.map(horaExtraRequestDto, HoraExtra.class);
    }
}
