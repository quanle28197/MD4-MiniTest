package com.codegym.demo1.controller;

import com.codegym.demo1.model.Province;
import com.codegym.demo1.service.country.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/provinces")
public class ProvinceController {
    @Autowired
    private IProvinceService provinceService;

    @GetMapping
    public ResponseEntity<List<Province>> findAll() {
        List<Province> provinces = this.provinceService.findAll();
        if (provinces.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(provinces, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Province> findById(@PathVariable Long id) {
        Optional<Province> province = this.provinceService.findById(id);
        if (!province.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(province.get(), HttpStatus.OK);
    }

}
