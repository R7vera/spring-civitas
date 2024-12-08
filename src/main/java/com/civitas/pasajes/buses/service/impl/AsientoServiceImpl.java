package com.civitas.pasajes.buses.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.civitas.pasajes.buses.entity.Asiento;
import com.civitas.pasajes.buses.repository.AsientoRepository;
import com.civitas.pasajes.buses.repository.UsuarioRepository;
import com.civitas.pasajes.buses.service.AsientoService;
import com.civitas.pasajes.buses.service.AuthService;

@Service
public class AsientoServiceImpl implements AsientoService {


    @Autowired
    private AsientoRepository repository;
    public List<Asiento> obtenerAsientosPorBus(int idVehiculo) {
        return repository.findByVehiculoId(idVehiculo);
    }

}
