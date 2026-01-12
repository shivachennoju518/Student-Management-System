package com.Security;

import com.Repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("studentSecurity")
@RequiredArgsConstructor
public class StudentSecurity {

    private final StudentRepo studentRepo;

    public boolean isOwner(Long id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // 1️⃣ Block unauthenticated access
        if (auth == null || !auth.isAuthenticated()) return false;

        String email = auth.getName();

        // 2️⃣ ONE DB call – clean & safe
        return studentRepo.findByEmail(email)
                .map(student -> student.getId().equals(id))
                .orElse(false);
    }
}
