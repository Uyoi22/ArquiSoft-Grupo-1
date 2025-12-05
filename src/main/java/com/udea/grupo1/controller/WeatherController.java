package com.udea.grupo1.controller;

import com.udea.grupo1.entity.Weather;
import com.udea.grupo1.service.WeatherService;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v1/clima")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "Weather", description = "Gestión de datos climáticos por país y ciudad")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Operation(summary = "Registrar datos climáticos", description = "Registra los datos climáticos de una ciudad específica.", responses = {
            @ApiResponse(responseCode = "201", description = "Datos climáticos registrados exitosamente y devuelve el recurso creado con enlaces HATEOAS.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EntityModel.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content)
    })

    @PostMapping
    public ResponseEntity<?> createWeather(
            @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos climáticos a registrar", required = true, content = @Content(schema = @Schema(implementation = Weather.class))) Weather weather) {

        if (weather.getCountry() == null || weather.getCity() == null) {
            return ResponseEntity.badRequest().body("{\"error\": \"País y ciudad son obligatorios\"}");
        }

        Weather saved = weatherService.saveWeather(weather);

        EntityModel<Weather> resource = EntityModel.of(saved);

        resource.add(linkTo(methodOn(WeatherController.class)
                .searchWeather(saved.getCountry(), saved.getCity())).withSelfRel());

        resource.add(linkTo(methodOn(WeatherController.class)
                .createWeather(null)).withRel("crear-nuevo"));

        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }

    @Operation(summary = "Buscar datos climáticos por país y ciudad", description = "Devuelve una lista de todos los registros de clima que coinciden con el país y ciudad especificados, con enlaces HATEOAS.", responses = {
            @ApiResponse(responseCode = "200", description = "Datos climáticos encontrados exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CollectionModel.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron datos climáticos para los parámetros proporcionados", content = @Content)
    })

    @GetMapping("/buscar")
    public ResponseEntity<List<Weather>> searchWeather(
            @RequestParam("pais") String pais,
            @RequestParam("ciudad") String ciudad) {

        List<Weather> weatherList = weatherService.findByCountryAndCity(pais, ciudad);
        return ResponseEntity.ok(weatherList);
    }
}
