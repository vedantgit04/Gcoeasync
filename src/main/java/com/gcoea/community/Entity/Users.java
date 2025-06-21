package com.gcoea.community.Entity;

import com.gcoea.community.Enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import jakarta.persistence.Enumerated;  // Add this import
import jakarta.persistence.EnumType;   // Add this import


import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;  // New field for login
    private String password;  // New field for login

    @Enumerated(EnumType.STRING)  // Add this annotation
    private Role role;

    // Default constructor
    public Users() {
    }

    // Parameterized constructor
    public Users(Long id, String name, String username, String password, Role role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // UserDetails methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert the Role enum to a Spring Security authority (role)
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // We’re not using account expiration, so always true
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // We’re not using account locking, so always true
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // We’re not using credential expiration, so always true
    }

    @Override
    public boolean isEnabled() {
        return true;  // We’re not using enable/disable accounts, so always true
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='[PROTECTED]'" +
                ", role=" + role +
                '}';
    }
}
