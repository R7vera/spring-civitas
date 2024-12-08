package com.civitas.pasajes.buses.service;

import java.util.List;

import com.civitas.pasajes.buses.entity.Destinos;
import com.civitas.pasajes.buses.entity.Vehiculo;

public interface DestinoService {

	public List<Destinos> findAll();
    // Buscar País por ID
	public Destinos findById(int id);

    // Guardar un nuevo País
    public Destinos save(Destinos destinos);

    // Eliminar un País por ID
    public void delete(int id);
}
