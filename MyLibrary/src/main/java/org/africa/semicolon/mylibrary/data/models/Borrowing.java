package org.africa.semicolon.mylibrary.data.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
@Data
@Document(collection = "borrowed")
@NoArgsConstructor
public class Borrowing {
    @Id
    private String id;
    private String userId;
    private String bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private LocalDate dueDate;
    private boolean returned;

}
