package org.africa.semicolon.mylibrary.services;

import org.africa.semicolon.mylibrary.data.models.Book;
import org.africa.semicolon.mylibrary.dtos.requests.AddBookRequest;
import org.africa.semicolon.mylibrary.dtos.requests.BorrowBookRequest;
import org.africa.semicolon.mylibrary.dtos.requests.ReturnBookRequest;
import org.africa.semicolon.mylibrary.dtos.requests.UpdateBookRequest;
import org.africa.semicolon.mylibrary.dtos.responses.*;

import java.util.List;

public interface BookService {
    AddBookResponse addBook(AddBookRequest request);
    UpdateBookResponse updateBook(UpdateBookRequest request);
    DeleteBookResponse deleteBook(String id);
    List<Book> searchBooks(String title, String author, String category);
    BorrowBookResponse borrowBook(BorrowBookRequest request);
    ReturnBookResponse returnBook(ReturnBookRequest request);
}
