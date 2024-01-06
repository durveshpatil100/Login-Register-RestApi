package com.app.repo;

import com.app.entity.Book;
import com.app.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Integer> {

    List<Book> findByCategory(Optional<Category> category);
}
