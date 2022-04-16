package interfaces;

import models.Owner;
import models.Cat;

import java.util.List;

public interface IDao {
    Owner findById(int id);

    void save(Owner owner);
    void update(Owner owner);
    void delete(Owner owner);

    List<Owner> findAll();

    Cat findCatById(int id);
}
