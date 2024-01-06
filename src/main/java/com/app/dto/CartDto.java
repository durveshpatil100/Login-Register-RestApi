package com.app.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private int cartId;

    private Set<CartItemDto> itemsDto = new HashSet<>();

    private UserDto userDto;
}
