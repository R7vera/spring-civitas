package com.civitas.pasajes.buses.converter;

import com.civitas.pasajes.buses.dto.PaisDto;
import com.civitas.pasajes.buses.entity.Pais;
import org.springframework.stereotype.Component;

@Component
public class PaisConverter extends AbstractConverter<Pais, PaisDto> {

    @Override
    public PaisDto fromEntity(Pais entity) {
        if (entity == null) return null;

        return PaisDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .url(entity.getUrl())
                .build();
    }

    @Override
    public Pais fromDTO(PaisDto dto) {
        if (dto == null) return null;

        return Pais.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .url(dto.getUrl())
                .build();
    }
}
