package com.ia.dto;

public class ProductDTO {
    private Integer id;
    private String name;
    private String sku;
    private int repositionPoint;
    private int quantity;

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
}
