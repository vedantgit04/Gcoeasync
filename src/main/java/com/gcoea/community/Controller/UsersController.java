package com.gcoea.community.Controller;

import com.gcoea.community.Model.Users;
import com.gcoea.community.Repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {


    @Autowired
    private UsersRepo usersRepo;

    @PostMapping
    public Users createUser(@RequestBody Users user) {
        return usersRepo.save(user);
    }

    @GetMapping
    public List<Users> getUsers() {
        return  usersRepo.findAll();
    }
}
