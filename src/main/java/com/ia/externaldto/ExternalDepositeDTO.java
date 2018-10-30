package com.ia.externaldto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.criteria.CriteriaBuilder;

public class ExternalDepositeDTO {
    @JsonProperty(value="Cliente")
    private ExternalClientDTO externalClientDTO;
    @JsonProperty(value="Producto")
    private ExternalProductDTO externalProductDTO;

    @JsonProperty(value="nro_orden")
    private Integer purchaseId;

    public ExternalDepositeDTO(ExternalClientDTO externalClientDTO, ExternalProductDTO externalProductDTO,Integer purchaseId) {
        this.externalClientDTO = externalClientDTO;
        this.externalProductDTO = externalProductDTO;
        this.purchaseId = purchaseId;
    }

    public ExternalClientDTO getExternalClientDTO() {
        return externalClientDTO;
    }

    public void setExternalClientDTO(ExternalClientDTO externalClientDTO) {
        this.externalClientDTO = externalClientDTO;
    }

    public ExternalProductDTO getExternalProductDTO() {
        return externalProductDTO;
    }

    public void setExternalProductDTO(ExternalProductDTO externalProductDTO) {
        this.externalProductDTO = externalProductDTO;
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }
}
