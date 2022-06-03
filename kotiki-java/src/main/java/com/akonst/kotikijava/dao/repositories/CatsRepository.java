package com.akonst.kotikijava.dao.repositories;

import java.util.List;

import com.akonst.kotikijava.dao.models.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatsRepository extends JpaRepository<Cat, Long> {
    Cat findById(long id);
    List<Cat> findByName(String name);
    List<Cat> findByBreed(String breed);
    List<Cat> findByColor(String color);
    List<Cat> findByOwnerId(long ownerId);
}
