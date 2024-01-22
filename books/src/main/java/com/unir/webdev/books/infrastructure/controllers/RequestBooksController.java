package com.unir.webdev.books.infrastructure.controllers;

import com.unir.webdev.books.application.RequestBookUseCase;
import com.unir.webdev.books.domain.response.Result;
import com.unir.webdev.books.infrastructure.controllers.DTO.request.BooksIdVerificationRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping ("api/v1/books")
@AllArgsConstructor
@FieldDefaults (level = AccessLevel.PRIVATE)
public class RequestBooksController {
    RequestBookUseCase requestBookUseCase;

    @PostMapping ("/request")
    public ResponseEntity<Object> verifyIds(@RequestBody BooksIdVerificationRequest booksIdVerificationRequest) {
        return Optional.ofNullable(booksIdVerificationRequest)
                       .filter(BooksIdVerificationRequest :: isNotNullBooksID)
                       .map(BooksIdVerificationRequest :: booksID)
                       .map(booksId -> requestBookUseCase.requestBooks(booksId))
                       .map(RequestBooksController :: buildResponseObject)
                       .orElse(ResponseEntity.badRequest()
                                             .body("Bad data given"));
    }

    @NotNull
    private static ResponseEntity<Object> buildResponseObject(
            @NotNull Result<String, Object> stringObjectResult) {
        return stringObjectResult.isSuccess() ?
               ResponseEntity.ok(stringObjectResult.getSuccess()) :
               ResponseEntity.badRequest()
                             .body(stringObjectResult.getError());
    }
}
