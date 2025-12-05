package com.udea.grupo1.service;

import com.udea.grupo1.model.Clima;
import com.udea.grupo1.repository.ClimaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClimaService {

    private final ClimaRepository climaRepository;

    public ClimaService(ClimaRepository climaRepository) {
        this.climaRepository = climaRepository;
    }

    public Clima guardarClima(Clima clima) {

        clima.setFechaRegistro(LocalDateTime.now());

        return climaRepository.save(clima);
    }

}
