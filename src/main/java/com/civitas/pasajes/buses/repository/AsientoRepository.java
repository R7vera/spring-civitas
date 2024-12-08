package com.civitas.pasajes.buses.repository;

import com.civitas.pasajes.buses.entity.Asiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsientoRepository extends JpaRepository<Asiento, Integer> {

    // Método para obtener los asientos de un vehículo (bus) específico
    List<Asiento> findByVehiculoId(int idVehiculo);

}
