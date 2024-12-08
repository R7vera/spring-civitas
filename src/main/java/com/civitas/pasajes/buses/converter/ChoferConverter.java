package com.civitas.pasajes.buses.converter;


import org.springframework.stereotype.Component;

import com.civitas.pasajes.buses.dto.ChoferDto;
import com.civitas.pasajes.buses.entity.Chofer;

@Component
public class ChoferConverter extends AbstractConverter<Chofer, ChoferDto>{

	@Override
	public ChoferDto fromEntity(Chofer entity) {
		if (entity == null)
			return null;

		return ChoferDto.builder()
				.id(entity.getId())
				.persona(entity.getPersona())
				.numeroLicencia(entity.getNumeroLicencia())
				.tipoLicencia(entity.getTipoLicencia())
				.estado(entity.isEstado())
				.build();
	}

	@Override
	public Chofer fromDTO(ChoferDto dto) {
		if (dto == null)
			return null;
		
		
		return Chofer.builder()
				.id(dto.getId())
				.persona(dto.getPersona())
				.numeroLicencia(dto.getNumeroLicencia())
				.tipoLicencia(dto.getTipoLicencia())
				.estado(dto.isEstado())
				.build();
	}
}
