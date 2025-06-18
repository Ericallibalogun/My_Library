package org.africa.semicolon.mylibrary.data.repositories;

import org.africa.semicolon.mylibrary.data.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepo extends MongoRepository<Book, String> {
    boolean existsByTitleAndAuthor(String title, String author);

    List<Book> findByTitleIgnoreCaseContaining(String title);

    List<Book> findByAuthorIgnoreCaseContaining(String author);

    List<Book> findByCategoryIgnoreCaseContaining(String category);
}
