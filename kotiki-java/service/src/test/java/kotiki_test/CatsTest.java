package kotiki_test;

import models.Owner;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import services.OwnerService;

import objects.OwnerDao;

import org.junit.jupiter.api.*;

import java.sql.Timestamp;

import static org.mockito.BDDMockito.*;

public class CatsTest {
    @Mock
    private OwnerDao ownerDao;
    private OwnerService ownerService;

    public CatsTest() {
        MockitoAnnotations.openMocks(this);
        this.ownerService = new OwnerService(ownerDao);
    }

    @Test
    void findOwner_ownerHasBeenFound()
    {
        given(ownerDao.findById(1)).willReturn(new Owner("Artyom", Timestamp.valueOf("2002-11-22 00:00:00")));

        Assertions.assertEquals(ownerService.findOwner(1).getName(), "Artyom");
    }

    @Test
    void saveOwner_ownerHasBeenSaved()
    {
        Owner owner = new Owner("Artyom", Timestamp.valueOf("2002-11-22 00:00:00"));
        ownerService.saveOwner(owner);

        verify(ownerDao).save(owner);
    }

    @Test
    void deleteOwner_ownerHasBeenDeleted()
    {
        Owner owner = new Owner("Artyom", Timestamp.valueOf("2002-11-22 00:00:00"));
        ownerService.deleteOwner(owner);

        verify(ownerDao).delete(owner);
    }

    @Test
    void findAllOwners_allOwnersHasBeenFound()
    {
        ownerService.findAllOwners();
        verify(ownerDao).findAll();
    }
}
