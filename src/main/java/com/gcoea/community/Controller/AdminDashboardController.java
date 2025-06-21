package com.gcoea.community.Controller;

import com.gcoea.community.Entity.*;
import com.gcoea.community.Enums.Role;
import com.gcoea.community.Service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminDashboardController {

    private final UsersService usersService;
    private final SocietyService societyService;
    private final EventService eventService;
    private final AnnouncementService announcementService;

    public AdminDashboardController(UsersService usersService, SocietyService societyService,
                                    EventService eventService, AnnouncementService announcementService) {
        this.usersService = usersService;
        this.societyService = societyService;
        this.eventService = eventService;
        this.announcementService = announcementService;
    }

    // Dashboard landing endpoint
    @GetMapping("/dashboard")
    public ResponseEntity<String> getAdminDashboard(@AuthenticationPrincipal Users user) {
        return ResponseEntity.ok("Welcome to the Admin Dashboard, " + user.getName() + "!");
    }

    // List all users
    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    // Create a new user
    @PostMapping("/users")
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        return ResponseEntity.ok(usersService.saveUser(user));
    }

    // Delete a user
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        usersService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

    // List all societies
    @GetMapping("/societies")
    public ResponseEntity<List<Society>> getAllSocieties() {
        return ResponseEntity.ok(societyService.getAllSocieties());
    }

    // Create a new society
    @PostMapping("/societies")
    public ResponseEntity<Society> createSociety(@RequestBody Society society,
                                                 @AuthenticationPrincipal Users user) {
        society.setCreatedBy(user);
        return ResponseEntity.ok(societyService.saveSociety(society));
    }

    // Delete a society
    @DeleteMapping("/societies/{societyId}")
    public ResponseEntity<String> deleteSociety(@PathVariable Long societyId) {
        societyService.deleteSociety(societyId);
        return ResponseEntity.ok("Society deleted successfully");
    }

    // List all events
    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    // Create a new event
    @PostMapping("/events")
    public ResponseEntity<Event> createEvent(@RequestBody Event event,
                                             @AuthenticationPrincipal Users user) {
        event.setCreatedBy(user);
        if (event.getDate() == null) {
            event.setDate(LocalDateTime.now());
        }
        return ResponseEntity.ok(eventService.saveEvent(event));
    }

    // Delete an event
    @DeleteMapping("/events/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.ok("Event deleted successfully");
    }

    // List all announcements
    @GetMapping("/announcements")
    public ResponseEntity<List<Announcement>> getAllAnnouncements() {
        return ResponseEntity.ok(announcementService.getAllAnnouncements());
    }

    // Create a new announcement
    @PostMapping("/announcements")
    public ResponseEntity<Announcement> createAnnouncement(@RequestBody Announcement announcement,
                                                           @AuthenticationPrincipal Users user) {
        announcement.setCreatedBy(user);
        return ResponseEntity.ok(announcementService.saveAnnouncement(announcement));
    }

    // Delete an announcement
    @DeleteMapping("/announcements/{announcementId}")
    public ResponseEntity<String> deleteAnnouncement(@PathVariable Long announcementId) {
        announcementService.deleteAnnouncement(announcementId);
        return ResponseEntity.ok("Announcement deleted successfully");
    }
}
