package com.udea.grupo1.controller;

import com.udea.grupo1.entity.Clima;
import com.udea.grupo1.service.ClimaService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v1/clima")
public class ClimaController {

    private final ClimaService climaService;

    public ClimaController(ClimaService climaService) {
        this.climaService = climaService;
    }

    @PostMapping
    public ResponseEntity<?> registrarClima(@RequestBody Clima clima) {

        if (clima.getPais() == null || clima.getCiudad() == null) {
            return ResponseEntity.badRequest().body("Pais y ciudad son obligatorios");
        }

        Clima guardado = climaService.guardarClima(clima);

        EntityModel<Clima> resource = EntityModel.of(guardado);
        
        resource.add(linkTo(methodOn(ClimaController.class)
                .buscarClima(guardado.getPais(), guardado.getCiudad())).withSelfRel());
        
        resource.add(linkTo(methodOn(ClimaController.class)
                .registrarClima(null)).withRel("crear-nuevo"));

        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }
    @GetMapping("/buscar")
    public ResponseEntity<CollectionModel<EntityModel<Clima>>> buscarClima(
            @RequestParam String pais, 
            @RequestParam String ciudad) {

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