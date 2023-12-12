package com.app.service;

import com.app.dto.BookDto;
import com.app.entity.Book;
import com.app.response.ApiResponse;

import java.util.List;
import java.util.Optional;

public interface BookService {
    ApiResponse addBook(BookDto bookDto);
    List<Book> viewAllBooks();
    Optional<Book> getBookById(int id);
    ApiResponse deleteBook(int id);
}
