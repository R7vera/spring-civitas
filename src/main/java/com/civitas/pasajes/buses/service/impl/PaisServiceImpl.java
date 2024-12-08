package com.civitas.pasajes.buses.service.impl;


import com.civitas.pasajes.buses.exception.GeneralException;
import com.civitas.pasajes.buses.exception.NoDataFoundException;
import com.civitas.pasajes.buses.exception.ValidateException;

import com.civitas.pasajes.buses.entity.Pais;
import com.civitas.pasajes.buses.repository.PaisRepository;
import com.civitas.pasajes.buses.service.PaisService;
import com.civitas.pasajes.buses.validator.PaisValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaisServiceImpl implements PaisService {

    @Autowired
    private PaisRepository paisRepository;


    @Override
    public Pais findByNombre(String nombre) {
        return paisRepository.findByNombre(nombre);
    }
    
	@Override
	@Transactional(readOnly = true)
	public List<Pais> findAll() {
		try {
			List<Pais> registros = paisRepository.findAll();
			return registros;
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		} catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}
	}


    @Override
    public Pais findById(int id) {
        return paisRepository.findById(id).orElse(null);
    }

    
	@Override
	@Transactional
	public Pais save(Pais pais) {
		try {

			PaisValidator.save(pais);
			// Nuevo registro
			if (pais.getId() == 0) {
				
				Pais nuevo = paisRepository.save(pais);				
				return nuevo;
			}
			// editar registro
			Pais registro = paisRepository.findById(pais.getId())
					.orElseThrow(() -> new NoDataFoundException("No existe un registro con ese ID"));
			registro.setNombre(pais.getNombre());
			registro.setUrl(pais.getUrl());
			paisRepository.save(registro);
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
	    Pais pais = paisRepository.findById(id)
	        .orElseThrow(() -> new NoDataFoundException("No se encontró un país con el ID proporcionado"));

	    // Elimina el país si existe
	    paisRepository.delete(pais);
	}

}
