package com.CMS.service;

import com.CMS.dto.CourseDto;
import com.CMS.entities.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    List<Course> getAllCourses(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    Course getCourseById(Long id);
    Course createCourse(CourseDto courseDto);
    Course updateCourse(Long id, CourseDto courseDto);
    boolean deleteCourse(Long id);
    List<Course> searchCourses(String keyword);

}
