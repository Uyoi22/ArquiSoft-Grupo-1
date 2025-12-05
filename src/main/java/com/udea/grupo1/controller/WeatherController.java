package com.udea.grupo1.controller;

import com.udea.grupo1.entity.Weather;
import com.udea.grupo1.service.WeatherService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v1/clima")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @PostMapping
    public ResponseEntity<?> createWeather(@RequestBody Weather weather) {

        if (weather.getCountry() == null || weather.getCity() == null) {
            return ResponseEntity.badRequest().body("País y ciudad son obligatorios");
        }

        Weather saved = weatherService.saveWeather(weather);

        EntityModel<Weather> resource = EntityModel.of(saved);
        
        resource.add(linkTo(methodOn(WeatherController.class)
                .searchWeather(saved.getCountry(), saved.getCity())).withSelfRel());
        
        resource.add(linkTo(methodOn(WeatherController.class)
                .createWeather(null)).withRel("crear-nuevo"));

        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Weather>> searchWeather(
            @RequestParam String country, 
            @RequestParam String city) {

        List<Weather> weatherList = weatherService.findByCountryAndCity(country, city);
        return ResponseEntity.ok(weatherList);
    }
}

