package com.app.controller;

import com.app.dto.BookDto;
import com.app.entity.Book;
import com.app.response.ApiResponse;
import com.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<ApiResponse> addBook(@RequestBody BookDto bookDto){
        ApiResponse bookResponse =  bookService.addBook(bookDto);
       if(bookResponse.getStatus()==false)
       {
           return new ResponseEntity<ApiResponse>(bookResponse, HttpStatus.BAD_REQUEST);
       }
       else{
           return new ResponseEntity<ApiResponse>(bookResponse, HttpStatus.CREATED);
       }
    }

    @GetMapping
    public List<Book> viewAllBooks(){
        List<Book> viewAll = bookService.viewAllBooks();
        return viewAll;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id){
        Optional<Book> book =bookService.getBookById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateBook(@PathVariable int id, @RequestBody BookDto bookDto){
        if(!bookService.getBookById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        bookDto.setBookName(bookDto.getBookName());
        bookDto.setBookDescription(bookDto.getBookDescription());
        bookDto.setBookPrice(bookDto.getBookPrice());
        bookDto.setAuthorName(bookDto.getAuthorName());
        bookDto.setPublishingDate(bookDto.getPublishingDate());
        bookDto.setBookImage(bookDto.getBookImage());

        ApiResponse bookResponse =  bookService.addBook(bookDto);
         return ResponseEntity.ok(bookResponse);
    }

    public ResponseEntity<ApiResponse> deleteBook(@PathVariable int id){
        if(!bookService.getBookById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        ApiResponse bookResponse = bookService.deleteBook(id);
        if(bookResponse.getStatus()==false)
        {
            return new ResponseEntity<ApiResponse>(bookResponse, HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<ApiResponse>(bookResponse, HttpStatus.OK);
        }
    }


}
