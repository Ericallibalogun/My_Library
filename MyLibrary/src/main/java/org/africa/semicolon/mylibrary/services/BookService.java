package org.africa.semicolon.mylibrary.services;

import org.africa.semicolon.mylibrary.dtos.requests.AddBookRequest;
import org.africa.semicolon.mylibrary.dtos.requests.DeleteBookRequest;
import org.africa.semicolon.mylibrary.dtos.requests.UpdateBookRequest;
import org.africa.semicolon.mylibrary.dtos.responses.AddBookResponse;
import org.africa.semicolon.mylibrary.dtos.responses.UpdateBookResponse;

public interface BookService {
    AddBookResponse addBook(AddBookRequest request);
    UpdateBookResponse updateBook(UpdateBookRequest request);
    void deleteBook(String id);
}
