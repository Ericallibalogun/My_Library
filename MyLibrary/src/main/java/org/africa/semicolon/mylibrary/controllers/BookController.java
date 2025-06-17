package org.africa.semicolon.mylibrary.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.africa.semicolon.mylibrary.dtos.requests.AddBookRequest;
import org.africa.semicolon.mylibrary.dtos.responses.AddBookResponse;
import org.africa.semicolon.mylibrary.dtos.responses.ApiResponse;
import org.africa.semicolon.mylibrary.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody @Valid AddBookRequest request) {
            AddBookResponse response = bookService.addBook(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, response));
    }


}
