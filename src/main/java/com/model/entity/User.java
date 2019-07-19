package com.model.entity;

import com.model.entity.enums.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name="users")
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    @NotEmpty
    @Size(max = 25)
    private String firstName;
    @NotEmpty
    @Size(max = 25)
    private String lastName;
    @NotEmpty
    @Size(max = 40)
    private String login;
    @Size(min = 8)
    private String password;
    @Email
    @NotEmpty
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean activity;
    @Transient
    @NotEmpty
    private String passwordConfirmation;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Ticket> tickets;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
