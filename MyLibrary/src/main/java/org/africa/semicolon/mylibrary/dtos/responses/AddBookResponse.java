package org.africa.semicolon.mylibrary.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBookResponse {
    private String message;
    private String title;
    private String author;
    private String category;
    private int availableCopies;
}
