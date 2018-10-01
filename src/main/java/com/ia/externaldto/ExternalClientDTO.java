package com.ia.externaldto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalClientDTO {
    @JsonProperty(value="nombre")
    private String name;
    @JsonProperty(value="apellido")
    private String lastName;
    @JsonProperty(value="mail")
    private String mail;
    @JsonProperty(value="direccion")
    private String address;

    public ExternalClientDTO(String name, String lastName, String mail, String address) {
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
        this.address = address;
    }
}
