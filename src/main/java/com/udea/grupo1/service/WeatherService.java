package com.udea.grupo1.service;

import com.udea.grupo1.dto.WeatherResponseDTO;
import com.udea.grupo1.entity.City;
import com.udea.grupo1.entity.Weather;
import com.udea.grupo1.repository.CityRepository;
import com.udea.grupo1.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeatherService {
    
    private final CityRepository cityRepository;
    private final WeatherRepository weatherRepository;
    
    @Transactional(readOnly = true)
    public Optional<WeatherResponseDTO> getWeatherByCityAndCountry(String cityName, String countryName) {
        Optional<City> cityOpt = cityRepository.findByNombreAndPais(cityName, countryName);
        
        if (cityOpt.isEmpty()) {
            return Optional.empty();
        }
        
        City city = cityOpt.get();
        Optional<Weather> weatherOpt = weatherRepository.findFirstByCityOrderByFechaRegistroDesc(city);
        
        if (weatherOpt.isEmpty()) {
            return Optional.empty();
        }
        
        return Optional.of(WeatherResponseDTO.fromEntity(weatherOpt.get()));
    }
}

