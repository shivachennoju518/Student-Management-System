////package com.Service;
////
////import com.DTO.StudentCreateDTO;
////import com.DTO.StudentResponseDTO;
////import com.DTO.StudentUpdateDTO;
////import com.Entity.Student;
////import com.Entity.Role;
////import com.Exception.CustomException;
////import com.Repository.StudentRepo;
////import lombok.RequiredArgsConstructor;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.stereotype.Service;
////
////import java.util.List;
////
////@Service
////@RequiredArgsConstructor
////public class StudentServiceImpl implements StudentService {
////
////    private final StudentRepo studentRepo;
////    private final PasswordEncoder passwordEncoder;
////
////    @Override
////    public List<StudentResponseDTO> getAllStudents() {
////        return studentRepo.findAll()
////                .stream()
////                .map(this::toResponse)
////                .toList();
////    }
////
////    @Override
////    public StudentResponseDTO getStudentById(Long id) {
////        Student s = studentRepo.findById(id)
////                .orElseThrow(() -> new CustomException("Student not found"));
////        return toResponse(s);
////    }
////
////    @Override
////    public StudentResponseDTO createStudent(StudentCreateDTO dto) {
////
////        Role role;
////        try {
////            role = Role.valueOf(dto.getRole().toUpperCase());
////        } catch (Exception e) {
////            throw new CustomException("Role must be USER or ADMIN");
////        }
////
////        Student s = new Student();
////        s.setName(dto.getName());
////        s.setEmail(dto.getEmail());
////        s.setDepartment(dto.getDepartment());
////        s.setPassword(passwordEncoder.encode(dto.getPassword()));
////        s.setRole(role);
////
////        return toResponse(studentRepo.save(s));
////    }
////
////
////    @Override
////    public StudentResponseDTO updateStudent(Long id, StudentUpdateDTO dto) {
////
////        Student s = studentRepo.findById(id)
////                .orElseThrow(() -> new CustomException("Student not found"));
////
////        s.setName(dto.getName());
////        s.setDepartment(dto.getDepartment());
////
////        return toResponse(studentRepo.save(s));
////    }
////
////    @Override
////    public void deleteStudent(Long id) {
////        if (!studentRepo.existsById(id))
////            throw new CustomException("Student not found");
////        studentRepo.deleteById(id);
////    }
////
////    private StudentResponseDTO toResponse(Student s) {
////        return new StudentResponseDTO(
////                s.getId(), s.getName(), s.getEmail(), s.getDepartment());
////    }
////}
//
//package com.Service;
//
//import com.DTO.*;
//import com.Entity.Role;
//import com.Entity.Student;
//import com.Exception.CustomException;
//import com.Mapper.StudentMapper;
//import com.Repository.StudentRepo;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class StudentServiceImpl implements StudentService {
//
//    private final StudentRepo repo;
//    private final StudentMapper mapper;
//    private final PasswordEncoder encoder;
//
//    @Override
//    public StudentResponseDTO createStudent(StudentCreateDTO dto) {
//
//        Student student = mapper.toEntity(dto);
//        student.setPassword(encoder.encode(dto.getPassword()));
//
//        // ✅ ROLE DECISION LOGIC
//        if (dto.getRole() != null) {
//            student.setRole(dto.getRole()); // admin-created
//        } else {
//            student.setRole(Role.USER); // public registration
//        }
//
//        return mapper.toResponse(repo.save(student));
//    }
//
//    @Override
//    public List<StudentResponseDTO> getAllStudents() {
//        return repo.findAll().stream().map(mapper::toResponse).toList();
//    }
//
//    @Override
//    public StudentResponseDTO getStudentById(Long id) {
//        return mapper.toResponse(
//                repo.findById(id)
//                        .orElseThrow(() -> new CustomException("Student not found"))
//        );
//    }
//
//    @Override
//    public StudentResponseDTO updateStudent(Long id, StudentUpdateDTO dto) {
//        Student s = repo.findById(id)
//                .orElseThrow(() -> new CustomException("Student not found"));
//
//        s.setName(dto.getName());
//        s.setDepartment(dto.getDepartment());
//
//        return mapper.toResponse(repo.save(s));
//    }
//
//    @Override
//    public void deleteStudent(Long id) {
//        repo.deleteById(id);
//    }
//}




package com.Service;

import com.DTO.StudentCreateDTO;
import com.DTO.StudentResponseDTO;
import com.DTO.StudentUpdateDTO;
import com.Entity.Role;
import com.Entity.Student;
import com.Exception.CustomException;
import com.Mapper.StudentMapper;
import com.Repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepo repo;
    private final StudentMapper mapper;
    private final PasswordEncoder encoder;

    // Public registration → always USER
    @Override
    public StudentResponseDTO registerUser(StudentCreateDTO dto) {
        if (repo.existsByEmail(dto.getEmail())) {
            throw new CustomException("Email already registered");
        }

        Student student = mapper.toEntity(dto);
        student.setPassword(encoder.encode(dto.getPassword()));
        student.setRole(Role.USER);

        return mapper.toResponse(repo.save(student));
    }

    // Admin can create USER or ADMIN
    @Override
    public StudentResponseDTO createStudent(StudentCreateDTO dto) {
        if (repo.existsByEmail(dto.getEmail())) {
            throw new CustomException("Email already exists");
        }

        Student student = mapper.toEntity(dto);
        student.setPassword(encoder.encode(dto.getPassword()));

        // Default to USER if somehow role is null
        if (student.getRole() == null) {
            student.setRole(Role.USER);
        }

        return mapper.toResponse(repo.save(student));
    }

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        return repo.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public StudentResponseDTO getStudentById(Long id) {
        Student student = repo.findById(id)
                .orElseThrow(() -> new CustomException("Student not found"));
        return mapper.toResponse(student);
    }

    @Override
    public StudentResponseDTO updateStudent(Long id, StudentUpdateDTO dto) {
        Student student = repo.findById(id)
                .orElseThrow(() -> new CustomException("Student not found"));

        if (dto.getName() != null) student.setName(dto.getName());
        if (dto.getDepartment() != null) student.setDepartment(dto.getDepartment());

        return mapper.toResponse(repo.save(student));
    }

    @Override
    public void deleteStudent(Long id) {
        if (!repo.existsById(id)) throw new CustomException("Student not found");
        repo.deleteById(id);
    }
}

