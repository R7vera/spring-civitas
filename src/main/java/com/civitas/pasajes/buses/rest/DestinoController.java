package com.civitas.pasajes.buses.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civitas.pasajes.buses.converter.DestinosConverter;
import com.civitas.pasajes.buses.dto.DestinosDto;
import com.civitas.pasajes.buses.entity.Destinos;
import com.civitas.pasajes.buses.exception.NoDataFoundException;
import com.civitas.pasajes.buses.service.DestinoService;
import com.civitas.pasajes.buses.util.WrapperResponse;

@RestController
@RequestMapping("/api/v1/destinos")
public class DestinoController {
	@Autowired
	private DestinoService service;

	@Autowired
	private DestinosConverter converter;

	@GetMapping
	public ResponseEntity<List<DestinosDto>> findAll() {
		List<DestinosDto> registros = converter.fromEntity(service.findAll());
		return new WrapperResponse(true, "success", registros).createResponse(HttpStatus.OK);
	}

	

	@PostMapping
	public ResponseEntity<DestinosDto> create(@RequestBody DestinosDto destino) {
		Destinos entity = converter.fromDTO(destino);
		DestinosDto registro = converter.fromEntity(service.save(entity));
		return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<DestinosDto> update(@PathVariable("id") int id, @RequestBody DestinosDto destino) {
		destino.setId(id);
		Destinos entity = converter.fromDTO(destino);
		DestinosDto registro = converter.fromEntity(service.save(entity));
		return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		try {
			service.delete(id);
			return new WrapperResponse(true, "Destino eliminado correctamente", null).createResponse(HttpStatus.OK);
		} catch (DataIntegrityViolationException e) {
			return new WrapperResponse(false, "No se puede eliminar el destino porque está siendo utilizado", null)
					.createResponse(HttpStatus.CONFLICT);
		} catch (NoDataFoundException e) {
			return new WrapperResponse(false, e.getMessage(), null).createResponse(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new WrapperResponse(false, "Ocurrió un error interno", null)
					.createResponse(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
}
