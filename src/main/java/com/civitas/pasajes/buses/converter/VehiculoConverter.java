package com.civitas.pasajes.buses.converter;

import org.springframework.stereotype.Component;

import com.civitas.pasajes.buses.dto.VehiculoDto;
import com.civitas.pasajes.buses.entity.TipoVehiculo;
import com.civitas.pasajes.buses.entity.Vehiculo;
import com.civitas.pasajes.buses.entity.Vehiculo.EstadoVehiculo;

@Component
public class VehiculoConverter extends AbstractConverter<Vehiculo, VehiculoDto>  {
	 @Override
	    public VehiculoDto fromEntity(Vehiculo entity) {
	        if (entity == null) return null;

	        return VehiculoDto.builder()
	                .id(entity.getId())
	                .tipoVehiculo(entity.getTipoVehiculo())
	                .placa(entity.getPlaca())
	                .numeroPisos(entity.getNumeroPisos())
	                .estado(entity.getEstado())
	                .build();
	    }
	
	    @Override
	    public Vehiculo fromDTO(VehiculoDto dto) {
	        if (dto == null) return null;

	        return Vehiculo.builder()
	        		.id(dto.getId())
	                .tipoVehiculo(dto.getTipoVehiculo())
	                .placa(dto.getPlaca())
	                .numeroPisos(dto.getNumeroPisos())
	                .estado(dto.getEstado())
	                .build();
	    }
}
