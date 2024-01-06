package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {

    private int cartItemId;

    private int quantity;

    private double totalPrice;

    private CartDto cartDto;

    private BookDto bookDto;
}
