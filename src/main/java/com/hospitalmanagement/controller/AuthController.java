package com.hospitalmanagement.controller;



import com.hospitalmanagement.entities.Role;
import com.hospitalmanagement.entities.Staff;
import com.hospitalmanagement.payload.JWTAuthResponse;
import com.hospitalmanagement.payload.LoginDto;
import com.hospitalmanagement.payload.SignUpDto;
import com.hospitalmanagement.repository.RoleRepository;
import com.hospitalmanagement.repository.StaffRepository;
import com.hospitalmanagement.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto
                                                                    loginDto){
        Authentication authentication = authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTAuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
        // Check if username already exists
        if (staffRepository.existsByUsername(signUpDto.getUsername())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // Check if email already exists
        if (staffRepository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // Create new staff member
        Staff member = new Staff();
        member.setName(signUpDto.getName());
        member.setUsername(signUpDto.getUsername());
        member.setEmail(signUpDto.getEmail());
        member.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        // Set staff member role to "ROLE_ADMIN"
        Role role = roleRepository.findByName("ROLE_ADMIN").orElseGet(() -> {
            Role newRole = new Role();
            newRole.setName("ROLE_ADMIN");
            return roleRepository.save(newRole);
        });
        member.setRoles(Collections.singleton(role));
        // Save staff member to the database
        staffRepository.save(member);

        return new ResponseEntity<>("Staff member registered successfully", HttpStatus.OK);
    }}
