package com.civitas.pasajes.buses.converter;

import org.springframework.stereotype.Component;

import com.civitas.pasajes.buses.dto.PersonaDto;

import com.civitas.pasajes.buses.entity.Persona;


@Component
public class PersonaConverter extends AbstractConverter<Persona, PersonaDto> {

	@Override
	public PersonaDto fromEntity(Persona entity) {
		if (entity == null)
			return null;

		return PersonaDto.builder()
				.id(entity.getId())
				.nombres(entity.getNombres())
				.apellidoMaterno(entity.getApellidoMaterno())
				.apellidoPaterno(entity.getApellidoPaterno())
				.documentoIdentidad(entity.getDocumentoIdentidad())
				.tipoDocumento(entity.getTipoDocumento())
				.build();
	}

	@Override
	public Persona fromDTO(PersonaDto dto) {
		if (dto == null)
			return null;

		return Persona.builder()
				.id(dto.getId())
				.nombres(dto.getNombres())
				.apellidoMaterno(dto.getApellidoMaterno())
				.apellidoPaterno(dto.getApellidoPaterno())
				.documentoIdentidad(dto.getDocumentoIdentidad())
				.TipoDocumento(dto.getTipoDocumento())
				.build();
	}
}
