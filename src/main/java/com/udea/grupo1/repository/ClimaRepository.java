package com.udea.grupo1.repository;

import com.udea.grupo1.entity.Clima;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClimaRepository extends MongoRepository<Clima, String> {

    List<Clima> findByPaisAndCiudad(String pais, String ciudad);

}