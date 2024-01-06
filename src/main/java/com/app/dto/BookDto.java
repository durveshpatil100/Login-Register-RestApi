package com.app.dto;

import com.app.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private String bookName;

    private String authorName;

    private Date publishingDate;

    private double bookPrice;

    private String bookImage;

    private String bookDescription;

    private CategoryDto category;
}
