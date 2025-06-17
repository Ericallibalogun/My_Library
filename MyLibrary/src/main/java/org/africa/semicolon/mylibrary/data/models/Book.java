package org.africa.semicolon.mylibrary.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "books")
public class Book {
    @Id
    private String id;
    private String author;
    private String title;
    private String category;
    private int totalCopies;
    private int availableCopies;
}
