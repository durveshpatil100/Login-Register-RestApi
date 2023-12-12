package com.app.service;

import com.app.dto.BookDto;
import com.app.entity.Book;
import com.app.repo.BookRepository;
import com.app.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;


    public ApiResponse addBook(BookDto bookDto) {
         Book book = new Book(
                 bookDto.getBookName(),
                 bookDto.getBookImage(),
                 bookDto.getAuthorName(),
                 bookDto.getBookPrice(),
                 bookDto.getBookDescription(),
                 bookDto.getPublishingDate()
         );

         bookRepository.save(book);
         return new ApiResponse("Book is saved", true);


    }


    @Override
    public List<Book> viewAllBooks() {
       return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public ApiResponse deleteBook(int id) {
        bookRepository.deleteById(id);
        return new ApiResponse("Book is deleted", true);
    }
}
