package models;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.sql.Timestamp;

@Entity(name = "Cat")
@Table (name = "`Cats`")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="owner_id")
    private Owner owner;

    @Column(name = "friend_id_list", columnDefinition = "integer[]")
    @Type(ListArrayType.class)
    private List<Integer> friendIdList = new ArrayList<>();

    private String name;
    private String breed;
    private String color;

    @Column(name = "date_of_birth")
    private Timestamp dateOfBirth;

    public Cat() {
    }

    public Cat(String name, String breed, String color, Owner owner, Timestamp dateOfBirth, List<Integer> friendIdList) {
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.owner = owner;
        this.dateOfBirth = dateOfBirth;
        this.friendIdList = friendIdList;
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

    public List<Integer> getFriendIdList() {
        return Collections.unmodifiableList(friendIdList);
    }

    public void setFriendIdList(List<Integer> friendIdList) {
        this.friendIdList = friendIdList;
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
}
