package com.civitas.pasajes.buses.dto;


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
public class DestinosDto {

	private int id;

	private DistritoDto direccionSalida;

	private DistritoDto direccionLlegada;

	private int kilometros;
	
	private boolean estado;
	
	private int horas;

	private float costoBase;

}
