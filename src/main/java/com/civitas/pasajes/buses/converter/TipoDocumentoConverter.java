package com.civitas.pasajes.buses.converter;

import org.springframework.stereotype.Component;

import com.civitas.pasajes.buses.dto.TipoDocumentoDto;

import com.civitas.pasajes.buses.entity.TipoDocumento;

@Component
public class TipoDocumentoConverter extends AbstractConverter<TipoDocumento, TipoDocumentoDto> {
	@Override
	public TipoDocumentoDto fromEntity(TipoDocumento entity) {
		if (entity == null)
			return null;

		return TipoDocumentoDto.builder().id(entity.getId()).nombre(entity.getNombre()).build();
	}

	@Override
	public TipoDocumento fromDTO(TipoDocumentoDto dto) {
		if (dto == null)
			return null;

		return TipoDocumento.builder().id(dto.getId()).nombre(dto.getNombre()).build();
	}
}
