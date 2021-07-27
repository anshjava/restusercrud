package ru.kamuzta.restusercrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kamuzta.restusercrud.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
