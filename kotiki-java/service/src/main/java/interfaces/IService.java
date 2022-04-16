package interfaces;

import models.Cat;
import models.Owner;
import objects.OwnerDao;

import java.util.List;

public interface IService {
    Owner findOwner(int id);

    void saveOwner(Owner Owner);
    void deleteOwner(Owner Owner);
    void updateOwner(Owner Owner);

    List<Owner> findAllOwners();

    Cat findCatById(int id);
}
