package com.CMS.controllers;

import com.CMS.dto.UserDto;
import com.CMS.response.JwtResponse;
import com.CMS.security.JwtUtil;
import com.CMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        try{
            userService.registerUser(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to register user: "+ e.getMessage());
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDto userDto) {
        try {
            authenticationManager.authenticate(             //internally uses loadUser and Bcrypt password method
                    new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword())
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        final UserDetails userDetails = userService.loadUserByUsername(userDto.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        //return ResponseEntity.ok(new JwtResponse(jwt));
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    @GetMapping("/users/student")
    public ResponseEntity<List<UserDto>> getAllStudents() {
        List<UserDto> students = userService.findAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/users/teacher")
    public ResponseEntity<List<UserDto>> getAllTeachers() {
        List<UserDto> students = userService.findAllTeachers();
        return ResponseEntity.ok(students);
    }

    @PutMapping("/users/{id}/progress")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        try {
            UserDto updatedUser = userService.updateUser(id, userDto);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update progress: " + e.getMessage());
        }
    }

    @GetMapping("/users/{id}/progress")
    public ResponseEntity<?> getUserProgress(@PathVariable Long id) {
        try {
            UserDto userProgress = userService.getUserProgress(id);
            return ResponseEntity.ok(userProgress);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " + e.getMessage());
        }
    }
}
