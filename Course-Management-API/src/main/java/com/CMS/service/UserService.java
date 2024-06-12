package com.CMS.service;

import com.CMS.entities.Course;
import com.CMS.entities.User;
import com.CMS.exception.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    public User registerUser(User user);
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    User findByUsername(String username);

    List<User> getAllUsers();
    public List<User> findAllStudents();


    public  List<User> findAllTeachers();

    User updateUser(Long id, User user);

    User progress(Long id);

    Optional<User> getUserProgress(Long id);
    /*  public User updateProgress(Long id, User user);*/

}
