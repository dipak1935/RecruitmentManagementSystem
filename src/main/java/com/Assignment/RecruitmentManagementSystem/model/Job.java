package com.Assignment.RecruitmentManagementSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private LocalDateTime postedOn;
    private int totalApplications;
    private String companyName;

    @ManyToOne
    @JoinColumn(name = "posted_by_id")
    private User postedBy;
}
