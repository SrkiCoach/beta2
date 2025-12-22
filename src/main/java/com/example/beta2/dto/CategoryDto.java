package com.example.beta2.dto;

/**
 * Data Transfer Object (DTO) for Category entities.
 *
 * DTOs are used to transfer data between layers (e.g., REST endpoints and services)
 * without exposing the entity directly. This improves security and decouples layers.
 */
public class CategoryDto {

    // Unique identifier for the category
    private Long id;

    // User-defined code for the category (like a short reference or code)
    private String usrCode;

    // Description of the category
    private String description;

    // Active flag, typically 1 = active, 0 = inactive
    private Integer active;

    /**
     * Default no-argument constructor
     * Needed by frameworks (like Jackson for JSON deserialization)
     */
    public CategoryDto() {
    }

    /**
     * Parameterized constructor to create a fully-initialized DTO
     */
    public CategoryDto(Long id, String usrCode, String description, Integer active) {
        this.id = id;
        this.usrCode = usrCode;
        this.description = description;
        this.active = active;
    }

    // ===== GETTERS =====
    // Used to retrieve private field values. Required by frameworks for serialization to JSON.

    public Long getId() {
        return id;
    }

    public String getUsrCode() {
        return usrCode;
    }

    public String getDescription() {
        return description;
    }

    public Integer getActive() {
        return active;
    }

    // Note: There are no setters here.
    // If you plan to receive JSON input from REST endpoints, you may need setters
    // or a constructor with all parameters for frameworks like Jackson to deserialize properly.
}
