package com.codegym.demo1.controller;


import com.codegym.demo1.model.City;
import com.codegym.demo1.model.CityForm;
import com.codegym.demo1.service.city.ICityService;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/cities")

public class CityController {
    @Autowired
    private ICityService cityService;

    @Value("${file-upload}")
    private String uploadPath;

    @GetMapping
    public ResponseEntity<List<City>> findAll(){
        List<City> cities = this.cityService.findAll();
        if (cities.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(cities, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<City> findById(@PathVariable Long id){
        Optional<City> city = this.cityService.findById(id);
        if (!city.isPresent()){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(city.get(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<City> save(@ModelAttribute CityForm cityForm) {
        MultipartFile image = cityForm.getImage();
        if (image.getSize() != 0) {
            String fileName = cityForm.getImage().getOriginalFilename();
            try {
                FileCopyUtils.copy(cityForm.getImage().getBytes(), new File(uploadPath + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            City city = new City(cityForm.getId(), cityForm.getName(), cityForm.getProvince(), cityForm.getArea(), cityForm.getPopular(), fileName, cityForm.getDescription());
            return new ResponseEntity<>(cityService.save(city), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable Long id, @RequestBody City newCity) {
        Optional<City> productOptional = cityService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        newCity.setId(id);
        return new ResponseEntity<>(cityService.save(newCity), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<City> deleteCity(@PathVariable Long id){
        Optional<City> city  = this.cityService.findById(id);
        if (!city.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.cityService.deleteById(id);
        return new ResponseEntity<>(city.get(),HttpStatus.OK);
    }
}
