package com.app.controller;

import com.app.dto.CartDto;
import com.app.dto.ItemRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    public ResponseEntity<CartDto> addToCart(@RequestBody ItemRequest itemRequest){
        return  null;
    }
}
