package com.civitas.pasajes.buses.service;

import java.util.List;

import com.civitas.pasajes.buses.entity.*;


public interface AsientoService {
	public List<Asiento> obtenerAsientosPorBus(int idVehiculo);
}
