package com.example.tablebooker.user.service;

import com.example.tablebooker.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartnerUserService implements UserService {

    private final UserRepository userRepository;


}
