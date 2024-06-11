package com.CMS.service;

import com.CMS.entities.Course;
import com.CMS.entities.Role;
import com.CMS.entities.User;
import com.CMS.exception.ResourceNotFoundException;
import com.CMS.exception.UsernameNotFoundException;
import com.CMS.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
       return userRepo.findAll();
    }

    @Override
    public List<User> findAllStudents() {
        return userRepo.findByRole(Role.STUDENT);
    }
/*
    @Override
    public User updateProgress(Long userId, User user2) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setProgress(user2.getProgress());
        return userRepo.save(user);
    }
*/
/*
    @Override
    public User updateUser(Long id, User user2) {
        return userRepo.findById(id).map(user -> {
            user.setProgress(user2.getProgress());
            return userRepo.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }
*/
}
