package com.CMS.service;

import com.CMS.entities.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    List<Course> getAllCourses(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    Course getCourseById(Long id);
    Course createCourse(Course course);
    Course updateCourse(Long id, Course course);
    void deleteCourse(Long id);
    List<Course> searchCourses(String keyword);

}
