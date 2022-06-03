package com.akonst.kotikijava.dto.objects;

import com.akonst.kotikijava.dao.models.Owner;

import java.sql.Timestamp;

public class OwnerDto {

    private long id;
    private String name;
    private Timestamp dateOfBirth;

    public OwnerDto(Owner dbOwner) {
        this.id = dbOwner.getId();
        this.name = dbOwner.getName();
        this.dateOfBirth = dbOwner.getDateOfBirth();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Timestamp getDateOfBirth() {
        return dateOfBirth;
    }
}
