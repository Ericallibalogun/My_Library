package org.africa.semicolon.mylibrary.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginUserRequest {
    @NotBlank(message = "this field is required")
    private String email;

    @Size(min = 6, max = 20,message = "password is incorrect")
    @NotBlank(message = "this field is required")
    private String password;
}
