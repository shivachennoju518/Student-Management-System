//package com.Controller;
//
//import com.DTO.StudentResponseDTO;
//import com.Service.StudentService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/admin/students")
//@RequiredArgsConstructor
//@PreAuthorize("hasRole('ADMIN')")
//public class AdminController {
//
//    private final StudentService studentService;
//
//    @GetMapping
//    public List<StudentResponseDTO> getAll() {
//        return studentService.getAllStudents();
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        studentService.deleteStudent(id);
//    }
//}
