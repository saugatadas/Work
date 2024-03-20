package com.example.SplitWise.service;

import com.example.SplitWise.model.User;

public interface UserService {
    User signup(String name, String email, String password);
}
