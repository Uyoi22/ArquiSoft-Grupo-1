package com.udea.grupo1.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "climas")
public class Clima {

    @Id
    private String id;

    private String pais;
    private String ciudad;
    private double temperatura;
    private double sensacionTermica;
    private int humedad;
    private String descripcion;
    private double velocidadViento;
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