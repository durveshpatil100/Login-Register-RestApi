package com.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int categoryId;

    private String categoryName;

    @OneToMany(mappedBy = "category" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Book> book;

    public Category(String categoryName) {
    }
}
