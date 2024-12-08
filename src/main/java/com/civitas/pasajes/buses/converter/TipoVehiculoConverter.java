package com.civitas.pasajes.buses.converter;

import org.springframework.stereotype.Component;

import com.civitas.pasajes.buses.dto.TipoVehiculoDto;
import com.civitas.pasajes.buses.entity.TipoVehiculo;

@Component
public class TipoVehiculoConverter extends AbstractConverter<TipoVehiculo, TipoVehiculoDto> {
	@Override
	public TipoVehiculoDto fromEntity(TipoVehiculo entity) {
		if (entity == null)
			return null;

		return TipoVehiculoDto.builder().id(entity.getId()).nombre(entity.getNombre()).build();
	}

	@Override
	public TipoVehiculo fromDTO(TipoVehiculoDto dto) {
		if (dto == null)
			return null;

		return TipoVehiculo.builder().id(dto.getId()).nombre(dto.getNombre()).build();
	}
}
