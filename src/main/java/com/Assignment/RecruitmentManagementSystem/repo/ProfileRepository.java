package com.Assignment.RecruitmentManagementSystem.repo;

import com.Assignment.RecruitmentManagementSystem.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
}