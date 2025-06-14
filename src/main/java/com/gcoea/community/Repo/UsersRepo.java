package com.gcoea.community.Repo;

import com.gcoea.community.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users,Long> {
}
