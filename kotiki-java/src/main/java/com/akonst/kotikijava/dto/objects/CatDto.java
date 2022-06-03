package com.akonst.kotikijava.dto.objects;

import com.akonst.kotikijava.dao.models.Cat;

import java.sql.Timestamp;

public class CatDto {

    private long id;
    private long ownerId;

    private String name;

    private String breed;
    private String color;
    private Timestamp dateOfBirth;

    public CatDto(Cat dbCat)
    {
        this.id = dbCat.getId();
        this.ownerId = dbCat.getOwner().getId();
        this.name = dbCat.getName();
        this.breed = dbCat.getBreed();
        this.color = dbCat.getColor();
        this.dateOfBirth = dbCat.getDateOfBirth();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public String getColor() {
        return color;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public Timestamp getDateOfBirth() {
        return dateOfBirth;
    }
}
