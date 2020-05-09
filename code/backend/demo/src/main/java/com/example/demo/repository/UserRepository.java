package com.example.demo.repository;


import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * UserRepository interface
 *
 * used to operation with database
 *
 * @author Zhe Li
 *
 * @date 2020/05/01
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * find user by username
     *
     * @param username finding user with this exact username
     * @return the user with this username
     * */
    Optional<User> findByUsername(String username);

    /**
     * check if username is valid
     *
     * @param username pending username
     * @return  true if exists same username
     */
    Boolean existsByUsername(String username);
}
