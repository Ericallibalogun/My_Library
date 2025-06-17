package org.africa.semicolon.mylibrary.controllers;

import org.africa.semicolon.mylibrary.data.models.User;
import org.africa.semicolon.mylibrary.dtos.requests.LoginUserRequest;
import org.africa.semicolon.mylibrary.dtos.requests.RegisterUserRequest;
import org.africa.semicolon.mylibrary.dtos.responses.ApiResponse;
import org.africa.semicolon.mylibrary.dtos.responses.LoginUserResponse;
import org.africa.semicolon.mylibrary.dtos.responses.RegisterUserResponse;
import org.africa.semicolon.mylibrary.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest request) {
        try {
            RegisterUserResponse response = userService.register(request);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginUserRequest request) {
        try{
            LoginUserResponse response = userService.login(request);
            return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
