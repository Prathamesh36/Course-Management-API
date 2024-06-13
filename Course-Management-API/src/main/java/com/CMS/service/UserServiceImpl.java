package com.CMS.service;

import com.CMS.dto.UserDto;
import com.CMS.entities.Role;
import com.CMS.entities.User;
import com.CMS.exception.UsernameNotFoundException;
import com.CMS.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.valueOf(userDto.getRole()));
        userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }


    @Override
    public List<UserDto> findAllStudents() {
        return userRepo.findAll().stream()
                .filter(user -> user.getRole() == Role.STUDENT)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findAllTeachers() {
        return userRepo.findAll().stream()
                .filter(user -> user.getRole() == Role.TEACHER)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setProgress(userDto.getProgress());
        userRepo.save(user);
        return convertToDTO(user);
    }

    @Override
    public User progress(Long id) {
        return null;
    }

    @Override
    public UserDto getUserProgress(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDTO(user);
    }

    private UserDto convertToDTO(User user) {
        UserDto userDTO = new UserDto();
        userDTO.setId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole().name());
        userDTO.setProgress(user.getProgress());
        return userDTO;
    }


}
