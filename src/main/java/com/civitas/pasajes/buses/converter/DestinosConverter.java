package com.civitas.pasajes.buses.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.civitas.pasajes.buses.dto.DestinosDto;

import com.civitas.pasajes.buses.entity.Destinos;
import com.civitas.pasajes.buses.entity.Distrito;
import com.civitas.pasajes.buses.service.DistritoService;

@Component
public class DestinosConverter extends AbstractConverter<Destinos, DestinosDto>{
	@Autowired
	private DistritoService service;
	@Autowired
	private DistritoConverter converter;

	@Override
	public DestinosDto fromEntity(Destinos entity) {
		if (entity == null)
			return null;
		  return DestinosDto.builder()
	                .id(entity.getId())
	                .direccionSalida(converter.fromEntity(entity.getDireccionSalida()))
	                .direccionLlegada(converter.fromEntity(entity.getDireccionLlegada()))
	                .kilometros(entity.getKilometros())
	                .horas(entity.getHoras())
	                .estado(entity.isEstado())
	                .costoBase(entity.getCostoBase())
	                .build();
	}
	
	@Override
	public Destinos fromDTO(DestinosDto dto) {
		if (dto == null)
			return null;
		 Distrito direccionSalida = service.findById(dto.getDireccionSalida().getId());
	        Distrito direccionLlegada = service.findById(dto.getDireccionLlegada().getId());

	        return Destinos.builder()
	                .id(dto.getId())
	                .direccionSalida(direccionSalida)
	                .direccionLlegada(direccionLlegada)
	                .kilometros(dto.getKilometros())
	                .horas(dto.getHoras())
	                .estado(dto.isEstado()	)
	                .costoBase(dto.getCostoBase())
	                .build();
	}
}