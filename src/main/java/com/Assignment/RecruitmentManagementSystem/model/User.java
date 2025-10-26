package com.Assignment.RecruitmentManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_tbl")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    @Email(message = "fill proper email")
    private String email;
    private String address;

    @Enumerated(EnumType.STRING)
    private UserType userType=UserType.APPLICANT;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String profileHeadline;


    @OneToMany(mappedBy = "postedBy",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Job> jobs;


    @OneToOne(mappedBy = "applicant",cascade = CascadeType.ALL)
    private Profile profile;


    // Helper method to set profile and maintain bidirectional link
    public void setProfile(Profile profile) {
        this.profile = profile;
        if(profile != null) {
            profile.setApplicant(this);
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(()->"ROLE_"+userType.name());
        return authorities;

    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
