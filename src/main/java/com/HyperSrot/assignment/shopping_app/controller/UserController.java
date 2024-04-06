package com.HyperSrot.assignment.shopping_app.controller;

import com.HyperSrot.assignment.shopping_app.entity.User;
import com.HyperSrot.assignment.shopping_app.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserFacade userFacade;

    @GetMapping("")
    public List<User> getUsers(){
        return userFacade.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        if(user.getUsername()==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error occurred while adding user as Username can't be empty");
        }
        if (user != null) {
            return ResponseEntity.ok(userFacade.save(user));
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return userFacade.deleteUserById(id);
    }
}
