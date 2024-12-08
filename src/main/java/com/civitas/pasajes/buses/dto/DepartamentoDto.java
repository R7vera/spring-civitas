package com.civitas.pasajes.buses.dto;

import com.civitas.pasajes.buses.entity.Pais;

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
public class DepartamentoDto {
    private int id;
    private String nombre;
    private String urlImg;
    private Pais pais;
}
