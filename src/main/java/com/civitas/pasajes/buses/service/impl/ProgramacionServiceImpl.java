package com.civitas.pasajes.buses.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.civitas.pasajes.buses.entity.Programacion;

import com.civitas.pasajes.buses.exception.GeneralException;
import com.civitas.pasajes.buses.exception.NoDataFoundException;
import com.civitas.pasajes.buses.exception.ValidateException;
import com.civitas.pasajes.buses.repository.ProgramacionRepository;

import com.civitas.pasajes.buses.service.ProgramacionService;

@Service
public class ProgramacionServiceImpl implements ProgramacionService {

    @Autowired
    private ProgramacionRepository repository;


	@Override
	@Transactional(readOnly = true)
	public List<Programacion> findAll() {
		try {
			List<Programacion> registros = repository.findAll();
			return registros;
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		} catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}
	}



	@Override
	@Transactional
	public Programacion save(Programacion progra) {
		try {

			// Nuevo registro
			if (progra.getId() == 0) {
				
				Programacion nuevo = repository.save(progra);				
				return nuevo;
			}
			// editar registro
			Programacion registro = repository.findById(progra.getId())
					.orElseThrow(() -> new NoDataFoundException("No existe un registro con ese ID"));
			registro = progra;
			
		
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
		Programacion chofer = repository.findById(id)
	        .orElseThrow(() -> new NoDataFoundException("No se encontró un Chofer con el ID proporcionado"));

	    // Elimina el país si existe
		repository.delete(chofer);
	}




}
