package com.Controller;
import com.DTO.LoginRequest;
import com.DTO.LoginResponse;
import com.DTO.StudentCreateDTO;
import com.DTO.StudentResponseDTO;
import com.Service.StudentService;
import com.jwt.JwtUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AuthController{

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final StudentService studentService;

@PostMapping("/register")
public ResponseEntity<StudentResponseDTO> register(
        @Valid @RequestBody StudentCreateDTO request) {
    return ResponseEntity.ok(studentService.registerUser(request));
}
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(auth);
        UserDetails user = (UserDetails) auth.getPrincipal();

        String role = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("ROLE_USER")
                .replace("ROLE_", "");

        String token = jwtUtils.generateToken(user.getUsername(), role);

        return ResponseEntity.ok(
                new LoginResponse(
                        user.getUsername(),
                        com.Entity.Role.valueOf(role),
                        token
                )
        );
    }



}
