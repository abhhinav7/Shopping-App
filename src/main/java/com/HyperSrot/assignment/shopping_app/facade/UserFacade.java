package com.HyperSrot.assignment.shopping_app.facade;

import com.HyperSrot.assignment.shopping_app.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserFacade {
    User save(User user);

    List<User> findAll();

    User findById(Long id);

    ResponseEntity<String> deleteUserById(Long id);
}
