package com.example.tablebooker.utils;

import com.example.tablebooker.user.exception.InvalidUserRegisterException;
import com.example.tablebooker.user.repository.UserRepository;

public class UserValidationUtils {

    public static void checkDuplicateEmail(UserRepository userRepository, String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new InvalidUserRegisterException("이미 사용중인 이메일입니다.");
        }
    }

    public static void checkDuplicateMobileNumber(UserRepository userRepository, String mobileNumber) {
        if (userRepository.findByMobileNumber(mobileNumber).isPresent()) {
            throw new InvalidUserRegisterException("이미 사용중인 휴대폰 번호입니다.");
        }
    }
}
