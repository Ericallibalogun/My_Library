package org.africa.semicolon.mylibrary.data.repositories;

import org.africa.semicolon.mylibrary.data.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepo extends MongoRepository<Book, String> {
    boolean existsByTitleAndAuthor(String title, String author);
}
