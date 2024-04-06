package com.HyperSrot.assignment.shopping_app.service;

import com.HyperSrot.assignment.shopping_app.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    void deleteUserById(Long id);
}
