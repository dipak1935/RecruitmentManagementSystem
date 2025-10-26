package com.Assignment.RecruitmentManagementSystem.repo;

import com.Assignment.RecruitmentManagementSystem.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Integer> {
}