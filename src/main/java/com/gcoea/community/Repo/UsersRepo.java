package com.gcoea.community.Repo;

import com.gcoea.community.Entity.Users;
import com.gcoea.community.Enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepo extends JpaRepository<Users, Long> {
    // Custom query method to find users by role (already exists)
    List<Users> findByRole(Role role);

    // New custom query method to find a user by username
    Optional<Users> findByUsername(String username);

    Optional<Users> findByEmail(String email);

}
