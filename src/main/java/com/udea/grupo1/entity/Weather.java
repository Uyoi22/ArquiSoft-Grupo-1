package com.udea.grupo1.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Document(collection = "climas")
public class Weather {

    @Id
    @Schema(description = "Identificador único del registro climático", example = "609e125f5f1b2c0015b8e9b1", accessMode = Schema.AccessMode.READ_ONLY)
    private String id;
    @Schema(description = "País donde se registran los datos climáticos", example = "Colombia")
    @JsonProperty("pais")
    @JsonAlias({"pais", "country"})
    private String country;
    @Schema(description = "Ciudad donde se registran los datos climáticos", example = "Medellín")
    @JsonProperty("ciudad")
    @JsonAlias({"ciudad", "city"})
    private String city;
    @Schema(description = "Temperatura actual en grados Celsius", example = "22.5")
    @JsonProperty("temperatura")
    @JsonAlias({"temperatura", "temperature"})
    private double temperature;
    @Schema(description = "Sensación térmica en grados Celsius", example = "24.0")
    @JsonProperty("sensacionTermica")
    @JsonAlias({"sensacionTermica", "feelsLike"})
    private double feelsLike;
    @Schema(description = "Humedad relativa en porcentaje", example = "78")
    @JsonProperty("humedad")
    @JsonAlias({"humedad", "humidity"})
    private int humidity;
    @Schema(description = "Descripción del estado del clima", example = "Parcialmente nublado")
    @JsonProperty("descripcion")
    @JsonAlias({"descripcion", "description"})
    private String description;
    @Schema(description = "Velocidad del viento en km/h", example = "15.0")
    @JsonProperty("velocidadViento")
    @JsonAlias({"velocidadViento", "windSpeed"})
    private double windSpeed;
    @Schema(description = "Fecha y hora de registro del dato climático", example = "2024-06-15T14:30:00", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime registrationDate;

    public Weather() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
