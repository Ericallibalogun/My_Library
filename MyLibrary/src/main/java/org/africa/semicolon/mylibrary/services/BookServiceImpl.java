package org.africa.semicolon.mylibrary.services;

import org.africa.semicolon.mylibrary.data.models.Book;
import org.africa.semicolon.mylibrary.data.models.Borrowing;
import org.africa.semicolon.mylibrary.data.repositories.BookRepo;
import org.africa.semicolon.mylibrary.data.repositories.BorrowingRepo;
import org.africa.semicolon.mylibrary.dtos.requests.AddBookRequest;
import org.africa.semicolon.mylibrary.dtos.requests.BorrowBookRequest;
import org.africa.semicolon.mylibrary.dtos.requests.ReturnBookRequest;
import org.africa.semicolon.mylibrary.dtos.requests.UpdateBookRequest;
import org.africa.semicolon.mylibrary.dtos.responses.*;
import org.africa.semicolon.mylibrary.exceptions.BookNotFoundException;
import org.africa.semicolon.mylibrary.exceptions.ExistedBookException;
import org.africa.semicolon.mylibrary.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static org.africa.semicolon.mylibrary.utils.Mapper.mapRequestToBookResponse;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private BorrowingRepo borrowingRepo;

    @Override
    public AddBookResponse addBook(AddBookRequest request) {
        if (bookRepo.existsByTitleAndAuthor(request.getTitle(), request.getAuthor())) {
            throw new ExistedBookException("Book already exists.");
        }
        Book book = Mapper.mapRequestToBook(request);
        Book savedBook = bookRepo.save(book);
        AddBookResponse response = mapRequestToBookResponse(savedBook);
        return response;
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

    @Override
    public BorrowBookResponse borrowBook(BorrowBookRequest request) {
        Book book = bookRepo.findById(request.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("No copies available");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepo.save(book);

        Borrowing record = new Borrowing();
        record.setBookId(book.getId());
        record.setUserId(request.getUserId());
        record.setBorrowDate(LocalDate.now());
        record.setReturned(false);

        Borrowing savedRecord = borrowingRepo.save(record);

        BorrowBookResponse response = new BorrowBookResponse();
        response.setMessage("Book borrowed successfully");
        response.setBorrowId(savedRecord.getId());
        return response;
    }
    @Override
    public ReturnBookResponse returnBook(ReturnBookRequest request) {
        Borrowing record = borrowingRepo.findById(request.getBorrowId())
                .orElseThrow(() -> new RuntimeException("Borrow record not found"));

        if (record.isReturned()) {
            throw new RuntimeException("Book already returned");
        }

        Book book = bookRepo.findById(record.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepo.save(book);

        record.setReturned(true);
        record.setReturnDate(LocalDate.now());
        borrowingRepo.save(record);

        ReturnBookResponse response = new ReturnBookResponse();
        response.setMessage("Book returned successfully");
        return response;
    }
}
