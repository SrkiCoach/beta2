package com.example.beta2.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "HC_KEN_CATEG")
public class Category {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "USRCODE", nullable = false, unique = true, length = 10)
    private String usrCode;

    @Column(name = "DESCRIPTION", nullable = false, length = 150)
    private String description;

    @Column(name = "ACTIVE")
    private Integer active;

    @Column(name = "VERSION")
    private Integer version;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "DELETED_BY")
    private String deletedBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATED_AT")
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    @Temporal(TemporalType.DATE)
    @Column(name = "DELETED_AT")
    private Date deletedAt;

    @Column(name = "IS_DELETED")
    private String isDeleted;

    @Column(name = "VERID")
    private String verId;

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

}
