package com.ia.externaldto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalClientDTO {
    @JsonProperty(value="nombre")
    private String name;
    @JsonProperty(value="apellido")
    private String lastName;
    @JsonProperty(value="email")
    private String mail;
    @JsonProperty(value="direccion")
    private String address;

    public ExternalClientDTO(String name, String lastName, String mail, String address) {
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
