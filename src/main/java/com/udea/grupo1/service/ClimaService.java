package com.udea.grupo1.service;

import com.udea.grupo1.entity.Clima;
import com.udea.grupo1.repository.ClimaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClimaService {

    private final ClimaRepository climaRepository;

    public ClimaService(ClimaRepository climaRepository) {
        this.climaRepository = climaRepository;
    }

    // Lógica para GUARDAR (POST)
    public Clima guardarClima(Clima clima) {
        // Asignamos la hora actual del servidor antes de guardar
        clima.setFechaRegistro(LocalDateTime.now());
        return climaRepository.save(clima);
    }

    // Lógica para BUSCAR (GET) - Este era el que faltaba
    public List<Clima> buscarPorPaisYCiudad(String pais, String ciudad) {
        // Llama al repositorio modificado que devuelve una lista
        return climaRepository.findByPaisAndCiudad(pais, ciudad);
    }
}