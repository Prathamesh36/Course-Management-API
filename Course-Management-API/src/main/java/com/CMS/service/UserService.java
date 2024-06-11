package com.CMS.service;

import com.CMS.entities.Course;
import com.CMS.entities.User;
import com.CMS.exception.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public User registerUser(User user);
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    User findByUsername(String username);

    List<User> getAllUsers();
    public List<User> findAllStudents();

   /* User updateUser(Long id, User user);*/

    /*  public User updateProgress(Long id, User user);*/

}
