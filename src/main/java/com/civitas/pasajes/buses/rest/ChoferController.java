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

import com.civitas.pasajes.buses.converter.ChoferConverter;
import com.civitas.pasajes.buses.converter.TipoLicenciaConverter;
import com.civitas.pasajes.buses.dto.ChoferDto;
import com.civitas.pasajes.buses.dto.TipoLicenciaDto;
import com.civitas.pasajes.buses.entity.Chofer;
import com.civitas.pasajes.buses.entity.Persona;
import com.civitas.pasajes.buses.exception.NoDataFoundException;
import com.civitas.pasajes.buses.service.ChoferService;
import com.civitas.pasajes.buses.service.TipoLicenciaService;
import com.civitas.pasajes.buses.service.PersonaService;
import com.civitas.pasajes.buses.util.WrapperResponse;

@RestController
@RequestMapping("/api/v1/choferes")
public class ChoferController {
	@Autowired
	private ChoferService service;
	
	@Autowired
	private PersonaService personaService;
	
	@Autowired
	private TipoLicenciaService TipoService;

	@Autowired
	private ChoferConverter converter;

	@Autowired
	private TipoLicenciaConverter converterTipo;
	
	@GetMapping
	public ResponseEntity<List<ChoferDto>> findAll() {
		List<ChoferDto> registros = converter.fromEntity(service.findAll());
		return new WrapperResponse(true, "success", registros).createResponse(HttpStatus.OK);
	}

	@GetMapping("/tipolicencia")
	public ResponseEntity<List<TipoLicenciaDto>> findAllTipoLicencia() {
		List<TipoLicenciaDto> registros = converterTipo.fromEntity(TipoService.findAll());
		return new WrapperResponse(true, "success", registros).createResponse(HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ChoferDto> create(@RequestBody ChoferDto chofer) {
	    // Obtiene la persona del DTO
	    Persona registroPersona = chofer.getPersona();
	    
	    // Verifica si la persona ya está registrada por su DNI (usando un servicio que verifique la existencia)
	    Persona personaExistente = personaService.finByDocumentoIdentidad(registroPersona.getDocumentoIdentidad());

	    if (personaExistente != null) {
	        // Si ya existe una persona con el mismo DNI, asignamos esa persona al chofer
	        registroPersona = personaExistente;
	        // Mensaje de éxito con el cliente ya existente
	        return new WrapperResponse(true, "Persona ya existente, asignada al chofer correctamente.", chofer).createResponse(HttpStatus.OK);
	    } 
	        // Si no existe, guardamos la nueva persona
	    registroPersona = personaService.save(registroPersona);
	       // Mensaje de éxito al guardar la nueva persona

	    

	    // Asocia la persona al chofer
	    chofer.setPersona(registroPersona);
	    
	    // Convierte el DTO a entidad
	    Chofer entity = converter.fromDTO(chofer);
	    
	    // Guarda el chofer y convierte la entidad de vuelta al DTO
	    ChoferDto registro = converter.fromEntity(service.save(entity));

	    // Retorna la respuesta
	    return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ChoferDto> update(@PathVariable("id") int id, @RequestBody ChoferDto chofer) {
		chofer.setId(id);
		Chofer entity = converter.fromDTO(chofer);
		ChoferDto registro = converter.fromEntity(service.save(entity));
		return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		try {
			service.delete(id);
			return new WrapperResponse(true, "Chofer eliminado correctamente", null).createResponse(HttpStatus.OK);
		} catch (DataIntegrityViolationException e) {
			return new WrapperResponse(false, "No se puede eliminar el Chofer porque está siendo utilizado", null)
					.createResponse(HttpStatus.CONFLICT);
		} catch (NoDataFoundException e) {
			return new WrapperResponse(false, e.getMessage(), null).createResponse(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new WrapperResponse(false, "Ocurrió un error interno", null)
					.createResponse(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
