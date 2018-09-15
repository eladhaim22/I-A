package com.ia.entity;

import com.ia.validators.UniqueDNI;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Table(name = "persons")
@Entity
@UniqueDNI(message ="*El dni ya esta registrado.")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="address")
    @NotEmpty(message = "*Please provide your address")
    private String address;

    @Column(name="dni")
    @NotEmpty(message = "*Porfavor ingrese un dni.")
    private String dni;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
