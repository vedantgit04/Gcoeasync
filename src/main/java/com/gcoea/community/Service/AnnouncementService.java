package com.gcoea.community.Service;

import com.gcoea.community.Entity.Announcement;
import com.gcoea.community.Entity.Society;
import com.gcoea.community.Entity.Users;
import com.gcoea.community.Repo.AnnouncementRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementService {

    private final AnnouncementRepo announcementRepo;

    public AnnouncementService(AnnouncementRepo announcementRepo) {
        this.announcementRepo = announcementRepo;
    }

    // Fetch all announcements (for student dashboard)
    public List<Announcement> getAllAnnouncements() {
        return announcementRepo.findAll();
    }

    // Fetch announcements for a specific society (for student or society dashboard)
    public List<Announcement> getAnnouncementsBySociety(Society society) {
        return announcementRepo.findBySociety(society);
    }

    // Fetch announcements created by a specific user (for admin or society dashboard)
    public List<Announcement> getAnnouncementsByCreatedBy(Users user) {
        return announcementRepo.findByCreatedBy(user);
    }

    // Create or update an announcement (for admin or society)
    public Announcement saveAnnouncement(Announcement announcement) {
        return announcementRepo.save(announcement);
    }

    // Delete an announcement (for admin or society)
    public void deleteAnnouncement(Long id) {
        announcementRepo.deleteById(id);
    }
}