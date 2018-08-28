package com.ia.dto;

import javax.persistence.Column;

public class ProductDTO {
    private Integer id;
    private String name;
    private String sku;
    private int repositionPoint;
    private int cantidad;

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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
