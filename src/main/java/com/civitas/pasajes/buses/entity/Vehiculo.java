package com.civitas.pasajes.buses.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

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
@Entity
@Table(name = "vehiculo")
@EntityListeners(AuditingEntityListener.class)
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "tipovehiculo", nullable = false)  // FK a la tabla tipo_vehiculo
    private TipoVehiculo tipoVehiculo;

    @Column(nullable = false, length = 20)
    private String placa;

    @Column(name = "numeropisos", nullable = false, columnDefinition = "INT DEFAULT 1")
    private int numeroPisos;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_Vehiculo", nullable = false, length = 50)
    private EstadoVehiculo estado;  // Enum 'Activo', 'Inactivo', 'En Ruta', 'Venta'

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
    
    public enum EstadoVehiculo {
    	Activo, Inactivo, EnRuta,Venta 
    }
}
