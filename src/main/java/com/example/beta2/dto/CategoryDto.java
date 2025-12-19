package com.example.beta2.dto;

public class CategoryDto {

    private Long id;
    private String usrCode;
    private String description;
    private Integer active;

    public CategoryDto() {
    }

    public CategoryDto(Long id, String usrCode, String description, Integer active) {
        this.id = id;
        this.usrCode = usrCode;
        this.description = description;
        this.active = active;
    }

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
}
