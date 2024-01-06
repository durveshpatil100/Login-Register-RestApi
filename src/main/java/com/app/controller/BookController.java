package com.app.controller;

import com.app.dto.AppConstants;
import com.app.dto.BookDto;
import com.app.entity.Book;
import com.app.exception.ResourceNotFoundException;
import com.app.response.ApiResponse;
import com.app.service.BookService;
import com.app.service.CategoryService;
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

    private CategoryService categoryService;

    @PostMapping("/create/{categoryId}")
    public ResponseEntity<ApiResponse> addBook(@RequestBody BookDto bookDto, @PathVariable int categoryId) throws ResourceNotFoundException {
        ApiResponse bookResponse =  bookService.addBook(bookDto,categoryId);
       if(bookResponse.getStatus()==false)
       {
           return new ResponseEntity<ApiResponse>(bookResponse, HttpStatus.BAD_REQUEST);
       }
       else{
           return new ResponseEntity<ApiResponse>(bookResponse, HttpStatus.CREATED);
       }
    }

    @GetMapping
    public ResponseEntity<List<BookDto>>viewAllBooks(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER_STRING, required = false) int pageNumber,
                                                  @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE_STRING, required = false) int pageSize,
                                                  @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY_STRING, required = false) String sortBy,
                                                  @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR_STRING, required = false) String sortDir){
        List<BookDto> viewAll = bookService.viewAllBooks( pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<List<BookDto>>(viewAll,HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id){
        Optional<Book> book =bookService.getBookById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{bookId}/{categoryId}")
    public ResponseEntity<ApiResponse> updateBook(@PathVariable int bookId,@PathVariable int categoryId, @RequestBody BookDto bookDto) throws ResourceNotFoundException {

        ApiResponse bookResponse =  bookService.updateBook(bookId,categoryId,bookDto);
        if(bookResponse.getStatus()==false)
        {
            return new ResponseEntity<ApiResponse>(bookResponse, HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<ApiResponse>(bookResponse, HttpStatus.CREATED);
        }

    }

    @DeleteMapping("/{id}")
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

    //find book by category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<BookDto>> getBookByCategory(@PathVariable int categoryId){
        List<BookDto> bookByCategory = bookService.findBookByCategory(categoryId);
        return new ResponseEntity<List<BookDto>>(bookByCategory,HttpStatus.ACCEPTED);
    }


}
