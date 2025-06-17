package org.africa.semicolon.mylibrary.dtos.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddBookResponse {
    private String message;
    private String title;
    private String author;
    private String category;
    private int availableCopies;
}
