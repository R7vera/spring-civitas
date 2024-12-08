package com.civitas.pasajes.buses.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.civitas.pasajes.buses.dto.DistritoDto;
import com.civitas.pasajes.buses.entity.Distrito;
import com.civitas.pasajes.buses.entity.Ciudad;
import com.civitas.pasajes.buses.service.CiudadService;

@Component
public class DistritoConverter extends AbstractConverter<Distrito, DistritoDto>{
	@Autowired
	private CiudadService service;
	
	@Override
	public DistritoDto fromEntity(Distrito entity) {
		if (entity == null)
			return null;

		return DistritoDto.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.direccion(entity.getDireccion())
				.referencia(entity.getReferencia())
				.pais(entity.getCiudad().getDepartamento().getPais().getNombre())
				.urlImg(entity.getUrlImg())
				.ciudad(entity.getCiudad().getNombre())
				.departamento(entity.getCiudad().getDepartamento().getNombre())
				.build();
	}

	@Override
	public Distrito fromDTO(DistritoDto dto) {
		if (dto == null)
			return null;
		Ciudad ciudad = service.findByNombre(dto.getCiudad());
		
		return Distrito.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.ciudad(ciudad)
				.urlImg(dto.getUrlImg())
				.direccion(dto.getDireccion())
				.referencia(dto.getReferencia())
				.build();
	}
}