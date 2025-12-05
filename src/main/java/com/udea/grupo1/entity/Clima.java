package com.udea.grupo1.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Entidad que representa los datos climáticos de una ciudad en un país específico.")
@Document(collection = "climas")
public class Clima {

    @Id
    @Schema(description = "Identificador único del registro climático", example = "609e125f5f1b2c0015b8e9b1", accessMode = Schema.AccessMode.READ_ONLY)
    private String id;
    @Schema(description = "Nombre del país", example = "Colombia")
    private String pais;
    @Schema(description = "Nombre de la ciudad", example = "Medellín")
    private String ciudad;
    @Schema(description = "Temperatura actual en grados Celsius", example = "22.5")
    private double temperatura;
    @Schema(description = "Sensación térmica en grados Celsius", example = "24.0")
    private double sensacionTermica;
    @Schema(description = "Humedad relativa en porcentaje", example = "78")
    private int humedad;
    @Schema(description = "Descripción general del clima", example = "Parcialmente nublado")
    private String descripcion;
    @Schema(description = "Velocidad del viento en km/h", example = "15.0")
    private double velocidadViento;
    @Schema(description = "Fecha y hora del registro climático", example = "2024-06-15T14:30:00", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime fechaRegistro;

    public Clima() {
    }

    public String getId() {
        return id;
    }

    public String getPais() {
        return pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public double getSensacionTermica() {
        return sensacionTermica;
    }

    public int getHumedad() {
        return humedad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getVelocidadViento() {
        return velocidadViento;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public void setSensacionTermica(double sensacionTermica) {
        this.sensacionTermica = sensacionTermica;
    }

    public void setHumedad(int humedad) {
        this.humedad = humedad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setVelocidadViento(double velocidadViento) {
        this.velocidadViento = velocidadViento;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}