package com.udea.grupo1.repository;

import com.udea.grupo1.entity.Weather;
import com.udea.grupo1.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {
    List<Weather> findByCity(City city);
    Optional<Weather> findFirstByCityOrderByFechaRegistroDesc(City city);
}

