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

import com.civitas.pasajes.buses.converter.DistritoConverter;

import com.civitas.pasajes.buses.dto.DistritoDto;

import com.civitas.pasajes.buses.entity.Distrito;
import com.civitas.pasajes.buses.exception.NoDataFoundException;
import com.civitas.pasajes.buses.service.DistritoService;
import com.civitas.pasajes.buses.util.WrapperResponse;

@RestController
@RequestMapping("/api/v1/distritos")
public class DistritoController {
	@Autowired
	private DistritoService service;

	@Autowired
	private DistritoConverter converter;

	@GetMapping
	public ResponseEntity<List<DistritoDto>> findAll() {
		List<DistritoDto> registros = converter.fromEntity(service.findAll());
		return new WrapperResponse(true, "success", registros).createResponse(HttpStatus.OK);
	}

	@GetMapping("/buscar/{nombre}")
	public ResponseEntity<?> buscarPorNombre(@PathVariable("nombre") String nombre) {
		try {

			Distrito distrito = service.findByNombre(nombre);

			if (distrito == null) {
				throw new NoDataFoundException("No se encontró un Distrito con el nombre proporcionado");
			}
			DistritoDto registro = converter.fromEntity(distrito);
			return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.OK);

		} catch (NoDataFoundException e) {

			return new WrapperResponse(false, e.getMessage(), null).createResponse(HttpStatus.NOT_FOUND);
		} catch (Exception e) {

			return new WrapperResponse(false, "Ocurrió un error interno", null)
					.createResponse(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<DistritoDto> create(@RequestBody DistritoDto distrito) {
		System.out.println("Distrito a guardar: " + distrito);
		Distrito Entity = converter.fromDTO(distrito);
		DistritoDto registro = converter.fromEntity(service.save(Entity));
		return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Distrito> update(@PathVariable("id") int id, @RequestBody DistritoDto distrito) {
		distrito.setId(id);
		Distrito Entity = converter.fromDTO(distrito);
		DistritoDto registro = converter.fromEntity(service.save(Entity));
		return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		try {

			service.delete(id);
			return new WrapperResponse(true, "Ciudad eliminado correctamente", null)
					.createResponse(HttpStatus.OK);
		} catch (DataIntegrityViolationException e) {

			return new WrapperResponse(false, "No se puede eliminar el Departamento porque está siendo utilizado", null)
					.createResponse(HttpStatus.CONFLICT);
		} catch (NoDataFoundException e) {

			return new WrapperResponse(false, e.getMessage(), null).createResponse(HttpStatus.NOT_FOUND);
		} catch (Exception e) {

			return new WrapperResponse(false, "Ocurrió un error interno", null)
					.createResponse(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Distrito> findById(@PathVariable("id") int id) {
		Distrito ciudad = service.findById(id);
		DistritoDto registro = converter.fromEntity(ciudad);
		return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.OK);
	}
}


