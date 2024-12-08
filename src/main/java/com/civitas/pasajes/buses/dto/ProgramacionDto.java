package com.civitas.pasajes.buses.dto;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.civitas.pasajes.buses.entity.Destinos;

import com.civitas.pasajes.buses.entity.Vehiculo;

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
public class ProgramacionDto {

    private int id; 

   
    private LocalDateTime FechaSalida;

   
    private LocalDateTime FechaLlegada;

   
    private int idVehiculo;

    
    private int idDestino;

  
}
