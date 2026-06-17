package com.northwind.northwind_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId")
    private Long id;


    @Column(name = "categoryName")
    private String categoryName;

    @Column(name = "description")
    private String description;

    @Lob // Tells JPA this is a Large Object (BLOB/CLOB)
    @Column(name = "Picture", columnDefinition = "LONGBLOB") // Forces the specific MySQL type
    private byte[] picture;


    public Long getCategoryId() {
        return id;
    }

    public void setCategoryId(Long categoryId) {
        this.id = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
