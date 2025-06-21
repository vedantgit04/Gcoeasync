package com.gcoea.community.Repo;

import com.gcoea.community.Entity.Event;
import com.gcoea.community.Entity.EventRegistration;
import com.gcoea.community.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventRegistrationRepo extends JpaRepository<EventRegistration, Long> {
    // Custom query method to find event registrations for a specific user
    List<EventRegistration> findByUser(Users user);

    // Custom query method to find event registrations for a specific event
    List<EventRegistration> findByEvent(Event event);
}