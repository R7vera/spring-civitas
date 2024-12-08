package com.civitas.pasajes.buses.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.civitas.pasajes.buses.converter.TipoVehiculoConverter;
import com.civitas.pasajes.buses.converter.VehiculoConverter;
import com.civitas.pasajes.buses.dto.TipoLicenciaDto;
import com.civitas.pasajes.buses.dto.TipoVehiculoDto;
import com.civitas.pasajes.buses.dto.VehiculoDto;
import com.civitas.pasajes.buses.entity.Vehiculo;
import com.civitas.pasajes.buses.exception.NoDataFoundException;
import com.civitas.pasajes.buses.service.TipoVehiculoService;
import com.civitas.pasajes.buses.service.VehiculoService;
import com.civitas.pasajes.buses.util.WrapperResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private VehiculoConverter converter;

    @Autowired
    private TipoVehiculoService Service;

    @Autowired
    private TipoVehiculoConverter converterType;
    
    @GetMapping
    public ResponseEntity<List<VehiculoDto>> findAll() {
        List<VehiculoDto> registros = converter.fromEntity(vehiculoService.findAll());
        return new WrapperResponse(true, "success", registros).createResponse(HttpStatus.OK);
    }
    @GetMapping("/tipovehiculo")
	public ResponseEntity<List<TipoVehiculoDto>> findAllType() {
		List<TipoVehiculoDto> registros = converterType.fromEntity(Service.findAll());
		return new WrapperResponse(true, "success", registros).createResponse(HttpStatus.OK);
	}
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") int id) {
        try {
            Vehiculo vehiculo = vehiculoService.findById(id);

            if (vehiculo == null) {
                throw new NoDataFoundException("No se encontró un vehículo con el ID proporcionado");
            }

            return new WrapperResponse(true, "success", vehiculo).createResponse(HttpStatus.OK);

        } catch (NoDataFoundException e) {
            return new WrapperResponse(false, e.getMessage(), null).createResponse(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new WrapperResponse(false, "Ocurrió un error interno", null).createResponse(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<VehiculoDto> create(@RequestBody VehiculoDto vehiculoDto) {
        Vehiculo vehiculoEntity = converter.fromDTO(vehiculoDto);
        VehiculoDto registro = converter.fromEntity(vehiculoService.save(vehiculoEntity));
        return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiculoDto> update(@PathVariable("id") int id, @RequestBody VehiculoDto vehiculoDto) {
        Vehiculo vehiculoEntity = converter.fromDTO(vehiculoDto);
        vehiculoEntity.setId(id);  
        Vehiculo updatedVehiculo = vehiculoService.save(vehiculoEntity);
        VehiculoDto updatedDto = converter.fromEntity(updatedVehiculo);
        return new WrapperResponse(true, "success", updatedDto).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        try {
            vehiculoService.delete(id);
            return new WrapperResponse(true, "Vehículo eliminado correctamente", null).createResponse(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            return new WrapperResponse(false, "No se puede eliminar el vehículo porque está siendo utilizado", null)
                    .createResponse(HttpStatus.CONFLICT);
        } catch (NoDataFoundException e) {
            return new WrapperResponse(false, e.getMessage(), null).createResponse(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new WrapperResponse(false, "Ocurrió un error interno", null).createResponse(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
