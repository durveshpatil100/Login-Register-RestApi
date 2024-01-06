package com.app.service;

import com.app.dto.BookDto;
import com.app.dto.CategoryDto;
import com.app.entity.Book;
import com.app.entity.Category;
import com.app.exception.ResourceNotFoundException;
import com.app.repo.BookRepository;
import com.app.repo.CategoryRepo;
import com.app.response.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ApiResponse addBook(BookDto bookDto, int categoryId) throws ResourceNotFoundException {
      //fetch category
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("CategoryId not found"));
//        Book book = new Book(
//                 bookDto.getBookName(),
//                 bookDto.getBookImage(),
//                 bookDto.getAuthorName(),
//                 bookDto.getBookPrice(),
//                 bookDto.getBookDescription(),
//                 bookDto.getPublishingDate(),
//                 bookDto.getCategory()
//         );

         Book book = this.modelMapper.map(bookDto, Book.class);
         book.setCategory(category);
         bookRepository.save(book);
         return new ApiResponse("Book is saved", true);


    }




    @Override
    public List<BookDto> viewAllBooks(int pageNumber, int pageSize, String sortBy, String sortDir) {
        //sorting
        Sort sort = null;
        if(sortDir.trim().toLowerCase().equals("asc")){
            sort = Sort.by(sortBy).ascending();
            System.out.println(sort);
        }else{
          sort =  Sort.by(sortBy).descending();
            System.out.println(sort);
        }

        Pageable pageable = PageRequest.of(pageNumber,pageSize, sort);
        Page<Book> page = this.bookRepository.findAll(pageable);
        List<Book> pageBook = page.getContent();
        List<Book> book =pageBook.stream().filter(b -> b.getBookPrice() > 50.0)
                .collect(Collectors.toList());

        List<Book> findAll = bookRepository.findAll();
        List<BookDto> findAllDto = findAll.stream().map(b -> modelMapper.map(b, BookDto.class))
                .collect(Collectors.toList());
       return findAllDto;
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

    @Override
    public ApiResponse updateBook(int bookId, int categoryId, BookDto bookDto) throws ResourceNotFoundException {

        Book existingBook = bookRepository.findById(bookId).orElseThrow(()-> new ResourceNotFoundException("cant find bookId"));
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("CategoryId is not present"));

//        existingBook.setBookName(bookDto.getBookName());
//        existingBook.setBookImage(bookDto.getBookImage());
//        existingBook.setBookPrice(bookDto.getBookPrice());
//        existingBook.setBookDescription(bookDto.getBookDescription());
//        existingBook.setAuthorName(bookDto.getAuthorName());
//        existingBook.setPublishingDate(bookDto.getPublishingDate());

          modelMapper.map(bookDto, existingBook);
          existingBook.setCategory(category);

        bookRepository.save(existingBook);

        return new ApiResponse("Book is updated",true);


    }

    @Override
    public List<BookDto> findBookByCategory(int categoryId) {
        Optional<Category> category = categoryRepo.findById(categoryId);
        List<Book> books = bookRepository.findByCategory(category);
        List<BookDto> collect = books.stream().map(b -> modelMapper.map(b, BookDto.class)).collect(Collectors.toList());
        return collect;
    }

    public BookDto convertToBookDto(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setBookImage(book.getBookImage());
        bookDto.setBookName(book.getBookName());
        bookDto.setBookPrice(book.getBookPrice());
        bookDto.setBookDescription(book.getBookDescription());
        bookDto.setPublishingDate(book.getPublishingDate());
        bookDto.setAuthorName(book.getAuthorName());

        //change category to categoryDto
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName(book.getCategory().getCategoryName());

        bookDto.setCategory(categoryDto);

        return bookDto;
    }
}
