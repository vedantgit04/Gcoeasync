package com.gcoea.community.Config;

import com.gcoea.community.Service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Configure authentication
                .userDetailsService(userDetailsService)
                // Configure authorization (security rules)
                .authorizeHttpRequests(authorize -> authorize
                        // Allow everyone to access the login and logout pages
                        .requestMatchers("/login", "/logout").permitAll()
                        // Restrict /api/student/** to users with STUDENT role
                        .requestMatchers("/api/student/**").hasRole("STUDENT")
                        // Restrict /api/admin/** to users with ADMIN role
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        // Restrict /api/society/** to users with SOCIETY role
                        .requestMatchers("/api/society/**").hasRole("SOCIETY")
                        // Any other request requires authentication (login)
                        .anyRequest().authenticated()
                )
                // Enable form-based login
                .formLogin(form -> form
                        .loginPage("/login")  // Use the default login page at /login
                        .successHandler(authenticationSuccessHandler())  // Redirect based on role after login
                        .permitAll()  // Allow everyone to access the login page
                )
                // Enable logout
                .logout(logout -> logout
                        .logoutUrl("/logout")  // Logout endpoint
                        .logoutSuccessUrl("/login?logout")  // Redirect to login page after logout
                        .permitAll()  // Allow everyone to access the logout endpoint
                );

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            // Get the authenticated user
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            // Check the user's role and redirect accordingly
            if (userDetails.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_STUDENT"))) {
                response.sendRedirect("/api/student/dashboard");
            } else if (userDetails.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
                response.sendRedirect("/api/admin/dashboard");
            } else if (userDetails.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_SOCIETY"))) {
                response.sendRedirect("/api/society/dashboard");
            } else {
                response.sendRedirect("/login?error");  // Fallback if role is unknown
            }
        };
    }
}