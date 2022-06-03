package com.akonst.kotikijava.dao.models;

import javax.persistence.*;

import java.sql.Timestamp;

@Entity
@Table (name = "cats", schema = "public")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="owner_id")
    private Owner owner;

    private String name;
    private String breed;
    private String color;

    @Column(name = "date_of_birth")
    private Timestamp dateOfBirth;

    public Cat() {
    }

    public Cat(String name, String breed, String color, Owner owner, Timestamp dateOfBirth) {
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.owner = owner;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public Owner getOwner () {
        return owner;
    }

    public void setOwner (Owner owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Timestamp getDateOfBirth() {
        return this.dateOfBirth;
    }
}
