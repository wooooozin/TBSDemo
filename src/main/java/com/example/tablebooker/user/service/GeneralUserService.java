package com.example.tablebooker.user.service;

import com.example.tablebooker.user.dto.UserInputDTO;
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
    public User register(UserInputDTO user) {
        return null;
    }

}
