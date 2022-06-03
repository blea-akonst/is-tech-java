package com.akonst.kotikijava.services;

import com.akonst.kotikijava.dao.repositories.OwnerRepository;

import com.akonst.kotikijava.dto.objects.OwnerDto;
import com.akonst.kotikijava.dao.models.Owner;
import com.akonst.kotikijava.security.KotikiUserDetails;
import com.akonst.kotikijava.security.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerService {

    private final OwnerRepository repository;

    @Autowired
    public OwnerService(OwnerRepository repository) {
        this.repository = repository;
    }

    public OwnerDto findById(long id) throws UnauthorizedException {
        Owner dbResult = repository.findById(id);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        KotikiUserDetails currUserDetails = (KotikiUserDetails) auth.getPrincipal();

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("admin"))
                || dbResult.getId() == currUserDetails.getUser().getId()) {
            return new OwnerDto(dbResult);
        }

        throw new UnauthorizedException();
    }

    public List<OwnerDto> findByName(String name) {
        List<Owner> dbResult = repository.findByName(name);

        return dbResult.stream().map(OwnerDto::new).toList();
    }

    public List<OwnerDto> findAll() {
        List<Owner> dbResult = repository.findAll();
        List<OwnerDto> dtoResult = new ArrayList<>();

        for (Owner owner : dbResult) {
            dtoResult.add(new OwnerDto(owner));
        }

        return dtoResult;
    }
    
    public OwnerDto save(Owner owner) {
        Owner dbResult = repository.save(owner);

        return new OwnerDto(dbResult);
    }

    public boolean delete(long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println("Got exception: " + e.getMessage());
            return false;
        }
    }

    public boolean update(Owner owner) {
        try {
            repository.save(owner);
            return true;
        } catch (Exception e) {
            System.out.println("Got exception: " + e.getMessage());
            return false;
        }
    }
}
