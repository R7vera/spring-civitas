package com.civitas.pasajes.buses.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.civitas.pasajes.buses.entity.Destinos;
import com.civitas.pasajes.buses.entity.Vehiculo;
import com.civitas.pasajes.buses.exception.GeneralException;
import com.civitas.pasajes.buses.exception.NoDataFoundException;
import com.civitas.pasajes.buses.exception.ValidateException;
import com.civitas.pasajes.buses.repository.DestinoRepository;
import com.civitas.pasajes.buses.service.DestinoService;
import com.civitas.pasajes.buses.validator.DistritoValidator;

@Service
public class DestinoServiceImpl implements DestinoService {

    @Autowired
    private DestinoRepository repository;


	@Override
	@Transactional(readOnly = true)
	public List<Destinos> findAll() {
		try {
			List<Destinos> registros = repository.findAll();
			return registros;
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		} catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}
	}

	@Override
    public Destinos findById(int id) {
        // Buscar un vehículo por su ID, si no existe, retorna null o lanza una excepción personalizada.
        Optional<Destinos> vehiculo = repository.findById(id);
        return vehiculo.orElse(null); // Si no se encuentra, retorna null
    }
	
	@Override
	@Transactional
	public Destinos save(Destinos destinos) {
		try {

			// Nuevo registro
			if (destinos.getId() == 0) {
				
				Destinos nuevo = repository.save(destinos);				
				return nuevo;
			}
			// editar registro
			Destinos registro = repository.findById(destinos.getId())
					.orElseThrow(() -> new NoDataFoundException("No existe un registro con ese ID"));
			registro.setCostoBase(destinos.getCostoBase());
			registro.setDireccionLlegada(destinos.getDireccionLlegada());
			registro.setDireccionSalida(destinos.getDireccionSalida());
			registro.setEstado(destinos.isEstado());
			registro.setHoras(destinos.getHoras());
			registro.setKilometros(destinos.getKilometros());
			
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
		Destinos destinos = repository.findById(id)
	        .orElseThrow(() -> new NoDataFoundException("No se encontró un departamento con el ID proporcionado"));

	    // Elimina el país si existe
		repository.delete(destinos);
	}

}