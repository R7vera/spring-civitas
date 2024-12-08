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

import com.civitas.pasajes.buses.converter.CiudadConverter;
import com.civitas.pasajes.buses.dto.CiudadDto;
import com.civitas.pasajes.buses.entity.Ciudad;
import com.civitas.pasajes.buses.exception.NoDataFoundException;
import com.civitas.pasajes.buses.service.CiudadService;
import com.civitas.pasajes.buses.util.WrapperResponse;

@RestController
@RequestMapping("/api/v1/ciudades")
public class CiudadController {
	@Autowired
	private CiudadService service;

	@Autowired
	private CiudadConverter converter;

	@GetMapping
	public ResponseEntity<List<CiudadDto>> findAll() {
		List<CiudadDto> registros = converter.fromEntity(service.findAll());
		return new WrapperResponse(true, "success", registros).createResponse(HttpStatus.OK);
	}

	@GetMapping("/buscar/{nombre}")
	public ResponseEntity<?> buscarPorNombre(@PathVariable("nombre") String nombre) {
		try {

			Ciudad ciudad = service.findByNombre(nombre);

			if (ciudad == null) {
				throw new NoDataFoundException("No se encontró un ciudad con el nombre proporcionado");
			}
			CiudadDto registro = converter.fromEntity(ciudad);
			return new WrapperResponse(true, "success", ciudad).createResponse(HttpStatus.OK);

		} catch (NoDataFoundException e) {

			return new WrapperResponse(false, e.getMessage(), null).createResponse(HttpStatus.NOT_FOUND);
		} catch (Exception e) {

			return new WrapperResponse(false, "Ocurrió un error interno", null)
					.createResponse(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<CiudadDto> create(@RequestBody CiudadDto ciudad) {
		Ciudad Entity = converter.fromDTO(ciudad);
		CiudadDto registro = converter.fromEntity(service.save(Entity));
		return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Ciudad> update(@PathVariable("id") int id, @RequestBody CiudadDto ciudad) {
		ciudad.setId(id);
		Ciudad Entity = converter.fromDTO(ciudad);
		CiudadDto registro = converter.fromEntity(service.save(Entity));
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
	public ResponseEntity<Ciudad> findById(@PathVariable("id") int id) {
		Ciudad ciudad = service.findById(id);
		CiudadDto registro = converter.fromEntity(ciudad);
		return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.OK);
	}
}
