package com.akonst.kotikijava.controller;

import com.akonst.kotikijava.dao.models.User;
import com.akonst.kotikijava.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/registration")
    public String addUser(@RequestBody User user) {

        if(user != null) {
            userService.save(user);
            return "Added a user";
        } else {
            return "Request does not contain a body";
        }
    }
}
