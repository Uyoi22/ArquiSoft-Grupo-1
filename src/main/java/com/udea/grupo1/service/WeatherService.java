package com.udea.grupo1.service;

import com.udea.grupo1.entity.Weather;
import com.udea.grupo1.repository.WeatherRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;

    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public Weather saveWeather(Weather weather) {
        weather.setRegistrationDate(LocalDateTime.now());
        return weatherRepository.save(weather);
    }

    public List<Weather> findByCountryAndCity(String country, String city) {
        return weatherRepository.findByCountryAndCity(country, city);
    }
}
