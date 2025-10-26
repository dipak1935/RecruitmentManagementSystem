package com.Assignment.RecruitmentManagementSystem.dto;

import com.Assignment.RecruitmentManagementSystem.model.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {
    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Enter a valid email")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    private UserType userType;

    private String profileHeadline;
    private String address;
}

// (Name, Email, Password, UserType (Admin/Applicant), Profile Headline, Address).