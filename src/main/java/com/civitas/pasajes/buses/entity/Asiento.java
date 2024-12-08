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
@Table(name = "asiento")
@EntityListeners(AuditingEntityListener.class)
public class Asiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int numero;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo", nullable = false)  // FK a la tabla vehiculo (Bus)
    private Vehiculo vehiculo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_asiento", nullable = false, length = 20)
    private TipoAsiento tipoAsiento;  // Enum 'Ventana', 'Pasillo', 'Delantero'

    @Column(name = "preciobase", nullable = false)
    private float precioBase;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoAsiento estado;  // Enum 'Disponible', 'Ocupado', 'Reservado'

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    // Enumeraciones para TipoAsiento y EstadoAsiento
    public enum TipoAsiento {
        VENTANA, PASILLO, DELANTERO
    }

    public enum EstadoAsiento {
        DISPONIBLE, OCUPADO, RESERVADO
    }
}
