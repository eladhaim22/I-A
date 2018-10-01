package com.ia.externaldto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalProductDTO {
    @JsonProperty(value="codTienda")
    private String storeId;
    @JsonProperty(value="codBarra")
    private String sku;
    @JsonProperty(value="cantidad")
    private int quantity;

    public ExternalProductDTO(String storeId, String sku, int quantity) {
        this.storeId = storeId;
        this.sku = sku;
        this.quantity = quantity;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
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
