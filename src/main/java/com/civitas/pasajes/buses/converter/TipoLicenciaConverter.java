package com.civitas.pasajes.buses.converter;

import org.springframework.stereotype.Component;

import com.civitas.pasajes.buses.dto.TipoLicenciaDto;
import com.civitas.pasajes.buses.entity.TipoLicencia;

@Component
public class TipoLicenciaConverter extends AbstractConverter<TipoLicencia, TipoLicenciaDto> {
	@Override
	public TipoLicenciaDto fromEntity(TipoLicencia entity) {
		if (entity == null)
			return null;

		return TipoLicenciaDto.builder().id(entity.getId()).nombre(entity.getNombre()).build();
	}

	@Override
	public TipoLicencia fromDTO(TipoLicenciaDto dto) {
		if (dto == null)
			return null;

		return TipoLicencia.builder().id(dto.getId()).nombre(dto.getNombre()).build();
	}
}
