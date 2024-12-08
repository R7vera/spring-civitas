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

import com.civitas.pasajes.buses.converter.ProgramacionConverter;
import com.civitas.pasajes.buses.dto.ProgramacionDto;
import com.civitas.pasajes.buses.entity.Pais;
import com.civitas.pasajes.buses.entity.Programacion;
import com.civitas.pasajes.buses.exception.NoDataFoundException;
import com.civitas.pasajes.buses.service.ProgramacionService;
import com.civitas.pasajes.buses.util.WrapperResponse;


@RestController
@RequestMapping("/api/v1/programacion")
public class ProgramacionController {

    @Autowired
    private ProgramacionService service;

	@Autowired
	private ProgramacionConverter converter;
 
    @GetMapping
	public ResponseEntity<List<ProgramacionDto>> findAll(){				
		List<ProgramacionDto> registros = converter.fromEntity(service.findAll());		
		return new WrapperResponse(true, "success", registros).createResponse(HttpStatus.OK);
	}
	
    

  
	@PostMapping
	public ResponseEntity<ProgramacionDto> create (@RequestBody ProgramacionDto pais){
		Programacion paisEntity=converter.fromDTO(pais);
		ProgramacionDto registro = converter.fromEntity(service.save(paisEntity));
		return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.CREATED);
	}
  
    @PutMapping("/{id}")
    public ResponseEntity<Pais> update(@PathVariable("id") int id, @RequestBody Programacion pais) {
        pais.setId(id);  
        Programacion updatedPais = service.save(pais);
        return new WrapperResponse(true, "success", updatedPais).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        try {
            
        	service.delete(id);
            return new WrapperResponse(true, "Programacion eliminado correctamente", null).createResponse(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            
            return new WrapperResponse(false, "No se puede eliminar el Programacion porque está siendo utilizado", null)
                    .createResponse(HttpStatus.CONFLICT);
        } catch (NoDataFoundException e) {
            
            return new WrapperResponse(false, e.getMessage(), null).createResponse(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            
            return new WrapperResponse(false, "Ocurrió un error interno", null).createResponse(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
