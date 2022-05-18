package com.codegym.demo1.service.city;

import com.codegym.demo1.model.City;
import com.codegym.demo1.repository.ICityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CityService implements ICityService {
    @Autowired
    private ICityRepository cityRepository;
    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public Optional<City> findById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        cityRepository.deleteById(id);
    }
}
