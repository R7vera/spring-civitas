package com.civitas.pasajes.buses.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.civitas.pasajes.buses.entity.TipoVehiculo;
import com.civitas.pasajes.buses.exception.GeneralException;
import com.civitas.pasajes.buses.exception.NoDataFoundException;
import com.civitas.pasajes.buses.exception.ValidateException;
import com.civitas.pasajes.buses.repository.TipoVehiculoRepository;
import com.civitas.pasajes.buses.service.TipoVehiculoService;

@Service
public class TipoVehiculoServiceImpl implements TipoVehiculoService {
	@Autowired
	private TipoVehiculoRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<TipoVehiculo> findAll() {
		try {
			List<TipoVehiculo> registros = repository.findAll();
			return registros;
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		} catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}
	}
}