package com.Controller;

import com.DTO.StudentCreateDTO;
import com.DTO.StudentResponseDTO;
import com.DTO.StudentUpdateDTO;
import com.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAll() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<StudentResponseDTO> create(@Valid @RequestBody StudentCreateDTO dto) {
        return ResponseEntity.ok(studentService.createStudent(dto));
    }

    @PreAuthorize("hasRole('ADMIN') or @studentSecurity.isOwner(#id)")
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PreAuthorize("hasRole('ADMIN') or @studentSecurity.isOwner(#id)")
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> update(@PathVariable Long id,
                                                     @RequestBody StudentUpdateDTO dto) {
        return ResponseEntity.ok(studentService.updateStudent(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/me")
    public ResponseEntity<StudentResponseDTO> getMyProfile(Authentication auth) {
        return ResponseEntity.ok(
                studentService.getByEmail(auth.getName())
        );
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/me")
    public ResponseEntity<StudentResponseDTO> updateMyProfile(
            Authentication auth,
            @RequestBody StudentUpdateDTO dto) {

        StudentResponseDTO updated =
                studentService.updateByEmail(auth.getName(), dto);

        return ResponseEntity.ok(updated); // ✅ MUST return body
    }

}
