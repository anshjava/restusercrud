package ru.kamuzta.restusercrud.service;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kamuzta.restusercrud.model.User;
import ru.kamuzta.restusercrud.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Checking server status
     * endpoint /hello
     * @return greeting_message
     */
    public String hello() {
        return "I'm on-line";
    }

    /**
     * Return all users
     * endpoint /getusers
     * @return list of users
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Return User by it's Id
     * endpoint /getuser
     * @return one user
     * @param id - id of User
     */
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new EntityNotFoundException("User with ID :"+id+" Not Found!"));
    }

    /**
     * Save User
     * endpoint /saveuser
     * @return saved Uder
     * @param user - user to save
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Delete User by it's Id
     * endpoint /deleteuser
     * @param id - id of User
     */
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
