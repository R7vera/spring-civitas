package com.civitas.pasajes.buses.dto;

import com.civitas.pasajes.buses.entity.Persona;
import com.civitas.pasajes.buses.entity.TipoLicencia;
import com.civitas.pasajes.buses.entity.Vehiculo;
import com.civitas.pasajes.buses.entity.Asiento.EstadoAsiento;
import com.civitas.pasajes.buses.entity.Asiento.TipoAsiento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AsientoDto {

    private int id;

 
    private int numero;

  
    private Vehiculo vehiculo;

 
    private TipoAsiento tipoAsiento;  // Enum 'Ventana', 'Pasillo', 'Delantero'


    private float precioBase;

    
    private EstadoAsiento estado;  // Enum 'Disponible', 'Ocupado', 'Reservado'

}
