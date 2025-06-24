package com.gcoea.community.Controller;

import com.gcoea.community.Entity.*;
import com.gcoea.community.Service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/society")
public class SocietyDashboardController {

    private final SocietyService societyService;
    private final EventService eventService;
    private final MembershipService membershipService;
    private final AnnouncementService announcementService;

    public SocietyDashboardController(SocietyService societyService, EventService eventService,
                                      MembershipService membershipService, AnnouncementService announcementService) {
        this.societyService = societyService;
        this.eventService = eventService;
        this.membershipService = membershipService;
        this.announcementService = announcementService;
    }

    // Dashboard landing endpoint
    @GetMapping("/dashboard")
    public ResponseEntity<String> getSocietyDashboard(@AuthenticationPrincipal Users user) {
        return ResponseEntity.ok("Welcome to the Society Dashboard, " + user.getName() + "!");
    }

    // List societies managed by this society user
    @GetMapping("/my-societies")
    public ResponseEntity<List<Society>> getMySocieties(@AuthenticationPrincipal Users user) {
        return ResponseEntity.ok(societyService.getSocietiesByCreatedBy(user));
    }

    // Create a new event for a society
    @PostMapping("/events")
    public ResponseEntity<Event> createEvent(@RequestBody Event event,
                                             @AuthenticationPrincipal Users user) {
        Society society = societyService.getSocietiesByCreatedBy(user).stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Society not found for this user"));
        event.setSociety(society);
        event.setCreatedBy(user);
        if (event.getDate() == null) {
            event.setDate(LocalDateTime.now());
        }
        return ResponseEntity.ok(eventService.saveEvent(event));
    }

    // List events for the society
    @GetMapping("/events")
    public ResponseEntity<List<Event>> getSocietyEvents(@AuthenticationPrincipal Users user) {
        List<Society> societies = societyService.getSocietiesByCreatedBy(user);
        if (societies.isEmpty()) {
            return ResponseEntity.ok(List.of()); // Return empty list instead of throwing
        }
        Society society = societies.get(0);
        return ResponseEntity.ok(eventService.getEventsBySociety(society));
    }
    // List members of the society
    @GetMapping("/members")
    public ResponseEntity<List<Membership>> getSocietyMembers(@AuthenticationPrincipal Users user) {
        Society society = societyService.getSocietiesByCreatedBy(user).stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Society not found for this user"));
        return ResponseEntity.ok(membershipService.getMembershipsBySociety(society));
    }

    // Create a new announcement for the society
    @PostMapping("/announcements")
    public ResponseEntity<Announcement> createAnnouncement(@RequestBody Announcement announcement,
                                                           @AuthenticationPrincipal Users user) {
        Society society = societyService.getSocietiesByCreatedBy(user).stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Society not found for this user"));
        announcement.setSociety(society);
        announcement.setCreatedBy(user);
        return ResponseEntity.ok(announcementService.saveAnnouncement(announcement));
    }

    // List announcements for the society
    @GetMapping("/announcements")
    public ResponseEntity<List<Announcement>> getSocietyAnnouncements(@AuthenticationPrincipal Users user) {
        Society society = societyService.getSocietiesByCreatedBy(user).stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Society not found for this user"));
        return ResponseEntity.ok(announcementService.getAnnouncementsBySociety(society));
    }
}