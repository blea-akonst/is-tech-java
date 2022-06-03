package com.akonst.kotikijava.controller;

import com.akonst.kotikijava.dao.models.Cat;
import com.akonst.kotikijava.dto.objects.CatDto;
import com.akonst.kotikijava.security.UnauthorizedException;
import com.akonst.kotikijava.services.CatsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cats")
public class CatsController {

    private final CatsService catsService;

    public CatsController(CatsService catsService) {
        this.catsService = catsService;
    }

    @GetMapping("/all")
    public List<CatDto> getAllCats() {
        return catsService.findAll();
    }

    @GetMapping("/id/{id}")
    public CatDto getCatById(@PathVariable long id) throws UnauthorizedException {
        return catsService.findById(id);
    }

    @GetMapping("/name/{name}")
    public List<CatDto> getCatsByName(@PathVariable String name) {
        return catsService.findByName(name);
    }

    @GetMapping("/breed/{breed}")
    public List<CatDto> getCatsByBreed(@PathVariable String breed) {
        return catsService.findByBreed(breed);
    }

    @GetMapping("/color/{color}")
    public List<CatDto> getCatsByColor(@PathVariable String color) {
        return catsService.findByColor(color);
    }

    @GetMapping("/owner-id/{ownerId}")
    public List<CatDto> getCatsByOwnerId(@PathVariable long ownerId) {
        return catsService.findByOwnerId(ownerId);
    }

    @PostMapping("")
    public String addCat(@RequestBody Cat cat) {

        if(cat != null) {
            catsService.save(cat);
            return "Added a cat";
        } else {
            return "Request does not contain a body";
        }
    }

    @PutMapping("")
    public String updateCat(@RequestBody Cat cat) {
        if(cat != null) {
            catsService.update(cat);
            return "Updated cat.";
        } else {
            return "Request does not contain a body";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteCat(@PathVariable("id") long id) {

        if(id > 0) {
            if(catsService.delete(id)) {
                return "Deleted the cat.";
            } else {
                return "Cannot delete the cat.";
            }
        }
        return "The id is invalid for the cat.";
    }
}
