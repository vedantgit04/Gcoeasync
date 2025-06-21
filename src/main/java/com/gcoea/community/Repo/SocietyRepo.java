package com.gcoea.community.Repo;

import com.gcoea.community.Entity.Society;
import com.gcoea.community.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SocietyRepo extends JpaRepository<Society, Long> {
    // Custom query method to find societies created by a specific user
    List<Society> findByCreatedBy(Users createdBy);
}
