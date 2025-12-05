package com.udea.grupo1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import com.udea.grupo1.entity.Clima;
import com.udea.grupo1.service.ClimaService;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v1/clima")
@Tag(name = "Clima", description = "Gestión de datos climáticos por país y ciudad")
public class ClimaController {

    private final ClimaService climaService;

    public ClimaController(ClimaService climaService) {
        this.climaService = climaService;
    }

    @Operation(summary = "Registrar datos climáticos", description = "Registra los datos climáticos de una ciudad específica.", responses = {
            @ApiResponse(responseCode = "201", description = "Datos climáticos registrados exitosamente y devuelve el recurso creado con enlaces HATEOAS.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EntityModel.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content)
    })

    @PostMapping
    public ResponseEntity<?> registrarClima(

            @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos climáticos a registrar", required = true, content = @Content(schema = @Schema(implementation = Clima.class))) Clima clima) {

        if (clima.getPais() == null || clima.getCiudad() == null) {
            return ResponseEntity.badRequest().body("Pais y ciudad son obligatorios");
        }

        Clima guardado = climaService.guardarClima(clima);

        EntityModel<Clima> resource = EntityModel.of(guardado);

        resource.add(linkTo(methodOn(ClimaController.class)
                .buscarClima(guardado.getPais(), guardado.getCiudad())).withSelfRel());

        resource.add(linkTo(methodOn(ClimaController.class)
                .registrarClima(new Clima())).withRel("crear-nuevo"));

        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }

    @Operation(summary = "Buscar datos climáticos por país y ciudad", description = "Devuelve una lista de todos los registros de clima que coinciden con el país y ciudad especificados, con enlaces HATEOAS.", responses = {
            @ApiResponse(responseCode = "200", description = "Datos climáticos encontrados exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CollectionModel.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron datos climáticos para los parámetros proporcionados", content = @Content)
    })

    @GetMapping("/buscar")
    public ResponseEntity<CollectionModel<EntityModel<Clima>>> buscarClima(
            @RequestParam @Parameter(description = "Nombre del país a buscar", example = "Colombia", required = true) String pais,
            @RequestParam @Parameter(description = "Nombre de la ciudad a buscar", example = "Bogotá", required = true) String ciudad) {

        List<Clima> listaClima = climaService.buscarPorPaisYCiudad(pais, ciudad);

        // Transformamos la lista de objetos a lista de recursos HATEOAS
        List<EntityModel<Clima>> resources = listaClima.stream()
                .map(clima -> {
                    EntityModel<Clima> resource = EntityModel.of(clima);
                    // Link a esta búsqueda específica
                    resource.add(linkTo(methodOn(ClimaController.class)
                            .buscarClima(pais, ciudad)).withSelfRel());
                    return resource;
                })
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Clima>> collection = CollectionModel.of(resources);
        collection.add(linkTo(methodOn(ClimaController.class)
                .buscarClima(pais, ciudad)).withSelfRel());

        return ResponseEntity.ok(collection);
    }
}