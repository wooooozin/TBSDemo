package com.example.tablebooker.user.service;

import com.example.tablebooker.user.dto.UserInputDto;
import com.example.tablebooker.user.entity.User;
import com.example.tablebooker.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GeneralUserService implements UserService {

    private final UserRepository userRepository;


    @Override
    public User register(UserInputDto user) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

}
