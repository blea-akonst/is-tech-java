package models.builders;

import models.Cat;
import models.Owner;
import objects.OwnerDao;

import java.sql.Timestamp;
import java.util.List;

public class CatBuilder {
    private String name;
    private String breed;
    private String color;
    
    private Owner owner;
    
    private Timestamp dateOfBirth;

    private List<Integer> friendsIdList;


    public CatBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CatBuilder setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    public CatBuilder setColor(String color) {
        this.color = color;
        return this;
    }

    public CatBuilder setOwner(Owner owner) {
        this.owner = owner;
        return this;
    }

    public CatBuilder setDateOfBirth(Timestamp dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public CatBuilder setFriendIdList(List<Integer> friendIdList) throws Exception {
        OwnerDao ownerDao = new OwnerDao();

        for (int id: friendIdList) {
            if (ownerDao.findCatById(id) == null) {
                throw new Exception("You provided incorrect cat's friends list!");
            }
        }

        this.friendsIdList = friendIdList;
        return this;
    }
    
    public Cat getCat() {
        return new Cat(name, breed, color, owner, dateOfBirth, friendsIdList);
    }
}
