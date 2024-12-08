package com.civitas.pasajes.buses.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.civitas.pasajes.buses.entity.Vehiculo;
import com.civitas.pasajes.buses.repository.VehiculoRepository;
import com.civitas.pasajes.buses.service.VehiculoService;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    // Inyección del repositorio de Vehiculo
    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    public List<Vehiculo> findAll() {
        // Devuelve todos los vehículos
        return vehiculoRepository.findAll();
    }

    @Override
    public Vehiculo findById(int id) {
        // Buscar un vehículo por su ID, si no existe, retorna null o lanza una excepción personalizada.
        Optional<Vehiculo> vehiculo = vehiculoRepository.findById(id);
        return vehiculo.orElse(null); // Si no se encuentra, retorna null
    }

    @Override
    public Vehiculo save(Vehiculo vehiculo) {
        // Guarda el vehículo, ya sea un nuevo vehículo o una actualización
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public void delete(int id) {
        // Elimina un vehículo por su ID
        vehiculoRepository.deleteById(id);
    }
}
