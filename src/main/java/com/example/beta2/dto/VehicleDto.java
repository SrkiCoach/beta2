package com.example.beta2.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class VehicleDto {

    private Long id;

    @NotBlank(message = "Brand is required")
    @Size(max = 50, message = "Brand must be at most 50 characters")
    private String brand;

    @NotNull(message = "Year is required")
    @Min(value = 1886, message = "Year must be valid") // first car invented ~1886
    private Integer year;

    @NotBlank(message = "Type is required")
    @Size(max = 20, message = "Type must be at most 20 characters")
    private String type;

    public VehicleDto() {}

    public VehicleDto(Long id, String brand, Integer year, String type) {
        this.id = id;
        this.brand = brand;
        this.year = year;
        this.type = type;
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public Integer getYear() {
        return year;
    }

    public String getType() {
        return type;
    }

    //  SETTERS (THIS IS WHAT WAS MISSING)
    public void setId(Long id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setType(String type) {
        this.type = type;
    }
}
