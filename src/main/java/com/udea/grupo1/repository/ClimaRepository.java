package com.udea.grupo1.repository;



import com.udea.grupo1.model.Clima;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClimaRepository extends MongoRepository<Clima, String> {

    Optional<Clima> findByPaisAndCiudad(String pais, String ciudad);

}
