//package com.Service;
//
//import com.Entity.Student;
//import com.Repository.StudentRepo;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.*;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final StudentRepo studentRepo;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) {
//        Student s = studentRepo.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        return new org.springframework.security.core.userdetails.User(
//                s.getEmail(),
//                s.getPassword(),
//                List.of(new SimpleGrantedAuthority("ROLE_" + s.getRole().name()))
//        );
//    }
//}


package com.Service;

import com.Entity.Student;
import com.Repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final StudentRepo repo;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Student user = repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
        );
    }
}
