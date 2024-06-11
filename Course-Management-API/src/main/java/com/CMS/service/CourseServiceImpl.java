package com.CMS.service;

import com.CMS.entities.Course;
import com.CMS.exception.ResourceNotFoundException;
import com.CMS.repositories.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepo courseRepo;

    @Override
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepo.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        Course existingCourse = courseRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        existingCourse.setName(course.getName());
        existingCourse.setDescription(course.getDescription());
        return courseRepo.save(existingCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepo.deleteById(id);
    }
}
