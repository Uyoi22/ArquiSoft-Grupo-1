package com.udea.grupo1.repository;

import com.udea.grupo1.entity.Weather;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepository extends MongoRepository<Weather, String> {

    List<Weather> findByCountryAndCity(String country, String city);

}
