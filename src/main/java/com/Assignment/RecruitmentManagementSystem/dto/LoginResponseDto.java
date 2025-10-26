package com.Assignment.RecruitmentManagementSystem.dto;

import com.Assignment.RecruitmentManagementSystem.model.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {

    private String token;
    private String message;
    private UserType userType;
}
