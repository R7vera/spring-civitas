package com.civitas.pasajes.buses.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.civitas.pasajes.buses.entity.Ciudad;
import com.civitas.pasajes.buses.exception.GeneralException;
import com.civitas.pasajes.buses.exception.NoDataFoundException;
import com.civitas.pasajes.buses.exception.ValidateException;
import com.civitas.pasajes.buses.repository.CiudadRepository;
import com.civitas.pasajes.buses.service.CiudadService;
import com.civitas.pasajes.buses.validator.CiudadValidator;

@Service
public class CiudadServiceImpl implements CiudadService {

    @Autowired
    private CiudadRepository repository;


    @Override
    public Ciudad findByNombre(String nombre) {
        return repository.findByNombre(nombre);
    }
    
	@Override
	@Transactional(readOnly = true)
	public List<Ciudad> findAll() {
		try {
			List<Ciudad> registros = repository.findAll();
			return registros;
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		} catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}
	}


    @Override
    public Ciudad findById(int id) {
        return repository.findById(id).orElse(null);
    }

    
	@Override
	@Transactional
	public Ciudad save(Ciudad ciudad) {
		try {

			CiudadValidator.save(ciudad);
			// Nuevo registro
			if (ciudad.getId() == 0) {
				
				Ciudad nuevo = repository.save(ciudad);				
				return nuevo;
			}
			// editar registro
			Ciudad registro = repository.findById(ciudad.getId())
					.orElseThrow(() -> new NoDataFoundException("No existe un registro con ese ID"));
			registro.setNombre(ciudad.getNombre());
			registro.setUrlImg(ciudad.getUrlImg());
			registro.setDepartamento(ciudad.getDepartamento());
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
		Ciudad ciudad = repository.findById(id)
	        .orElseThrow(() -> new NoDataFoundException("No se encontró un ciudad con el ID proporcionado"));

	    // Elimina el país si existe
		repository.delete(ciudad);
	}

}