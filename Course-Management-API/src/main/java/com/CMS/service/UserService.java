package com.CMS.service;

import com.CMS.dto.UserDto;
import com.CMS.entities.User;
import com.CMS.exception.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    void registerUser(UserDto userDto);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    List<UserDto> findAllStudents();

    List<UserDto> findAllTeachers();

    UserDto updateUser(Long id, UserDto userDto);

    User progress(Long id);

    UserDto getUserProgress(Long id);


}
