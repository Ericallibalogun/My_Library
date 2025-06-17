package org.africa.semicolon.mylibrary.data.repositories;

import org.africa.semicolon.mylibrary.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {
    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

   Optional<User> findByEmail(String email);
}
