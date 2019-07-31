package com.crm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crm.exception.ResourceNotFoundException;
import com.crm.model.User;
import com.crm.repository.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "User")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "Mostra lista de usuarios")
    @GetMapping("/users")
    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }


    @PostMapping("/Users")
    public User createUser(@Valid @RequestBody User User) {
        return userRepository.save(User);
    }

    @PutMapping("/Users/{UserId}")
    public User updateUser(@PathVariable Long UserId,
                                   @Valid @RequestBody User UserRequest) {
        return userRepository.findById(UserId)
                .map(User -> {
                    User.setUsername(UserRequest.getUsername());
                    User.setPassword(UserRequest.getPassword());
                    return userRepository.save(User);
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + UserId));
    }


    @DeleteMapping("/Users/{UserId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long UserId) {
        return userRepository.findById(UserId)
                .map(User -> {
                	userRepository.delete(User);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + UserId));
    }
}