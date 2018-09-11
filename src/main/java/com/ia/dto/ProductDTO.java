package com.ia.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;

public class ProductDTO {
    private Integer id;
    private String name;
    private String sku;
    private int repositionPoint;
    private int quantity;
    private boolean active;
    private String imageUrl;;
    private MultipartFile file;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getRepositionPoint() {
        return repositionPoint;
    }

    public void setRepositionPoint(int repositionPoint) {
        this.repositionPoint = repositionPoint;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
