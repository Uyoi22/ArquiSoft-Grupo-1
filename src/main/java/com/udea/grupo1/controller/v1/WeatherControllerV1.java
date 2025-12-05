package com.udea.grupo1.controller.v1;

import com.udea.grupo1.dto.WeatherResponseDTO;
import com.udea.grupo1.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clima")
@RequiredArgsConstructor
public class WeatherControllerV1 {
    
    private final WeatherService weatherService;
    
    @GetMapping
    public ResponseEntity<EntityModel<WeatherResponseDTO>> getWeather(
            @RequestParam String ciudad,
            @RequestParam String pais) {
        
        return weatherService.getWeatherByCityAndCountry(ciudad, pais)
                .map(weatherDTO -> {
                    EntityModel<WeatherResponseDTO> resource = EntityModel.of(weatherDTO);
                    
                    Link selfLink = WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(WeatherControllerV1.class)
                                    .getWeather(ciudad, pais)
                    ).withSelfRel();
                    resource.add(selfLink);
                    
                    return ResponseEntity.ok(resource);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}

