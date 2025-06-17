package org.africa.semicolon.mylibrary.dtos.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateBookRequest {
    @NotBlank(message = "Book ID is required")
    private String id;

    private String title;
    private String author;
    private String category;

    @Min(value = 0, message = "Available copies cannot be negative")
    private int availableCopies;
}
