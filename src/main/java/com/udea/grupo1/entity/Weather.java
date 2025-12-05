package com.udea.grupo1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "climas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ciudad_id", nullable = false)
    private City city;
    
    @Column(nullable = false)
    private Double temperatura;
    
    @Column(nullable = false)
    private String unidadTemperatura;
    
    @Column(nullable = false)
    private Integer humedad;
    
    @Column(nullable = false)
    private Double presion;
    
    @Column(nullable = false)
    private String descripcion;
    
    @Column(nullable = false)
    private Double velocidadViento;
    
    @Column(nullable = false)
    private LocalDateTime fechaRegistro;
    
    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDateTime.now();
    }
}

