package com.unir.webdev.books.infrastructure.controllers;

import com.unir.webdev.books.application.DeleteBookUseCase;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DeleteBookController {
    DeleteBookUseCase deleteBookUseCase;

    @DeleteMapping("/{bookID}")
    public ResponseEntity<String> deleteBook(@PathVariable UUID bookID) {
       String deletionResult = deleteBookUseCase.deleteBook(bookID);
       return ResponseEntity.ok().body(deletionResult);

    }
}
