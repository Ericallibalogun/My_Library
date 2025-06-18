package org.africa.semicolon.mylibrary.services;

import org.africa.semicolon.mylibrary.data.models.Book;
import org.africa.semicolon.mylibrary.dtos.requests.AddBookRequest;
import org.africa.semicolon.mylibrary.dtos.requests.UpdateBookRequest;
import org.africa.semicolon.mylibrary.dtos.responses.AddBookResponse;
import org.africa.semicolon.mylibrary.dtos.responses.DeleteBookResponse;
import org.africa.semicolon.mylibrary.dtos.responses.UpdateBookResponse;

import java.util.List;

public interface BookService {
    AddBookResponse addBook(AddBookRequest request);
    UpdateBookResponse updateBook(UpdateBookRequest request);
    DeleteBookResponse deleteBook(String id);
    List<Book> searchBooks(String title, String author, String category);
}
