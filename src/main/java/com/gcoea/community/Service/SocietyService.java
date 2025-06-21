package com.gcoea.community.Service;

import com.gcoea.community.Entity.Society;
import com.gcoea.community.Entity.Users;
import com.gcoea.community.Repo.SocietyRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SocietyService {

    private final SocietyRepo societyRepo;

    public SocietyService(SocietyRepo societyRepo) {
        this.societyRepo = societyRepo;
    }

    // Fetch all societies (for student dashboard to browse societies)
    public List<Society> getAllSocieties() {
        return societyRepo.findAll();
    }

    // Fetch a society by ID (for joining societies or viewing details)
    public Optional<Society> getSocietyById(Long id) {
        return societyRepo.findById(id);
    }

    // Fetch societies created by a specific user (for society dashboard)
    public List<Society> getSocietiesByCreatedBy(Users user) {
        return societyRepo.findByCreatedBy(user);
    }

    // Create or update a society (for admin or society role)
    public Society saveSociety(Society society) {
        return societyRepo.save(society);
    }

    // Delete a society (for admin)
    public void deleteSociety(Long id) {
        societyRepo.deleteById(id);
    }
}