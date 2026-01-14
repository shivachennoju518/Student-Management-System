package com.Config;

import com.Entity.Role;
import com.Entity.Student;
import com.Repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AdminBootstrap implements CommandLineRunner {

    private final StudentRepo repo;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception{

        if (repo.countByRole(Role.ADMIN) == 0) {

            Student admin = new Student();
            admin.setName("Super Admin");
            admin.setEmail("admin@system.com");
            admin.setDepartment("SYSTEM");
            admin.setPassword(encoder.encode("Admin@123"));
            admin.setRole(Role.ADMIN);

            repo.save(admin);

            System.out.println(" FIRST ADMIN CREATED");
        }
    }
}
