package com.Assignment.RecruitmentManagementSystem.security;

import com.Assignment.RecruitmentManagementSystem.model.User;
import com.Assignment.RecruitmentManagementSystem.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> ifExist=repository.findByEmail(email);

        if(ifExist.isEmpty()) throw new UsernameNotFoundException("User with username-"+email+" doesn't exists.");


        return ifExist.get();

    }
}
