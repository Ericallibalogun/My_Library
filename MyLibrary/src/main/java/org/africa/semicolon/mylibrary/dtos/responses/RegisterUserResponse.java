package org.africa.semicolon.mylibrary.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterUserResponse {
    private String message;
    private String fullName;
}
