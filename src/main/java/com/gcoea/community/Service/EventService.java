package com.gcoea.community.Service;

import com.gcoea.community.Entity.Event;
import com.gcoea.community.Entity.Society;
import com.gcoea.community.Entity.Users;
import com.gcoea.community.Repo.EventRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepo eventRepo;

    public EventService(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }

    // Fetch all events (for student dashboard to view events)
    public List<Event> getAllEvents() {
        return eventRepo.findAll();
    }

    // Fetch an event by ID (for registering for events)
    public Optional<Event> getEventById(Long id) {
        return eventRepo.findById(id);
    }

    // Fetch events for a specific society (for society dashboard)
    public List<Event> getEventsBySociety(Society society) {
        return eventRepo.findBySociety(society);
    }

    // Fetch events created by a specific user (for admin or society dashboard)
    public List<Event> getEventsByCreatedBy(Users user) {
        return eventRepo.findByCreatedBy(user);
    }

    // Create or update an event (for admin or society)
    public Event saveEvent(Event event) {
        return eventRepo.save(event);
    }

    // Delete an event (for admin or society)
    public void deleteEvent(Long id) {
        eventRepo.deleteById(id);
    }
}
