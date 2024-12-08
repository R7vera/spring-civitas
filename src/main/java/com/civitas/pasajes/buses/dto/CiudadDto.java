package com.civitas.pasajes.buses.dto;


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
public class CiudadDto {
    private int id;
    private String nombre;
    private String url;
    private String pais;
    private String departamento;
}
