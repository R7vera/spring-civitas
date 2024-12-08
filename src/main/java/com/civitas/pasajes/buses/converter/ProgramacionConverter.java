package com.civitas.pasajes.buses.converter;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.civitas.pasajes.buses.dto.ProgramacionDto;
import com.civitas.pasajes.buses.entity.Departamento;
import com.civitas.pasajes.buses.entity.Destinos;
import com.civitas.pasajes.buses.entity.Programacion;
import com.civitas.pasajes.buses.entity.Vehiculo;
import com.civitas.pasajes.buses.service.DepartamentoService;
import com.civitas.pasajes.buses.service.DestinoService;
import com.civitas.pasajes.buses.service.VehiculoService;

@Component
public class ProgramacionConverter extends AbstractConverter<Programacion, ProgramacionDto> {
	@Autowired
	private VehiculoService service;
	
	@Autowired
	private DestinoService serviceDestino;
	
	@Override
	public ProgramacionDto fromEntity(Programacion entity) {
		if (entity == null)
			return null;

		return ProgramacionDto.builder()
				.id(entity.getId())
				.FechaLlegada(entity.getFechaLlegada())
				.FechaSalida(entity.getFechaSalida())
				.idVehiculo(entity.getVehiculo().getId())
				.idDestino(entity.getDestino().getId())
				.build();
	}
	
	@Override
	public Programacion fromDTO(ProgramacionDto dto) {
		if (dto == null)
			return null;
		Vehiculo registro = service.findById(dto.getIdVehiculo());
		Destinos registro2 = serviceDestino.findById(dto.getIdDestino());
		
		return Programacion.builder()
				.id(dto.getId())
				.id(dto.getId())
				.FechaLlegada(dto.getFechaLlegada())
				.FechaSalida(dto.getFechaSalida())
				.vehiculo(registro)
				.destino(registro2)
				.build();
	}
}
