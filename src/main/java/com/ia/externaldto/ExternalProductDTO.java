package com.ia.externaldto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalProductDTO {
    @JsonProperty(value="codBarra")
    private String sku;
    @JsonProperty(value="cantidad")
    private int quantity;

    public ExternalProductDTO(String sku, int quantity) {
        this.sku = sku;
        this.quantity = quantity;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
