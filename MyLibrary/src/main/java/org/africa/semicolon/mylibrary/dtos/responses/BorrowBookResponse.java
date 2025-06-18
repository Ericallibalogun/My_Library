package org.africa.semicolon.mylibrary.dtos.responses;

import lombok.Data;

@Data
public class BorrowBookResponse {
    private String message;
    private String borrowId;
}
