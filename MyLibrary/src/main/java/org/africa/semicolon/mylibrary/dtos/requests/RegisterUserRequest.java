package org.africa.semicolon.mylibrary.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.africa.semicolon.mylibrary.data.models.Role;

@Data
@NoArgsConstructor
public class RegisterUserRequest {
    @NotBlank(message = "this field is required")
    private String fullName;

    @Email(message = "invalid email")
    @NotBlank(message = "this field is required")
    private String email;

    @NotBlank(message= "this field is required")
    @Pattern(
            regexp = "^\\+?[0-9]{10,15}$",
            message = "Invalid phone number format"
    )
    private String phoneNumber;


    @Size(min = 6, max = 18,message = "password must be at least 6 characters")
    private String password;
    private Role role;
}
