package com.civitas.pasajes.buses.dto;



import com.civitas.pasajes.buses.entity.Persona;
import com.civitas.pasajes.buses.entity.TipoLicencia;

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
public class ChoferDto {
	 private int id;
	 private Persona persona;
	 private String numeroLicencia;
	 private TipoLicencia tipoLicencia;
	 private boolean estado;
}
