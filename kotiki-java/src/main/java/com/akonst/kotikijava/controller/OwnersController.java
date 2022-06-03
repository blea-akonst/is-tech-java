package com.akonst.kotikijava.controller;

import com.akonst.kotikijava.dao.models.Owner;
import com.akonst.kotikijava.dto.objects.OwnerDto;
import com.akonst.kotikijava.security.UnauthorizedException;
import com.akonst.kotikijava.services.OwnerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnersController {

    @Autowired
    OwnerService ownerService;

    @GetMapping("/all")
    public List<OwnerDto> getAllOwners() {
        return ownerService.findAll();
    }

    @GetMapping("/id/{id}")
    public OwnerDto getOwnerById(@PathVariable long id) throws UnauthorizedException {
        return ownerService.findById(id);
    }

    @GetMapping("/name/{name}")
    public List<OwnerDto> getOwnersByName(@PathVariable String name) {
        return ownerService.findByName(name);
    }

    @PostMapping("")
    public String addOwner(@RequestBody Owner owner) {

        if(owner != null) {
            ownerService.save(owner);
            return "Added a owner";
        } else {
            return "Request does not contain a body";
        }
    }

    @PutMapping("")
    public String updateOwner(@RequestBody Owner owner) {
        if(owner != null) {
            ownerService.update(owner);
            return "Updated Owner.";
        } else {
            return "Request does not contain a body";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteOwner(@PathVariable("id") long id) {

        if(id > 0) {
            if(ownerService.delete(id)) {
                return "Deleted the owner.";
            } else {
                return "Cannot delete the owner.";
            }
        }
        return "The id is invalid for the owner.";
    }
}
