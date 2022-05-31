package com.codegym.demo1.service.country;

import com.codegym.demo1.model.Province;
import com.codegym.demo1.repository.IProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceService implements IProvinceService {
    @Autowired
    private IProvinceRepository provinceRepository;

    @Override
    public List<Province> findAll() {
        return provinceRepository.findAll();
    }

    @Override
    public Province save(Province country) {
        return provinceRepository.save(country);
    }

    @Override
    public Optional<Province> findById(Long id) {
        return provinceRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        provinceRepository.deleteById(id);
    }
}
