package com.udea.grupo1.repository;

import com.udea.grupo1.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByNombreAndPais(String nombre, String pais);
    boolean existsByNombreAndPais(String nombre, String pais);
}

