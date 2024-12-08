package com.civitas.pasajes.buses.dto;




import java.util.Date;

import com.civitas.pasajes.buses.entity.Persona;

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
public class AuthUserDto {
	private String correo;
	private String usuario;
	private String tipoUsuario;
	private Persona persona;
	private String token;
	private Date duracionTk;
}
