package com.Assignment.RecruitmentManagementSystem.security;

import com.Assignment.RecruitmentManagementSystem.model.User;
import com.Assignment.RecruitmentManagementSystem.repo.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserRepository repository;
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("incoming request: {}", request.getRequestURI());

        final String requestTokenHeader = request.getHeader("Authorization");

        if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {

            String token=requestTokenHeader.substring(7);

//        String token = requestTokenHeader.split("Bearer ")[1];

            String email = jwtUtil.getEmailFromToken(token);


            if(email != null && SecurityContextHolder.getContext().getAuthentication()==null){

                User user=repository.findByEmail(email).orElseThrow();

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new
                        UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        } catch (Exception e) {

            log.warn("JWT processing failed: {}", e.getMessage());
        }


        filterChain.doFilter(request,response);

    }

}
