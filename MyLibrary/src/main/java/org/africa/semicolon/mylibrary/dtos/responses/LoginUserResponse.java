package org.africa.semicolon.mylibrary.dtos.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginUserResponse {
    private String message;
    private String role ;

}
