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
public class DistritoDto {

	private int id;
	private String nombre;
	private String urlImg;
	private String direccion;
	private String referencia;
	private String ciudad;
    private String pais;
    private String departamento;
    

}
