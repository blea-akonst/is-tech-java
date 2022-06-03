package com.akonst.kotikijava.services;

import com.akonst.kotikijava.dao.repositories.CatsRepository;
import com.akonst.kotikijava.dao.models.Cat;
import com.akonst.kotikijava.dto.objects.CatDto;
import com.akonst.kotikijava.security.KotikiUserDetails;
import com.akonst.kotikijava.security.UnauthorizedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatsService {
    private final CatsRepository repository;

    public CatsService(CatsRepository repository) {
        this.repository = repository;
    }

    public CatDto findById(long id) throws UnauthorizedException {
        Cat dbResult = repository.findById(id);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        KotikiUserDetails currUserDetails = (KotikiUserDetails) auth.getPrincipal();

        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("admin"))
            || dbResult.getOwner().getId() == currUserDetails.getUser().getOwner().getId()) {
            return new CatDto(dbResult);
        }

        throw new UnauthorizedException();
    }

    public List<CatDto> findByName(String name) {
        List<Cat> dbResult = repository.findByName(name);

        return dbResult.stream().map(CatDto::new).toList();
    }

    public List<CatDto> findByBreed(String breed) {
        List<Cat> dbResult = repository.findByBreed(breed);

        return dbResult.stream().map(CatDto::new).toList();
    }

    public List<CatDto> findByColor(String color) {
        List<Cat> dbResult = repository.findByColor(color);

        return dbResult.stream().map(CatDto::new).toList();
    }

    public List<CatDto> findByOwnerId(long ownerId) {
        List<Cat> dbResult = repository.findByOwnerId(ownerId);

        return dbResult.stream().map(CatDto::new).toList();
    }

    public List<CatDto> findAll() {
        List<Cat> dbResult = repository.findAll();
        List<CatDto> dtoResult = new ArrayList<>();

        for (Cat Cat : dbResult) {
            dtoResult.add(new CatDto(Cat));
        }

        return dtoResult;
    }

    public CatDto save(Cat cat) {
        Cat dbResult = repository.save(cat);

        return new CatDto(dbResult);
    }

    public boolean update(Cat cat) {
        try {
            repository.save(cat);
            return true;
        } catch (Exception e) {
            System.out.println("Got exception: " + e.getMessage());
            return false;
        }
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
}
