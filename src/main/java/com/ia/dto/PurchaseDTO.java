package com.ia.dto;

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
    private List<ClaimDTO> claims = new ArrayList<>();

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

    public List<ClaimDTO> getClaims() {
        return claims;
    }

    public void setClaims(List<ClaimDTO> claims) {
        this.claims = claims;
    }
}
