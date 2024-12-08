package com.civitas.pasajes.buses.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.civitas.pasajes.buses.entity.Distrito;
import com.civitas.pasajes.buses.exception.GeneralException;
import com.civitas.pasajes.buses.exception.NoDataFoundException;
import com.civitas.pasajes.buses.exception.ValidateException;
import com.civitas.pasajes.buses.repository.DistritoRepository;
import com.civitas.pasajes.buses.service.DistritoService;
import com.civitas.pasajes.buses.validator.DistritoValidator;

@Service
public class DistritoServiceImpl implements DistritoService {

    @Autowired
    private DistritoRepository repository;


    @Override
    public Distrito findByNombre(String nombre) {
        return repository.findByNombre(nombre);
    }
    
	@Override
	@Transactional(readOnly = true)
	public List<Distrito> findAll() {
		try {
			List<Distrito> registros = repository.findAll();
			return registros;
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		} catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}
	}


    @Override
    public Distrito findById(int id) {
        return repository.findById(id).orElse(null);
    }

    
	@Override
	@Transactional
	public Distrito save(Distrito departamento) {
		try {

			DistritoValidator.save(departamento);
			// Nuevo registro
			System.out.println("Distrito a guardar: " + departamento);
			if (departamento.getId() == 0) {
				Distrito nuevo = repository.save(departamento);				
				return nuevo;
			}
			// editar registro
			Distrito registro = repository.findById(departamento.getId())
					.orElseThrow(() -> new NoDataFoundException("No existe un registro con ese ID"));
			registro.setNombre(departamento.getNombre());
			registro.setUrlImg(departamento.getUrlImg());
			registro.setDireccion(departamento.getDireccion());
			registro.setCiudad(departamento.getCiudad());
			registro.setDireccion(departamento.getDireccion());
			registro.setReferencia(departamento.getReferencia());
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
		Distrito departamento = repository.findById(id)
	        .orElseThrow(() -> new NoDataFoundException("No se encontró un departamento con el ID proporcionado"));

	    // Elimina el país si existe
		repository.delete(departamento);
	}

}