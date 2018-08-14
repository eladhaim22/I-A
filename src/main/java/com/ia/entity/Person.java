package com.ia.entity;

import javax.persistence.*;

@Table(name = "persons")
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name="address")
    private String address;
}
