package com.codegym.demo1.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityForm {
    private Long id;

    private String name;

    private double area;

    private int popular;

    private MultipartFile image;

    private String description;

    private Province province;

    public CityForm(String name, double area, int popular, MultipartFile image, String description, Province province) {
        this.name = name;
        this.area = area;
        this.popular = popular;
        this.image = image;
        this.description = description;
        this.province = province;
    }
}
