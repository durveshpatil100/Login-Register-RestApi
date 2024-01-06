package com.app.service;

import com.app.dto.BookDto;
import com.app.entity.Book;
import com.app.exception.ResourceNotFoundException;
import com.app.response.ApiResponse;

import java.util.List;
import java.util.Optional;

public interface BookService {
    ApiResponse addBook(BookDto bookDto,int categoryId) throws ResourceNotFoundException;
    List<BookDto> viewAllBooks(int pageNumber, int pageSize, String sortBy, String sortDir);
    Optional<Book> getBookById(int id);
    ApiResponse deleteBook(int id);
    ApiResponse updateBook( int bookId, int categoryId,BookDto bookDto) throws ResourceNotFoundException;
    List<BookDto> findBookByCategory(int categoryId);
}
