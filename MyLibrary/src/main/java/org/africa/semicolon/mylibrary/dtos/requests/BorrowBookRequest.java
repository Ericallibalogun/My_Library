package org.africa.semicolon.mylibrary.dtos.requests;

import lombok.Data;

@Data
public class BorrowBookRequest {
        private String bookId;
        private String userId;


}
