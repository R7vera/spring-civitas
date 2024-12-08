package com.civitas.pasajes.buses.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.civitas.pasajes.buses.entity.Persona;
import com.civitas.pasajes.buses.exception.GeneralException;
import com.civitas.pasajes.buses.exception.NoDataFoundException;
import com.civitas.pasajes.buses.exception.ValidateException;

import com.civitas.pasajes.buses.repository.PersonaRepository;

import com.civitas.pasajes.buses.service.PersonaService;

import com.civitas.pasajes.buses.validator.PersonaValidator;

@Service
public class PersonaServiceImpl implements PersonaService {
	@Autowired
	private PersonaRepository repository;

	@Override
	public Persona findByNombre(String nombre) {
		return repository.findByNombres(nombre);
	}
	
	@Override
	public Persona finByDocumentoIdentidad(String dni) {
		return repository.findByDocumentoIdentidad(dni);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Persona> findAll() {
		try {
			List<Persona> registros = repository.findAll();
			return registros;
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		} catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}
	}

	@Override
	public Persona findById(int id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Persona save(Persona persona) {
		try {

			PersonaValidator.save(persona);
			// Nuevo registro
			if (persona.getId() == 0) {

				Persona nuevo = repository.save(persona);
				return nuevo;
			}
			// editar registro
			Persona registro = repository.findById(persona.getId())
					.orElseThrow(() -> new NoDataFoundException("No existe un registro con ese ID"));
			registro.setNombres(persona.getNombres());
			registro.setApellidoPaterno(persona.getApellidoPaterno());
			registro.setApellidoMaterno(persona.getApellidoMaterno());
			registro.setDocumentoIdentidad(persona.getDocumentoIdentidad());
			registro.setTipoDocumento(persona.getTipoDocumento());
			repository.save(registro);
			return registro;
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		} catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}
	}

	@Override
	public void delete(int id) {
		// Verifica si el país existe
		Persona persona = repository.findById(id)
				.orElseThrow(() -> new NoDataFoundException("No se encontró un país con el ID proporcionado"));

		// Elimina el país si existe
		repository.delete(persona);
	}
}
