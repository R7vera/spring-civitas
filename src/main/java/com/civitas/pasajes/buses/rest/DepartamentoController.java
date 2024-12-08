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

import com.civitas.pasajes.buses.converter.DepartamentoConverter;
import com.civitas.pasajes.buses.dto.DepartamentoDto;
import com.civitas.pasajes.buses.dto.DistritoDto;
import com.civitas.pasajes.buses.entity.Departamento;
import com.civitas.pasajes.buses.exception.NoDataFoundException;
import com.civitas.pasajes.buses.service.DepartamentoService;
import com.civitas.pasajes.buses.util.WrapperResponse;

@RestController
@RequestMapping("/api/v1/departamentos")
public class DepartamentoController {
	@Autowired
	private DepartamentoService service;

	@Autowired
	private DepartamentoConverter converter;

	@GetMapping
	public ResponseEntity<List<DepartamentoDto>> findAll() {
		List<DepartamentoDto> registros = converter.fromEntity(service.findAll());
		return new WrapperResponse(true, "success", registros).createResponse(HttpStatus.OK);
	}

	@GetMapping("/buscar/{nombre}")
	public ResponseEntity<?> buscarPorNombre(@PathVariable("nombre") String nombre) {
		try {

			Departamento departamento = service.findByNombre(nombre);

			if (departamento == null) {
				throw new NoDataFoundException("No se encontró un Departamento con el nombre proporcionado");
			}
			DepartamentoDto registro = converter.fromEntity(departamento);
			return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.OK);

		} catch (NoDataFoundException e) {

			return new WrapperResponse(false, e.getMessage(), null).createResponse(HttpStatus.NOT_FOUND);
		} catch (Exception e) {

			return new WrapperResponse(false, "Ocurrió un error interno", null)
					.createResponse(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<DepartamentoDto> create(@RequestBody DepartamentoDto departamento) {
		Departamento Entity = converter.fromDTO(departamento);
		DepartamentoDto registro = converter.fromEntity(service.save(Entity));
		return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Departamento> update(@PathVariable("id") int id, @RequestBody Departamento departamento) {
		departamento.setId(id);
		Departamento updatedPais = service.save(departamento);
		return new WrapperResponse(true, "success", updatedPais).createResponse(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		try {

			service.delete(id);
			return new WrapperResponse(true, "Departamento eliminado correctamente", null)
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
	public ResponseEntity<Departamento> findById(@PathVariable("id") int id) {
		Departamento pais = service.findById(id);
		return new WrapperResponse(true, "success", pais).createResponse(HttpStatus.OK);
	}
}
