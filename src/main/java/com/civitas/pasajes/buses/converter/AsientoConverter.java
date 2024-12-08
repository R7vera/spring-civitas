package com.civitas.pasajes.buses.converter;

import org.springframework.stereotype.Component;

import com.civitas.pasajes.buses.dto.AsientoDto;
import com.civitas.pasajes.buses.dto.ChoferDto;
import com.civitas.pasajes.buses.entity.Chofer;
import com.civitas.pasajes.buses.entity.Asiento;
import com.civitas.pasajes.buses.entity.Asiento.EstadoAsiento;
import com.civitas.pasajes.buses.entity.Asiento.TipoAsiento;

@Component
public class AsientoConverter extends AbstractConverter<Asiento, AsientoDto>{

	@Override
	public AsientoDto fromEntity(Asiento entity) {
		if (entity == null)
			return null;

		return AsientoDto.builder()
				.id(entity.getId())
				.numero(entity.getNumero())
				.vehiculo(entity.getVehiculo())
				.tipoAsiento(entity.getTipoAsiento())
				.precioBase(entity.getPrecioBase())
				.estado(entity.getEstado())
				.build();
	}

	@Override
	public Asiento fromDTO(AsientoDto dto) {
		if (dto == null)
			return null;
		
		
		return Asiento.builder()
				.id(dto.getId())
				.numero(dto.getNumero())
				.vehiculo(dto.getVehiculo())
				.tipoAsiento(dto.getTipoAsiento())
				.precioBase(dto.getPrecioBase())
				.estado(dto.getEstado())
				.build();
	}
}


//private int id;


//private int numero;


//private Vehiculo vehiculo;


//private TipoAsiento tipoAsiento;  // Enum 'Ventana', 'Pasillo', 'Delantero'


//private float precioBase;


//private EstadoAsiento estado;