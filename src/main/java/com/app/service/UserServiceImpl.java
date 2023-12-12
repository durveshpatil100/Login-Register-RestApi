package com.app.service;

import com.app.dto.LoginDto;
import com.app.dto.UserDto;
import com.app.entity.User;
import com.app.repo.UserRepository;
import com.app.response.LoginResponse;
import com.app.response.SignUpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public SignUpResponse addUser(UserDto userDto) {
        String existingUSerEmail = userDto.getEmail();
        if (userRepository.findByEmail(existingUSerEmail) != null) {
            return new SignUpResponse("Email already exist try with another email", false);
        } else {
            User user = new User(

                    userDto.getEmail(),
                    this.passwordEncoder.encode(userDto.getPassword()),
                    userDto.getFirstName(),
                    userDto.getLastName(),
                    userDto.getLocation(),
                    userDto.getMobile(),
                    userDto.getAge()

            );
            userRepository.save(user);
            return new SignUpResponse("User is saved in database", true);
        }


    }

    @Override
    public LoginResponse loginUser(LoginDto loginDto) {


        User existingUser = userRepository.findByEmail(loginDto.getEmail());
        if (existingUser != null) {
            String password = loginDto.getPassword();
            String encodePassword = existingUser.getPassword();

            Boolean isPwRight = passwordEncoder.matches(password, encodePassword);
            if (isPwRight) {
                Optional<User> user = userRepository.findUserByEmailAndPassword(loginDto.getEmail(), encodePassword);
                if (user.isPresent()) {
                    return new LoginResponse("Login Success", true, existingUser.getFirstName(), existingUser.getLastName(), existingUser.getRole());
                } else {
                    return new LoginResponse("Login failed", false);
                }
            } else {
                return new LoginResponse("Password Not Matched", false);
            }
        } else {
            return new LoginResponse("Email does not exists", false);
        }

        // by java 8 features
//        return Optional.ofNullable(userRepository.findByEmail(loginDto.getEmail())).map(existingUser -> {
//                    String password = loginDto.getPassword();
//                    String encodePassword = existingUser.getPassword();
//
//                    Boolean isPwRight = passwordEncoder.matches(password, encodePassword);
//
//                    return isPwRight && userRepository.findUserByEmailAndPassword(loginDto.getEmail(), encodePassword).isPresent()
//                            ? new LoginResponse("Login Success", true,existingUser.getFirstName(),existingUser.getLastName())
//                            : new LoginResponse(isPwRight ? "Login failed" : "Password Not Matched", false);
//                })
//                .orElse(new LoginResponse("Email does not exist", false));
//    }
    }
}





