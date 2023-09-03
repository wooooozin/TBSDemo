package com.example.tablebooker.user.service;

import com.example.tablebooker.user.dto.UserInputDto;
import com.example.tablebooker.user.entity.User;
import com.example.tablebooker.user.exception.InvalidUserRegisterException;
import com.example.tablebooker.user.exception.UserNotFoundException;
import com.example.tablebooker.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.tablebooker.user.type.UserType.GENERAL_USER;

@Service
@RequiredArgsConstructor
public class PartnerUserService implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User register(UserInputDto user) {
        if (user.getUserType() == GENERAL_USER) {
            throw new InvalidUserRegisterException("회원가입 정보가 올바르지 않습니다.");
        }

        checkDuplicateEmail(user.getEmail());
        checkDuplicateMobileNumber(user.getMobileNumber());

        User registerUser = convertDtoToEntity(user);
        return userRepository.save(registerUser);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));
    }

    private void checkDuplicateEmail(String email) {
        if(userRepository.findByEmail(email).isPresent()) {
            throw new InvalidUserRegisterException("이미 사용중인 이메일입니다.");
        }
    }

    private void checkDuplicateMobileNumber(String mobileNumber) {
        if(userRepository.findByMobileNumber(mobileNumber).isPresent()) {
            throw new InvalidUserRegisterException("이미 사용중인 휴대폰 번호입니다.");
        }
    }

    private User convertDtoToEntity(UserInputDto userInput) {
        return User.builder()
                .email(userInput.getEmail())
                .password(passwordEncoder.encode(userInput.getPassword()))
                .mobileNumber(userInput.getMobileNumber())
                .userType(userInput.getUserType())
                .build();
    }

}
