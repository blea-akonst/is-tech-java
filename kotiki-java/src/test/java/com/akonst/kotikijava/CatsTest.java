package com.akonst.kotikijava;

import com.akonst.kotikijava.dao.models.Owner;
import com.akonst.kotikijava.dao.repositories.OwnerRepository;
import com.akonst.kotikijava.services.OwnerService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.junit.jupiter.api.*;

import java.sql.Timestamp;

import static org.mockito.BDDMockito.*;

public class CatsTest {
    @Mock
    private OwnerRepository repository;
    private OwnerService ownerService;

    public CatsTest() {
        MockitoAnnotations.openMocks(this);
        this.ownerService = new OwnerService(repository);
    }

    @Test
    void deleteOwner_ownerHasBeenDeleted()
    {
        Owner owner = new Owner("Artyom", Timestamp.valueOf("2002-11-22 00:00:00"));
        ownerService.delete(owner.getId());

        verify(repository).deleteById(owner.getId());
    }

    @Test
    void findAllOwners_allOwnersHasBeenFound()
    {
        ownerService.findAll();
        verify(repository).findAll();
    }
}