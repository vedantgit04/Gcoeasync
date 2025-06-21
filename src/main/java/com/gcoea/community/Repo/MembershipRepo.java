package com.gcoea.community.Repo;

import com.gcoea.community.Entity.Membership;
import com.gcoea.community.Entity.Society;
import com.gcoea.community.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MembershipRepo extends JpaRepository<Membership, Long> {
    // Custom query method to find memberships for a specific user
    List<Membership> findByUser(Users user);

    // Custom query method to find memberships for a specific society
    List<Membership> findBySociety(Society society);
}
