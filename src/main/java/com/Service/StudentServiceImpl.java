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
        student.setRole(Role.USER); // always USER

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

