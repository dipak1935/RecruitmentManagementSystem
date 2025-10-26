package com.Assignment.RecruitmentManagementSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User applicant;

    private String resumeFileAddress;
    private String education;
    private String experience;
    private String skills;
    private String name;
    private String email;
    private String phoneNumber;


}
