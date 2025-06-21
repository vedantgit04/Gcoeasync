package com.gcoea.community.Repo;

import com.gcoea.community.Entity.Event;
import com.gcoea.community.Entity.Society;
import com.gcoea.community.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventRepo extends JpaRepository<Event, Long> {
    // Custom query method to find events by society
    List<Event> findBySociety(Society society);

    // Custom query method to find events created by a specific user
    List<Event> findByCreatedBy(Users createdBy);
}
