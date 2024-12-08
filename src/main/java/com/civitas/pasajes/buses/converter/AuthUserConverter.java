package com.civitas.pasajes.buses.converter;

import org.springframework.stereotype.Component;

import com.civitas.pasajes.buses.dto.AuthUserDto;

import com.civitas.pasajes.buses.entity.Usuario;
import com.civitas.pasajes.buses.entity.Persona;
import com.civitas.pasajes.buses.entity.Usuario.TipoUsuario;

@Component
public class AuthUserConverter extends AbstractConverter<Usuario, AuthUserDto>  {
	

    @Override
    public AuthUserDto fromEntity(Usuario entity) {
        if (entity == null) return null;
        Persona persona = entity.getPersona();
        return AuthUserDto.builder()
                .correo(entity.getCorreo())
        		.usuario(entity.getUsuario())
        		.tipoUsuario(entity.getTipoUsuario().name())
        		.persona(persona)
        	
                .build();
    }
    
    
    

    @Override
    public Usuario fromDTO(AuthUserDto dto) {
        if (dto == null) return null;
        TipoUsuario tipoUsuario = TipoUsuario.valueOf(dto.getTipoUsuario());
        return Usuario.builder()
                .correo(dto.getCorreo())
                .usuario(dto.getUsuario())
                .tipoUsuario(tipoUsuario)
                .build();
    }

}
