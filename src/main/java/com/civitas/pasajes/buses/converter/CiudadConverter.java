package com.civitas.pasajes.buses.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.civitas.pasajes.buses.dto.CiudadDto;

import com.civitas.pasajes.buses.entity.Ciudad;
import com.civitas.pasajes.buses.entity.Departamento;
import com.civitas.pasajes.buses.service.DepartamentoService;

@Component
public class CiudadConverter extends AbstractConverter<Ciudad, CiudadDto>{
	@Autowired
	private DepartamentoService service;
	
	@Override
	public CiudadDto fromEntity(Ciudad entity) {
		if (entity == null)
			return null;

		return CiudadDto.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.pais(entity.getDepartamento().getPais().getNombre())
				.departamento(entity.getDepartamento().getNombre())
				.url(entity.getUrlImg())
				.build();
	}

	@Override
	public Ciudad fromDTO(CiudadDto dto) {
		if (dto == null)
			return null;
		Departamento departamento = service.findByNombre(dto.getDepartamento());
		
		return Ciudad.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.departamento(departamento)
			    .urlImg(dto.getUrl())
				.build();
	}
}
