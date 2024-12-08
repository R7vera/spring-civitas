package com.civitas.pasajes.buses.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civitas.pasajes.buses.converter.AsientoConverter;
import com.civitas.pasajes.buses.dto.AsientoDto;
import com.civitas.pasajes.buses.entity.*;
import com.civitas.pasajes.buses.service.AsientoService;
import com.civitas.pasajes.buses.util.WrapperResponse;

@RestController
@RequestMapping("/api/v1/asientos")
public class AsientoController {

	@Autowired
	private AsientoService service;
	@Autowired
	private AsientoConverter converter;

	@GetMapping("/bus/{idVehiculo}")
	public ResponseEntity<AsientoDto> obtenerAsientosPorBus(@PathVariable("idVehiculo") int idVehiculo) {
	    List<AsientoDto> registros = converter.fromEntity(service.obtenerAsientosPorBus(idVehiculo));
	    return new WrapperResponse(true, "success", registros).createResponse(HttpStatus.OK);
	}

}
