package ru.kamuzta.restusercrud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kamuzta.restusercrud.model.User;
import ru.kamuzta.restusercrud.service.UserService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/hello")
    public ResponseEntity<String> checkConnection() {
        String greeting = userService.hello();
        LOGGER.info("REST: /hello visited");
        return greeting != null
                ? new ResponseEntity<>(greeting, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        LOGGER.info("REST: /users visited");
        return users != null &&  !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> findUserById(@PathVariable(name = "id") Long id) {
        User user;
        LOGGER.info("REST: /users/" + id + " visited");
        try {
            user = userService.findById(id);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/saveuser")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        LOGGER.info("REST: /saveuser visited");
        if (user != null) {
            User savedUser = userService.saveUser(user);
            return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/deleteuser/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable(name = "id") Long id) {
        LOGGER.info("REST: /deleteuser/" + id + " visited");
        try {
            userService.findById(id);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }





}
