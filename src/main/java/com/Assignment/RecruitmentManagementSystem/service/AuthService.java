package com.Assignment.RecruitmentManagementSystem.service;

import com.Assignment.RecruitmentManagementSystem.dto.LoginRequestDto;
import com.Assignment.RecruitmentManagementSystem.dto.LoginResponseDto;
import com.Assignment.RecruitmentManagementSystem.dto.SignupRequestDto;
import com.Assignment.RecruitmentManagementSystem.dto.SignupResponseDto;
import com.Assignment.RecruitmentManagementSystem.exceptionHandling.exceptions.UserAlreadyExistException;
import com.Assignment.RecruitmentManagementSystem.model.User;
import com.Assignment.RecruitmentManagementSystem.model.UserType;
import com.Assignment.RecruitmentManagementSystem.repo.UserRepository;
import com.Assignment.RecruitmentManagementSystem.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;



//  POST /signup: Create a profile on the system (Name, Email, Password, UserType (Admin/Applicant), Profile Headline, Address).

    public SignupResponseDto signup(SignupRequestDto dto) {

        if(repository.findByEmail(dto.getEmail()).isPresent()){
            throw new UserAlreadyExistException("User already exists with email:"+dto.getEmail());
        }

        User user=new User();

        // (Name, Email, Password, UserType (Admin/Applicant), Profile Headline, Address).

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        user.setUserType(dto.getUserType() == null ? UserType.APPLICANT : dto.getUserType());


        user.setProfileHeadline(dto.getProfileHeadline());
        user.setAddress(dto.getAddress());
        user.setPassword(encoder.encode(dto.getPassword()));

        repository.save(user);

//        return new SignupResponseDto("User registered successfully", user.getEmail(), user.getUserType());
        return new SignupResponseDto("User registered successfully", user.getEmail());
    }

    public LoginResponseDto login(LoginRequestDto dto) {


        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(),dto.getPassword()));

        User user= (User) authentication.getPrincipal();


        String token=jwtUtil.generateAccessToken(user);

        return new LoginResponseDto(token,"Login Successful",user.getUserType());


    }

    public List<User> getAllUsers() {

        return repository.findAll();
    }
}
