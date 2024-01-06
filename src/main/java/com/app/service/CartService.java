package com.app.service;

import com.app.dto.CartDto;
import com.app.dto.ItemRequest;
import com.app.exception.ResourceNotFoundException;

public interface CartService {

    CartDto addItem(ItemRequest item, String username) throws ResourceNotFoundException;
}
