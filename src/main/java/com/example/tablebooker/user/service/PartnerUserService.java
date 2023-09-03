package com.example.tablebooker.user.service;

import com.example.tablebooker.user.dto.UserInputDto;
import com.example.tablebooker.user.entity.User;
import com.example.tablebooker.user.exception.InvalidUserRegisterException;
import com.example.tablebooker.user.exception.UnauthorizedAccessException;
import com.example.tablebooker.user.exception.UserNotFoundException;
import com.example.tablebooker.user.repository.UserRepository;
import com.example.tablebooker.utils.JWTUtils;
import com.example.tablebooker.utils.UserValidationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.tablebooker.user.type.UserType.GENERAL_USER;

@Slf4j
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

        UserValidationUtils.checkDuplicateEmail(userRepository, user.getEmail());
        UserValidationUtils.checkDuplicateMobileNumber(userRepository, user.getMobileNumber());

        User registerUser = convertDtoToEntity(user);
        return userRepository.save(registerUser);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));
    }

    @Override
    public User updateUserEmail(Long userId, String email, String token) {
        User userToUpdate = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("사용자 정보가 없습니다."));

        UserValidationUtils.checkDuplicateEmail(userRepository, email);
        String generatedToken;

        if (token.startsWith("Bearer")) {
            generatedToken = token.substring(7).trim();
        } else {
            throw new UnauthorizedAccessException("유효하지 않은 토큰 형식입니다.");
        }
        String issuer = JWTUtils.getIssuer(generatedToken);

        if (!issuer.equals(userToUpdate.getEmail())) {
            throw new UnauthorizedAccessException("권한이 없습니다.");
        }
        userToUpdate.setEmail(email);
        return userRepository.save(userToUpdate);
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
