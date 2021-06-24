package com.mindtree.Movies.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.Movies.models.User;

public interface UserRepository  extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);

}
