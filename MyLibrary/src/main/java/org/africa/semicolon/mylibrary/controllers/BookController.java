package org.africa.semicolon.mylibrary.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.africa.semicolon.mylibrary.data.models.Book;
import org.africa.semicolon.mylibrary.dtos.requests.AddBookRequest;
import org.africa.semicolon.mylibrary.dtos.requests.UpdateBookRequest;
import org.africa.semicolon.mylibrary.dtos.responses.AddBookResponse;
import org.africa.semicolon.mylibrary.dtos.responses.ApiResponse;
import org.africa.semicolon.mylibrary.dtos.responses.UpdateBookResponse;
import org.africa.semicolon.mylibrary.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PutMapping("/update")
    public ResponseEntity<?> updateBook(@RequestBody @Valid UpdateBookRequest request) {
        UpdateBookResponse response = bookService.updateBook(request);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, response));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok(new ApiResponse(true, "Book deleted successfully"));
    }
    @GetMapping("/search")
    public ResponseEntity<?> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category
    ) {
        List<Book> results = bookService.searchBooks(title, author, category);
        return ResponseEntity.ok(results);
    }


}
