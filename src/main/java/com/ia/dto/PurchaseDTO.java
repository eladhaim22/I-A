package com.ia.dto;

import com.ia.entity.Reclamo;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PurchaseDTO {
    private  Integer id;
    private Date purchaseDate;
    private Long userId;
    private ProductDTO product;
    private String productName;
    private float price;
    private int quantity;
    private List<ReclamoDTO> reclamos = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<ReclamoDTO> getReclamos() {
        return reclamos;
    }

    public void setReclamos(List<ReclamoDTO> reclamos) {
        this.reclamos = reclamos;
    }
}
