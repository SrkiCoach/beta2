package com.example.beta2.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * JPA Entity representing the HC_KEN_CATEG table in the database.
 * Each instance of this class corresponds to a row in that table.
 *
 * This class includes fields for soft delete, auditing, and versioning.
 */
@Entity
@Table(name = "HC_KEN_CATEG") // Maps this entity to the HC_KEN_CATEG table
public class Category {

    /**
     * Primary key of the table
     */
    @Id
    @Column(name = "ID")
    private Long id;

    /**
     * User-defined code for the category
     * - Cannot be null
     * - Must be unique
     * - Max length 10 characters
     */
    @Column(name = "USRCODE", nullable = false, unique = true, length = 10)
    private String usrCode;

    /**
     * Description of the category
     * - Cannot be null
     * - Max length 150 characters
     */
    @Column(name = "DESCRIPTION", nullable = false, length = 150)
    private String description;

    /**
     * Flag indicating whether the category is active (1 = active, 0 = inactive)
     */
    @Column(name = "ACTIVE")
    private Integer active;

    /**
     * Version field, often used for optimistic locking
     */
    @Column(name = "VERSION")
    private Integer version;

    /**
     * Auditing fields
     */
    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "DELETED_BY")
    private String deletedBy;

    /**
     * Dates for auditing
     * @Temporal indicates how Java Date is mapped to SQL types
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "CREATED_AT")
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    @Temporal(TemporalType.DATE)
    @Column(name = "DELETED_AT")
    private Date deletedAt;

    /**
     * Soft delete indicator (e.g., 'Y' = deleted, null = active)
     */
    @Column(name = "IS_DELETED")
    private String isDeleted;

    /**
     * Some additional identifier/version ID (purpose depends on business logic)
     */
    @Column(name = "VERID")
    private String verId;

    // ===== GETTERS AND SETTERS =====
    // Only a subset of fields is exposed here
    // Additional getters/setters can be added as needed for auditing or versioning

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsrCode() {
        return usrCode;
    }

    public void setUsrCode(String usrCode) {
        this.usrCode = usrCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    // Note: Other fields like createdAt, updatedAt, deletedAt, version, etc.
    // can have getters/setters added if you plan to read/write them in code.
}
