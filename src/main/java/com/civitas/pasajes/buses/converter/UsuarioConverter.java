package com.civitas.pasajes.buses.converter;

import org.springframework.stereotype.Component;


import com.civitas.pasajes.buses.dto.UsuarioDto;

import com.civitas.pasajes.buses.entity.Usuario;

@Component
public class UsuarioConverter extends AbstractConverter<Usuario, UsuarioDto>  {
	 @Override
	    public UsuarioDto fromEntity(Usuario entity) {
	        if (entity == null) return null;

	        return UsuarioDto.builder()
	                .id(entity.getId())
	                .persona(entity.getPersona())
	                .correo(entity.getCorreo())
	                .usuario(entity.getCorreo())
	                .tipoUsuario(entity.getTipoUsuario())
	                .contrasenia(entity.getContrasenia())
	                .build();
	    }

	    @Override
	    public Usuario fromDTO(UsuarioDto dto) {
	        if (dto == null) return null;

	        return Usuario.builder()
	                .id(dto.getId())
	                .persona(dto.getPersona())
	                .correo(dto.getCorreo())
	                .usuario(dto.getCorreo())
	                .tipoUsuario(dto.getTipoUsuario())
	                .contrasenia(dto.getContrasenia())
	                .build();
	    }
}
