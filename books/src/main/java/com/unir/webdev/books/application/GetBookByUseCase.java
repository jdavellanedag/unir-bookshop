package com.unir.webdev.books.application;

import com.unir.webdev.books.domain.Book;
import com.unir.webdev.books.domain.repository.BookRepository;
import com.unir.webdev.books.infrastructure.repositories.BookRepositoryElaImp;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults (makeFinal = true, level = AccessLevel.PRIVATE)
public class GetBookByUseCase {
    BookRepositoryElaImp bookRepository;

    public List<Book> getBookBy(String name, String author) {
        return bookRepository.findBooks(name, author);
    }
}
