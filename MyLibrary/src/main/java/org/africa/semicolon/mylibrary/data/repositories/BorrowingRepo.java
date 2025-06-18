package org.africa.semicolon.mylibrary.data.repositories;

import org.africa.semicolon.mylibrary.data.models.Borrowing;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BorrowingRepo extends MongoRepository<Borrowing,String> {
}
