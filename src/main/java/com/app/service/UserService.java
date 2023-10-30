package com.app.service;

import com.app.dto.LoginDto;
import com.app.dto.UserDto;
import com.app.entity.User;
import com.app.response.LoginResponse;

import java.util.List;

public interface UserService {
   User addUser(UserDto userDto);

   LoginResponse loginUser(LoginDto loginDto);


}
