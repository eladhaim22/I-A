package com.ia.externaldto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalDepositeDTO {
    @JsonProperty(value="Cliente")
    private ExternalClientDTO externalClientDTO;
    @JsonProperty(value="Producto")
    private ExternalProductDTO externalProductDTO;

    public ExternalDepositeDTO(ExternalClientDTO externalClientDTO, ExternalProductDTO externalProductDTO) {
        this.externalClientDTO = externalClientDTO;
        this.externalProductDTO = externalProductDTO;
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
}
