package com.app.service;

import com.app.dto.LoginDto;
import com.app.dto.UserDto;
import com.app.response.LoginResponse;
import com.app.response.SignUpResponse;

public interface UserService {
   SignUpResponse addUser(UserDto userDto);

   LoginResponse loginUser(LoginDto loginDto);


}
