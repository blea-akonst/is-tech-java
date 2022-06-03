package com.akonst.kotikijava.dao.repositories;

import java.util.List;

import com.akonst.kotikijava.dao.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner findById(long id);
    List<Owner> findByName (String name);
}