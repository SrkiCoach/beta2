package com.example.beta2.entity;

import javax.persistence.*;

/**
 * Vehicle is a JPA entity that maps to the VEHICLE table in the database.
 *
 * Each instance of this class represents a row in the VEHICLE table.
 * JPA annotations define how fields are mapped to columns and how IDs are generated.
 */
@Entity
@Table(name = "VEHICLE") // Maps this entity to the VEHICLE table
@SequenceGenerator(
        name = "vehicleSeq",      // Name used in @GeneratedValue
        sequenceName = "VEHICLE_SEQ", // Actual database sequence name
        allocationSize = 1        // How many IDs are preallocated in memory (1 = no preallocation)
)
public class Vehicle {

    /**
     * Primary key of the VEHICLE table.
     * Auto-generated using a database sequence VEHICLE_SEQ.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicleSeq")
    @Column(name = "ID")
    private Long id;

    /**
     * Vehicle brand column. Cannot be null.
     */
    @Column(name = "BRAND", nullable = false)
    private String brand;

    /**
     * Year of the vehicle. Cannot be null.
     */
    @Column(name = "YEAR", nullable = false)
    private Integer year;

    /**
     * Type of vehicle (e.g., SUV, Sedan). Cannot be null.
     */
    @Column(name = "TYPE", nullable = false)
    private String type;

    // ===== GETTERS AND SETTERS =====
    // Mandatory for JPA to read and write entity fields.
    // These methods allow JPA to access private fields via reflection.

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
