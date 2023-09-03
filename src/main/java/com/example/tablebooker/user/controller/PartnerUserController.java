package com.example.tablebooker.user.controller;

import com.example.tablebooker.user.dto.UserInputDTO;
import com.example.tablebooker.user.entity.User;
import com.example.tablebooker.user.service.PartnerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<User> registerPartner(
            @RequestBody UserInputDTO user
    ) {
        User registeredUser = partnerUserService.register(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

}
