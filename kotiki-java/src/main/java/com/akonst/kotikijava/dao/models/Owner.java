package com.akonst.kotikijava.dao.models;

import javax.persistence.*;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "owners", schema = "public")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(name = "date_of_birth")
    private Timestamp dateOfBirth;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cat> catsList = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Owner() {
    }

    public Owner(String name, Timestamp dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public void addCat(Cat cat) {
        cat.setOwner(this);
        catsList.add(cat);
    }

    public void removeCat(Cat cat) {
        catsList.remove(cat);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Timestamp dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Cat> getCatsList() {
        return Collections.unmodifiableList(catsList);
    }

    public void setCatsList(List<Cat> catsList) {
        this.catsList = catsList;
    }

    public long getId() {
        return id;
    }
}
