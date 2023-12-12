package com.app.controller;

import com.app.dto.LoginDto;
import com.app.dto.UserDto;
import com.app.repo.UserRepository;
import com.app.response.LoginResponse;
import com.app.response.SignUpResponse;
import com.app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/save")
    public ResponseEntity<SignUpResponse> saveUser(@RequestBody @Valid UserDto userDto)  {

//        String existingUSerEmail = userDto.getEmail();
//        if (userRepository.findByEmail(existingUSerEmail) != null) {
//           // throw new DuplicateEmailException("Email address already exists in the database: " + existingUSerEmail);
//
//        return new ResponseEntity<SignUpResponse>(signupResponse,HttpStatus.BAD_REQUEST);
//        }else{
//            userService.addUser(userDto);
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        }
        System.out.println("call to API");
        SignUpResponse signUpResponse = userService.addUser(userDto);
          if (signUpResponse.getStatus()==false){
              return new ResponseEntity<SignUpResponse>(signUpResponse,HttpStatus.BAD_REQUEST);
          }else{
              return new ResponseEntity<SignUpResponse>(signUpResponse,HttpStatus.CREATED);
          }


    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginDto loginDto)
    {
        LoginResponse loginResponse = userService.loginUser(loginDto);
        if(loginResponse.getMessage()=="Login Success"){
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }

    }


}
