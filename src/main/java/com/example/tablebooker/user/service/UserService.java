package com.example.tablebooker.user.service;

import com.example.tablebooker.user.dto.UserInputDto;
import com.example.tablebooker.user.entity.User;

import java.util.Optional;

public interface UserService {
    User register(UserInputDto user);
    User getUserByEmail(String email);

    User updateUserEmail(Long userId, String email, String token);

    void deleteUser(Long userId);
}
