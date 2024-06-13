package com.CMS.service;

import com.CMS.dto.CourseDto;
import com.CMS.entities.Course;
import com.CMS.entities.User;
import com.CMS.exception.ResourceNotFoundException;
import com.CMS.exception.UsernameNotFoundException;
import com.CMS.repositories.CourseRepo;
import com.CMS.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<Course> getAllCourses(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort =(sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

        Page<Course> coursePost = courseRepo.findAll(p);

        List<Course> allCourses = coursePost.getContent();

        return allCourses;
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    }

    @Override
    public Course createCourse(CourseDto courseDto) {
        String username = getCurrentUsername();
        User teacher = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (teacher == null) {
            throw new RuntimeException("Teacher not found");
        }
        Course course = new Course();
        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());
        course.setTeacher(teacher);
        return courseRepo.save(course);
    }

    @Override
    public Course updateCourse(Long id, CourseDto courseDto) {
        Course existingCourse = courseRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        existingCourse.setName(courseDto.getName());
        existingCourse.setDescription(courseDto.getDescription());
        return courseRepo.save(existingCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepo.deleteById(id);
    }

    @Override
    public List<Course> searchCourses(String keyword) {
        List<Course> courses = courseRepo.findByNameContaining(keyword);
        return courses;
    }

    private String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}
