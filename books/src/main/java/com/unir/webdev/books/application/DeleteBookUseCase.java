package com.unir.webdev.books.application;

import com.unir.webdev.books.domain.repository.BookRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults (level = AccessLevel.PRIVATE, makeFinal = true)
public class DeleteBookUseCase {
    BookRepository bookRepository;

    public String deleteBook(UUID bookId) {
        if (bookRepository.isValidBook(bookId)) {
            bookRepository.deleteBook(bookId);
            return ("Book deleted successfully");
        } else {
            return ("Book not found or invalid ID");
        }
    }
}
