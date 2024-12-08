package com.civitas.pasajes.buses.converter;

import org.springframework.stereotype.Component;

import com.civitas.pasajes.buses.dto.DepartamentoDto;
import com.civitas.pasajes.buses.entity.Departamento;

@Component
public class DepartamentoConverter extends AbstractConverter<Departamento, DepartamentoDto> {

	@Override
	public DepartamentoDto fromEntity(Departamento entity) {
		if (entity == null)
			return null;

		return DepartamentoDto.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.pais(entity.getPais())
				.urlImg(entity.getUrlImg())
				.build();
	}

	@Override
	public Departamento fromDTO(DepartamentoDto dto) {
		if (dto == null)
			return null;

		return Departamento.builder()
				.id(dto.getId())
				.id(dto.getId())
				.nombre(dto.getNombre())
				.pais(dto.getPais())
			    .urlImg(dto.getUrlImg())
				.build();
	}
}