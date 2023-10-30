package com.app.controller;

import com.app.dto.LoginDto;
import com.app.dto.UserDto;
import com.app.entity.User;
import com.app.response.LoginResponse;
import com.app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody @Valid UserDto userDto)
    {
        return new ResponseEntity<>(userService.addUser(userDto),HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto)
    {
        LoginResponse loginResponse = userService.loginUser(loginDto);
        return ResponseEntity.ok(loginResponse);
    }
}
