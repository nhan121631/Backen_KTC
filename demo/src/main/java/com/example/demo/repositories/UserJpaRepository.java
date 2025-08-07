package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.User;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    // @EntityGraph(attributePaths = { "roles", "userProfile" })
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles LEFT JOIN FETCH u.userProfile WHERE u.username = :username")
    Optional<User> findByUsername(@Param("username") String username);

    // boolean existsByUsername(String username);

}
