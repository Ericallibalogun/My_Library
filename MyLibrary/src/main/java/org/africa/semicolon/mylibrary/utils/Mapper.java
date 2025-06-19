package org.africa.semicolon.mylibrary.utils;

import org.africa.semicolon.mylibrary.data.models.Book;
import org.africa.semicolon.mylibrary.data.models.Role;
import org.africa.semicolon.mylibrary.data.models.User;
import org.africa.semicolon.mylibrary.dtos.requests.AddBookRequest;
import org.africa.semicolon.mylibrary.dtos.requests.RegisterUserRequest;
import org.africa.semicolon.mylibrary.dtos.responses.AddBookResponse;

public class Mapper {
    public static User mapToUser(RegisterUserRequest request){
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(request.getPassword());
        user.setRole(Role.USER);
        return user;
    }
    public static Book mapRequestToBook(AddBookRequest request){
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setCategory(request.getCategory());
        book.setAvailableCopies(request.getAvailableCopies());
        return book;
    }
    public static AddBookResponse mapRequestToBookResponse(Book savedBook){
        AddBookResponse response = new AddBookResponse();
        response.setMessage("Added successfully");
        response.setTitle(savedBook.getTitle());
        response.setAuthor(savedBook.getAuthor());
        response.setCategory(savedBook.getCategory());
        response.setAvailableCopies(savedBook.getAvailableCopies());
        return response;
    }
}
