package com.gcoea.community.Controller;

import com.gcoea.community.Entity.*;
import com.gcoea.community.Service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentDashboardController {

    private final SocietyService societyService;
    private final MembershipService membershipService;
    private final EventService eventService;
    private final EventRegistrationService eventRegistrationService;
    private final AnnouncementService announcementService;

    public StudentDashboardController(SocietyService societyService, MembershipService membershipService,
                                      EventService eventService, EventRegistrationService eventRegistrationService,
                                      AnnouncementService announcementService) {
        this.societyService = societyService;
        this.membershipService = membershipService;
        this.eventService = eventService;
        this.eventRegistrationService = eventRegistrationService;
        this.announcementService = announcementService;
    }

    // Dashboard landing endpoint (e.g., for a frontend to display a welcome message)
    @GetMapping("/dashboard")
    public ResponseEntity<String> getStudentDashboard(@AuthenticationPrincipal Users user) {
        return ResponseEntity.ok("Welcome to the Student Dashboard, " + user.getName() + "!");
    }

    // List all societies (for browsing)
    @GetMapping("/societies")
    public ResponseEntity<List<Society>> getAllSocieties() {
        return ResponseEntity.ok(societyService.getAllSocieties());
    }

    // Join a society
    @PostMapping("/societies/{societyId}/join")
    public ResponseEntity<Membership> joinSociety(@PathVariable Long societyId,
                                                  @AuthenticationPrincipal Users user) {
        Society society = societyService.getSocietyById(societyId)
                .orElseThrow(() -> new RuntimeException("Society not found with ID: " + societyId));
        Membership membership = membershipService.joinSociety(user, society);
        return ResponseEntity.ok(membership);
    }

    // List societies the student has joined
    @GetMapping("/my-societies")
    public ResponseEntity<List<Membership>> getMySocieties(@AuthenticationPrincipal Users user) {
        return ResponseEntity.ok(membershipService.getMembershipsByUser(user));
    }

    // List all events (for browsing)
    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    // Register for an event
    @PostMapping("/events/{eventId}/register")
    public ResponseEntity<EventRegistration> registerForEvent(@PathVariable Long eventId,
                                                              @AuthenticationPrincipal Users user) {
        Event event = eventService.getEventById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + eventId));
        EventRegistration registration = eventRegistrationService.registerForEvent(user, event);
        return ResponseEntity.ok(registration);
    }

    // List events the student has registered for
    @GetMapping("/my-events")
    public ResponseEntity<List<EventRegistration>> getMyEvents(@AuthenticationPrincipal Users user) {
        return ResponseEntity.ok(eventRegistrationService.getRegistrationsByUser(user));
    }

    // List all announcements
    @GetMapping("/announcements")
    public ResponseEntity<List<Announcement>> getAllAnnouncements() {
        return ResponseEntity.ok(announcementService.getAllAnnouncements());
    }
}
