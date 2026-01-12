package com.Service;

import com.DTO.*;

import java.util.List;

public interface StudentService {

    List<StudentResponseDTO> getAllStudents();
    StudentResponseDTO getStudentById(Long id);
    StudentResponseDTO createStudent(StudentCreateDTO dto); // admin endpage
    StudentResponseDTO updateStudent(Long id, StudentUpdateDTO dto);
    void deleteStudent(Long id);
    StudentResponseDTO registerUser(StudentCreateDTO dto); // Public registration

}
