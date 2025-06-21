package com.gcoea.community.Repo;

import com.gcoea.community.Entity.Announcement;
import com.gcoea.community.Entity.Society;
import com.gcoea.community.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnnouncementRepo extends JpaRepository<Announcement, Long> {
    // Custom query method to find announcements created by a specific user
    List<Announcement> findByCreatedBy(Users createdBy);

    // Custom query method to find announcements for a specific society
    List<Announcement> findBySociety(Society society);
}
