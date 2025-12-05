package com.udea.grupo1.dto;

import com.udea.grupo1.entity.Weather;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WeatherResponseDTO extends RepresentationModel<WeatherResponseDTO> {
    
    private Long id;
    private String ciudad;
    private String pais;
    private Double temperatura;
    private String unidadTemperatura;
    private Integer humedad;
    private Double presion;
    private String descripcion;
    private Double velocidadViento;
    private LocalDateTime fechaRegistro;
    
    public static WeatherResponseDTO fromEntity(Weather weather) {
        WeatherResponseDTO dto = new WeatherResponseDTO();
        dto.setId(weather.getId());
        dto.setCiudad(weather.getCity().getNombre());
        dto.setPais(weather.getCity().getPais());
        dto.setTemperatura(weather.getTemperatura());
        dto.setUnidadTemperatura(weather.getUnidadTemperatura());
        dto.setHumedad(weather.getHumedad());
        dto.setPresion(weather.getPresion());
        dto.setDescripcion(weather.getDescripcion());
        dto.setVelocidadViento(weather.getVelocidadViento());
        dto.setFechaRegistro(weather.getFechaRegistro());
        return dto;
    }
}

