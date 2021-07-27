package ru.kamuzta.restusercrud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import ru.kamuzta.restusercrud.model.User;
import ru.kamuzta.restusercrud.service.UserService;

import java.time.LocalDate;

@SpringBootTest
class RestusercrudApplicationTests {

    @Autowired
    UserService userService;


    @Test
    void contextLoads() {
    }

    @Test
    void crudTest() {
        User user = new User(0L,"TestFirstName","TestLastName", LocalDate.now());
        User savedUser = userService.saveUser(user);
        Assert.isTrue(user.getBirthdate().equals(savedUser.getBirthdate()), "Дни рождения не совпадают");
        User loadedUser = userService.findById(savedUser.getId());
        Assert.isTrue(savedUser.getBirthdate().equals(loadedUser.getBirthdate()), "Дни рождения не совпадают");
    }

}
