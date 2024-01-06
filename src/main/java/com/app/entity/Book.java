package com.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookId;

    private String bookName;

    private String authorName;

    private Date publishingDate;

    private double bookPrice;

    private String bookImage;

    private String bookDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;


    public Book(String bookName, String bookImage, String authorName, double bookPrice, String bookDescription, Date publishingDate, Category category) {
    }
}
