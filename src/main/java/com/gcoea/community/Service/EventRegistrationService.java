package com.gcoea.community.Service;

import com.gcoea.community.Entity.Event;
import com.gcoea.community.Entity.EventRegistration;
import com.gcoea.community.Entity.Users;
import com.gcoea.community.Repo.EventRegistrationRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventRegistrationService {

    private final EventRegistrationRepo eventRegistrationRepo;

    public EventRegistrationService(EventRegistrationRepo eventRegistrationRepo) {
        this.eventRegistrationRepo = eventRegistrationRepo;
    }

    // Fetch event registrations for a specific user (for student dashboard)
    public List<EventRegistration> getRegistrationsByUser(Users user) {
        return eventRegistrationRepo.findByUser(user);
    }

    // Fetch event registrations for a specific event (for admin or society dashboard)
    public List<EventRegistration> getRegistrationsByEvent(Event event) {
        return eventRegistrationRepo.findByEvent(event);
    }

    // Register for an event (for student)
    public EventRegistration registerForEvent(Users user, Event event) {
        EventRegistration registration = new EventRegistration();
        registration.setUser(user);
        registration.setEvent(event);
        return eventRegistrationRepo.save(registration);
    }

    // Cancel event registration (for student)
    public void cancelRegistration(Long registrationId) {
        eventRegistrationRepo.deleteById(registrationId);
    }
}
