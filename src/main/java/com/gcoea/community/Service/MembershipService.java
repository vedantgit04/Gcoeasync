package com.gcoea.community.Service;

import com.gcoea.community.Entity.Membership;
import com.gcoea.community.Entity.Society;
import com.gcoea.community.Entity.Users;
import com.gcoea.community.Repo.MembershipRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipService {

    private final MembershipRepo membershipRepo;

    public MembershipService(MembershipRepo membershipRepo) {
        this.membershipRepo = membershipRepo;
    }

    // Fetch memberships for a specific user (for student dashboard to see joined societies)
    public List<Membership> getMembershipsByUser(Users user) {
        return membershipRepo.findByUser(user);
    }

    // Fetch memberships for a specific society (for society dashboard to see members)
    public List<Membership> getMembershipsBySociety(Society society) {
        return membershipRepo.findBySociety(society);
    }

    // Join a society (for student)
    public Membership joinSociety(Users user, Society society) {
        Membership membership = new Membership();
        membership.setUser(user);
        membership.setSociety(society);
        return membershipRepo.save(membership);
    }

    // Leave a society (for student or society to remove a member)
    public void leaveSociety(Long membershipId) {
        membershipRepo.deleteById(membershipId);
    }
}