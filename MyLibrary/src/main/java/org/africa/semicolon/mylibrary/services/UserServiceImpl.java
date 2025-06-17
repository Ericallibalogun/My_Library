package org.africa.semicolon.mylibrary.services;

import org.africa.semicolon.mylibrary.data.models.Role;
import org.africa.semicolon.mylibrary.data.models.User;
import org.africa.semicolon.mylibrary.data.repositories.UserRepo;
import org.africa.semicolon.mylibrary.dtos.requests.LoginUserRequest;
import org.africa.semicolon.mylibrary.dtos.requests.RegisterUserRequest;
import org.africa.semicolon.mylibrary.dtos.responses.LoginUserResponse;
import org.africa.semicolon.mylibrary.dtos.responses.RegisterUserResponse;
import org.africa.semicolon.mylibrary.exceptions.EmailNotFoundException;
import org.africa.semicolon.mylibrary.exceptions.InvalidPasswordException;
import org.africa.semicolon.mylibrary.exceptions.UserAlreadyExistsException;
import org.africa.semicolon.mylibrary.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public RegisterUserResponse register(RegisterUserRequest request) {
        boolean emailExists = userRepo.existsByEmail(request.getEmail());
        boolean phoneExists = userRepo.existsByPhoneNumber(request.getPhoneNumber());

        if (emailExists || phoneExists)
            throw new UserAlreadyExistsException("This email or phone number has been registered");
        User user = Mapper.mapToUser(request);

        Role userRole = Role.valueOf(request.getRole().toUpperCase());
        user.setRole(userRole);

        userRepo.save(user);
        RegisterUserResponse response = new RegisterUserResponse();
        response.setFullName(user.getFullName());
        response.setMessage("Registered successfully Welcome " + response.getFullName());
        return response;
    }

    @Override
    public LoginUserResponse login(LoginUserRequest request) {
        LoginUserResponse response = new LoginUserResponse();
        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new EmailNotFoundException("Email not found"));

        boolean isWrongPassword = !user.getPassword().equals(request.getPassword());
        if(isWrongPassword) throw new InvalidPasswordException("Wrong password");

        response.setMessage("Login successful. welcome " + user.getFullName());
        response.setRole(user.getRole().name());
        return response;
    }
}