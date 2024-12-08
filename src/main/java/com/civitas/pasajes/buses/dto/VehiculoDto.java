package com.civitas.pasajes.buses.dto;

import com.civitas.pasajes.buses.entity.Persona;
import com.civitas.pasajes.buses.entity.TipoVehiculo;
import com.civitas.pasajes.buses.entity.Usuario.TipoUsuario;
import com.civitas.pasajes.buses.entity.Vehiculo.EstadoVehiculo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehiculoDto {
	 private int id;
	    private TipoVehiculo tipoVehiculo;
	    private String placa;
	    private int numeroPisos;
	    private EstadoVehiculo estado;
}
