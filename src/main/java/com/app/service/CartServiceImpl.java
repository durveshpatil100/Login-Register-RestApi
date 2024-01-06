package com.app.service;

import com.app.dto.CartDto;
import com.app.dto.ItemRequest;
import com.app.entity.Book;
import com.app.entity.Cart;
import com.app.entity.CartItem;
import com.app.entity.User;
import com.app.exception.ResourceNotFoundException;
import com.app.repo.BookRepository;
import com.app.repo.CartRepository;
import com.app.repo.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService{


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public CartDto addItem(ItemRequest item, String username) throws ResourceNotFoundException {
        int bookId = item.getBookId();
        int booksQuantity = item.getQuantity();

        User user = userRepository.findByEmail(username);
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("book not found"));

        CartItem cartItem = new CartItem();
        cartItem.setBook(book);
        cartItem.setQuantity(booksQuantity);
        double totalPrice = book.getBookPrice()* booksQuantity;
        cartItem.setTotalPrice(totalPrice);

        //getting cart from user
        Cart cart = user.getCart();
        if(cart ==null){
            Cart newCart = new Cart();

        }
        cartItem.setCart(cart);
        Set<CartItem> items = cart.getItems();

        //here we check item is available in cartItem or not
        //if cartItem is available then we increase quantity only else add new book in cartItem

        AtomicReference<Boolean> flag = new AtomicReference<>(); // because we cant set any variable inside lambda function

        Set<CartItem> newBook = items.stream().map(i -> {
            if (i.getBook().getBookId() == book.getBookId()) {
                i.setQuantity(booksQuantity);
                i.setTotalPrice(totalPrice);
                flag.set(true);
            }
            return i;
        }).collect(Collectors.toSet());

        if(flag.get()){
            items.clear();
            items.addAll(newBook);
        }else{
            cartItem.setCart(cart);
            items.add(cartItem);
        }

        Cart savedCart = cartRepository.save(cart);
        CartDto cartDto = modelMapper.map(savedCart, CartDto.class);

        return cartDto;
    }
}
