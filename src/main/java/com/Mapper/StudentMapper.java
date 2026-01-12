

package com.Mapper;

import com.DTO.StudentCreateDTO;
import com.DTO.StudentResponseDTO;
import com.Entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student toEntity(StudentCreateDTO dto) {
        Student s = new Student();
        s.setName(dto.getName());
        s.setEmail(dto.getEmail());
        s.setDepartment(dto.getDepartment());
        return s;
    }

    public StudentResponseDTO toResponse(Student student) {
        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getDepartment()
        );
    }
}
