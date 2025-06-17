package org.africa.semicolon.mylibrary.services;

import org.africa.semicolon.mylibrary.dtos.requests.LoginUserRequest;
import org.africa.semicolon.mylibrary.dtos.requests.RegisterUserRequest;
import org.africa.semicolon.mylibrary.dtos.responses.LoginUserResponse;
import org.africa.semicolon.mylibrary.dtos.responses.RegisterUserResponse;

public interface UserService {
    RegisterUserResponse register(RegisterUserRequest request);
    LoginUserResponse login(LoginUserRequest request);
}
