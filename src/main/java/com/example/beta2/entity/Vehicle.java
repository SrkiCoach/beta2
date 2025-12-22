package com.example.beta2.entity;

import javax.persistence.*;

@Entity
@Table(name = "VEHICLE")
@SequenceGenerator(
        name = "vehicleSeq",
        sequenceName = "VEHICLE_SEQ",
        allocationSize = 1
)
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicleSeq")
    @Column(name = "ID")
    private Long id;

    @Column(name = "BRAND", nullable = false)
    private String brand;

    @Column(name = "YEAR", nullable = false)
    private Integer year;

    @Column(name = "TYPE", nullable = false)
    private String type;

    // getters & setters (MANDATORY)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
