package com.example.beta2.dto;

// Importing validation annotations from javax.validation
// These annotations are used to automatically validate incoming data when using frameworks like JAX-RS or Spring
import javax.validation.constraints.NotBlank; // Ensures a String is not null and not empty
import javax.validation.constraints.NotNull;  // Ensures a value is not null
import javax.validation.constraints.Min;      // Ensures a numeric value is at least a minimum value
import javax.validation.constraints.Size;     // Ensures a String has a size within specified limits

// This is a Data Transfer Object (DTO) for Vehicle entities
// DTOs are used to transfer data between layers (e.g., from REST API to service layer) without exposing the entity directly
public class VehicleDto {

    // Unique identifier for the vehicle
    private Long id;

    // Vehicle brand (e.g., Toyota, Ford)
    // @NotBlank ensures the brand is not null and not just whitespace
    // @Size ensures brand does not exceed 50 characters
    @NotBlank(message = "Brand is required")
    @Size(max = 50, message = "Brand must be at most 50 characters")
    private String brand;

    // Year of manufacture of the vehicle
    // @NotNull ensures the year is provided
    // @Min ensures the year is valid (>= 1886, when the first car was invented)
    @NotNull(message = "Year is required")
    @Min(value = 1886, message = "Year must be valid")
    private Integer year;

    // Vehicle type (e.g., SUV, Sedan, Truck)
    // @NotBlank ensures type is not null or empty
    // @Size ensures type does not exceed 20 characters
    @NotBlank(message = "Type is required")
    @Size(max = 20, message = "Type must be at most 20 characters")
    private String type;

    // Default no-argument constructor
    // Needed by frameworks like JAX-RS or Jackson for serialization/deserialization
    public VehicleDto() {}

    // Parameterized constructor to easily create DTO instances with all properties
    public VehicleDto(Long id, String brand, Integer year, String type) {
        this.id = id;
        this.brand = brand;
        this.year = year;
        this.type = type;
    }

    // ===== GETTERS =====
    // Used to retrieve the values of the private fields

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

    // ===== SETTERS =====
    // Used to modify the values of the private fields
    // These are necessary for frameworks that deserialize JSON into DTOs

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
