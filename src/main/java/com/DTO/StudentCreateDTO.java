package com.DTO;

import com.Entity.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudentCreateDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String department;


}
