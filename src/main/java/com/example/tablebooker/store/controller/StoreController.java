package com.example.tablebooker.store.controller;

import com.example.tablebooker.exception.ErrorResponse;
import com.example.tablebooker.store.dto.StoreInputDto;
import com.example.tablebooker.store.entity.Store;
import com.example.tablebooker.store.serivce.StoreService;
import com.example.tablebooker.user.exception.UnauthorizedAccessException;
import com.example.tablebooker.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    // 상점 등록 api
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody @Valid StoreInputDto storeInputDto,
            @RequestHeader("Authorization") String token,
            Errors errors
    ) {
        if (errors.hasErrors()) {
            List<ErrorResponse> responseErrors = getResponseErrors(errors);
            return new ResponseEntity<>(responseErrors, HttpStatus.BAD_REQUEST);
        }
        String jwtToken = extractTokenFromHeader(token);

        Long userId = getUserIdFromToken(jwtToken);

        Store store = storeService.createStore(storeInputDto, userId);
        return ResponseEntity.ok().body(store);
    }

    private List<ErrorResponse> getResponseErrors(Errors errors) {
        return errors.getAllErrors().stream()
                .map(e -> new ErrorResponse(e.getDefaultMessage(), HttpStatus.BAD_REQUEST.value()))
                .collect(Collectors.toList());
    }

    private Long getUserIdFromToken(String token) {
        Long userId = JWTUtils.getId(token);
        return userId;
    }

    private String extractTokenFromHeader(String header) {
        // Authorization 헤더에서 토큰을 추출하는 로직 구현 (Bearer 토큰 형식을 가정)
        if (header.startsWith("Bearer")) {
            return header.substring(7).trim();
        } else {
            throw new UnauthorizedAccessException("유효하지 않은 토큰 형식입니다.");
        }
    }

}
