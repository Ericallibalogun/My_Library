package org.africa.semicolon.mylibrary.services;

import org.africa.semicolon.mylibrary.data.models.Book;
import org.africa.semicolon.mylibrary.data.repositories.BookRepo;
import org.africa.semicolon.mylibrary.dtos.requests.AddBookRequest;
import org.africa.semicolon.mylibrary.dtos.requests.UpdateBookRequest;
import org.africa.semicolon.mylibrary.dtos.responses.AddBookResponse;
import org.africa.semicolon.mylibrary.dtos.responses.DeleteBookResponse;
import org.africa.semicolon.mylibrary.dtos.responses.UpdateBookResponse;
import org.africa.semicolon.mylibrary.exceptions.BookNotFoundException;
import org.africa.semicolon.mylibrary.exceptions.ExistedBookException;
import org.africa.semicolon.mylibrary.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.africa.semicolon.mylibrary.utils.Mapper.mapRequestToBookResponse;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepo bookRepo;

    @Override
    public AddBookResponse addBook(AddBookRequest request) {
        if (bookRepo.existsByTitleAndAuthor(request.getTitle(), request.getAuthor())) {
            throw new ExistedBookException("Book already exists.");
        }
        Book book = Mapper.mapRequestToBook(request);
        Book savedBook = bookRepo.save(book);
        return mapRequestToBookResponse(savedBook);
    }

    @Override
    public UpdateBookResponse updateBook(UpdateBookRequest request) {
        Book book = bookRepo.findById(request.getId())
                .orElseThrow(()-> new BookNotFoundException("Book not found."));

        boolean titleIsNotEmpty = request.getTitle() != null && !request.getTitle().isEmpty();
        if(titleIsNotEmpty) book.setTitle(request.getTitle());

        boolean authorIsNotEmpty = request.getAuthor() != null && !request.getAuthor().isEmpty();
        if(authorIsNotEmpty) book.setAuthor(request.getAuthor());

        boolean categoryIsNotEmpty =  request.getCategory() != null && !request.getCategory().isEmpty();
        if(categoryIsNotEmpty) book.setCategory(request.getCategory());

        int newTotal = book.getAvailableCopies() + request.getAvailableCopies();
        boolean availableCopyIsLessThanZero = book.getAvailableCopies() > 0;
        if(availableCopyIsLessThanZero) book.setAvailableCopies(newTotal);

        bookRepo.save(book);

        UpdateBookResponse response = new UpdateBookResponse();
        response.setMessage("Updated successfully");
        return response;
    }

    @Override
    public DeleteBookResponse deleteBook(String id) {
        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));

        bookRepo.delete(book);

        DeleteBookResponse response = new DeleteBookResponse();
        response.setMessage(book.getTitle() + "deleted successfully");
        return response;
    }

    @Override
    public List<Book> searchBooks(String title, String author, String category) {
        if (title != null) {
            return bookRepo.findByTitleIgnoreCaseContaining(title);
        } else if (author != null) {
            return bookRepo.findByAuthorIgnoreCaseContaining(author);
        } else if (category != null) {
            return bookRepo.findByCategoryIgnoreCaseContaining(category);
        }else return bookRepo.findAll();
    }
}
