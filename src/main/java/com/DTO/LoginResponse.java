package com.DTO;

import com.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    private String email;
    private Role role;
    private String token;
}
