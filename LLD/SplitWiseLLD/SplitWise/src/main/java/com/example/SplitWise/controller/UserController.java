package com.example.SplitWise.controller;

import com.example.SplitWise.dto.UserRegistrationRequestDTO;
import com.example.SplitWise.exception.UserRegistrationInvalidDataException;
import com.example.SplitWise.mapper.EntityDTOMapper;
import com.example.SplitWise.service.UserService;
import com.example.SplitWise.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody UserRegistrationRequestDTO userRegistrationRequestDTO) {
        validateUserRegistrationRequestDTO(userRegistrationRequestDTO);
        User savedUser = userService.signup(userRegistrationRequestDTO.getName(),
                userRegistrationRequestDTO.getEmail(), userRegistrationRequestDTO.getPassword());
        return ResponseEntity.ok(EntityDTOMapper.toDTO(savedUser));
    }

    private void validateUserRegistrationRequestDTO(UserRegistrationRequestDTO userRegistrationRequestDTO) {
        if (userRegistrationRequestDTO.getEmail().isEmpty() ||
                userRegistrationRequestDTO.getPassword().isEmpty() ||
                userRegistrationRequestDTO.getName().isEmpty()) {
            throw new UserRegistrationInvalidDataException("invalid registration");
        }
    }

}
