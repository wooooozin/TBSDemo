package com.example.tablebooker.user.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.tablebooker.exception.ErrorResponse;
import com.example.tablebooker.user.dto.UserInputDto;
import com.example.tablebooker.user.dto.UserLoginDto;
import com.example.tablebooker.user.dto.UserLoginToken;
import com.example.tablebooker.user.entity.User;
import com.example.tablebooker.user.exception.PasswordNotMatchException;
import com.example.tablebooker.user.service.PartnerUserService;
import com.example.tablebooker.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/partner")
public class PartnerUserController {
    private final PartnerUserService partnerUserService;

    @Autowired
    public PartnerUserController(PartnerUserService partnerUserService) {
        this.partnerUserService = partnerUserService;
    }

    // 파트너 회원가입 api
    @PostMapping("/register")
    public ResponseEntity<?> registerPartner(
            @RequestBody @Valid UserInputDto user,
            Errors errors
    ) {
        if (errors.hasErrors()) {
            List<ErrorResponse> responseErrors = getResponseErrors(errors);
            return new ResponseEntity<>(responseErrors, HttpStatus.BAD_REQUEST);
        }

        User registeredUser = partnerUserService.register(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    // 파트너 로그인 api 및 jwt 발급 api
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody @Valid UserLoginDto userLoginDto,
            Errors errors
    ) {
        if (errors.hasErrors()) {
            List<ErrorResponse> responseErrors = getResponseErrors(errors);
            return new ResponseEntity<>(responseErrors, HttpStatus.BAD_REQUEST);
        }

        User user = partnerUserService.getUserByEmail(userLoginDto.getEmail());

        if (!PasswordUtils.equalPassword(userLoginDto.getPassword(), user.getPassword())) {
            throw new PasswordNotMatchException("비밀번호가 일치하지 않습니다.");
        }

        String token = generateToken(user);
        return ResponseEntity.ok().body(
                UserLoginToken.builder()
                        .id(user.getId())
                        .token(token)
                        .build()
        );
    }

    // 파트너 이메일 수정 api
    @PutMapping("/{userId}/email")
    public ResponseEntity<?> updateUserEamil(
            @PathVariable Long userId,
            @RequestBody UserInputDto userInputDto
    ) {
        User updatedUser = partnerUserService.updateUserEmail(userId, userInputDto.getEmail());
        return ResponseEntity.ok(updatedUser);
    }


    // 파트너 전화번호 수정 api



    private List<ErrorResponse> getResponseErrors(Errors errors) {
        return errors.getAllErrors().stream()
                .map(e -> new ErrorResponse(e.getDefaultMessage(), HttpStatus.BAD_REQUEST.value()))
                .collect(Collectors.toList());
    }

    private String generateToken(User user) {
        return JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + 864_000_000)) // 10일
                .withClaim("user_id", user.getId())
                .withIssuer(user.getEmail())
                .sign(Algorithm.HMAC512("booker".getBytes()));
    }

}
