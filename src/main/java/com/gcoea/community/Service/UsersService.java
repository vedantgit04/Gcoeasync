package com.gcoea.community.Service;

import com.gcoea.community.Entity.Users;
import com.gcoea.community.Enums.Role;
import com.gcoea.community.Repo.UsersRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepo usersRepo;

    public UsersService(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    // Fetch all users (for admin dashboard)
    public List<Users> getAllUsers() {
        return usersRepo.findAll();
    }

    // Fetch users by role (for admin to filter users by role)
    public List<Users> getUsersByRole(Role role) {
        return usersRepo.findByRole(role);
    }

    // Fetch a user by ID (for role simulation and other operations)
    public Optional<Users> getUserById(Long id) {
        return usersRepo.findById(id);
    }

    // Save or update a user (for admin user management)
    public Users saveUser(Users user) {
        return usersRepo.save(user);
    }

    // Delete a user (for admin user management)
    public void deleteUser(Long id) {
        usersRepo.deleteById(id);
    }
}