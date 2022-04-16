package services;

import interfaces.IDao;
import objects.OwnerDao;

import interfaces.IService;

import models.Owner;
import models.Cat;

import java.util.List;

public class OwnerService implements IService {
    private IDao ownerDao = new OwnerDao();

    public OwnerService(IDao dao) {
        this.ownerDao = dao;
    }


    public Owner findOwner(int id) {
        return ownerDao.findById(id);
    }

    public void saveOwner(Owner Owner) {
        ownerDao.save(Owner);
    }

    public void deleteOwner(Owner Owner) {
        ownerDao.delete(Owner);
    }

    public void updateOwner(Owner Owner) {
        ownerDao.update(Owner);
    }

    public List<Owner> findAllOwners() {
        return ownerDao.findAll();
    }

    public Cat findCatById(int id) {
        return ownerDao.findCatById(id);
    }
}
