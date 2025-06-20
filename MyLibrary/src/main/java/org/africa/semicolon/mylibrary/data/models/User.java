package org.africa.semicolon.mylibrary.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection ="users")
public class User {
    @Id
    private String id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String password;
    private Role role;
    private List<Book> books;
}
