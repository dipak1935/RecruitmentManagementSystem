package com.Assignment.RecruitmentManagementSystem.controller;

import com.Assignment.RecruitmentManagementSystem.dto.LoginRequestDto;
import com.Assignment.RecruitmentManagementSystem.dto.LoginResponseDto;
import com.Assignment.RecruitmentManagementSystem.dto.SignupRequestDto;
import com.Assignment.RecruitmentManagementSystem.dto.SignupResponseDto;
import com.Assignment.RecruitmentManagementSystem.model.User;
import com.Assignment.RecruitmentManagementSystem.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;


    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody @Valid SignupRequestDto dto){

        return ResponseEntity.ok(service.signup(dto));
    }


//    POST /login: Authenticate users and return a JWT token upon successful validation.


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto dto){
        return ResponseEntity.ok(service.login(dto));
    }


//    @GetMapping("/getAllUsers")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<List<User>> getAllUsers(){
//        return ResponseEntity.ok(service.getAllUsers());
//    }



}
