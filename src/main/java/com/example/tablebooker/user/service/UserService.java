package com.example.tablebooker.user.service;

import com.example.tablebooker.user.dto.UserInputDTO;
import com.example.tablebooker.user.entity.User;

import java.util.Optional;

public interface UserService {
    User register(UserInputDTO user);

}
