package com.civitas.pasajes.buses.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.civitas.pasajes.buses.entity.Chofer;
import com.civitas.pasajes.buses.entity.Persona;
import com.civitas.pasajes.buses.exception.GeneralException;
import com.civitas.pasajes.buses.exception.NoDataFoundException;
import com.civitas.pasajes.buses.exception.ValidateException;
import com.civitas.pasajes.buses.repository.ChoferRepository;

import com.civitas.pasajes.buses.service.ChoferService;
import com.civitas.pasajes.buses.service.PersonaService;

@Service
public class ChoferServiceImpl implements ChoferService {

    @Autowired
    private ChoferRepository repository;

    @Autowired
    private PersonaService service;


	@Override
	@Transactional(readOnly = true)
	public List<Chofer> findAll() {
		try {
			List<Chofer> registros = repository.findAll();
			return registros;
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		} catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}
	}



	@Override
	@Transactional
	public Chofer save(Chofer chofer) {
		try {

			// Nuevo registro
			if (chofer.getId() == 0) {
				
				Chofer nuevo = repository.save(chofer);				
				return nuevo;
			}
			// editar registro
			Chofer registro = repository.findById(chofer.getId())
					.orElseThrow(() -> new NoDataFoundException("No existe un registro con ese ID"));
			Persona persona = service.save(chofer.getPersona());
			registro.setPersona(chofer.getPersona());
			registro.setNumeroLicencia(chofer.getNumeroLicencia());
			registro.setEstado(chofer.isEstado());
			registro.setTipoLicencia(chofer.getTipoLicencia());
			
		
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
		Chofer chofer = repository.findById(id)
	        .orElseThrow(() -> new NoDataFoundException("No se encontró un Chofer con el ID proporcionado"));

	    // Elimina el país si existe
		repository.delete(chofer);
	}
}
