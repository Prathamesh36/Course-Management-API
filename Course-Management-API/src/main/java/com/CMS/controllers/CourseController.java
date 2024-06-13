package com.CMS.controllers;

import com.CMS.dto.CourseDto;
import com.CMS.entities.Course;
import com.CMS.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(@RequestParam(value="pageNumber", defaultValue="1", required =false)Integer pageNumber,
                                      @RequestParam(value="pageSize", defaultValue="10", required =false)Integer pageSize,
                                      @RequestParam(value="sortBy", defaultValue="id", required =false)String sortBy,
                                      @RequestParam(value="sortDir", defaultValue = "asc", required = false) String sortDir) {
        List<Course> courses = courseService.getAllCourses(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<Course>> searchCourseByTitle(@PathVariable("keywords") String keywords){
        List<Course> result = courseService.searchCourses((keywords));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody CourseDto courseDto) {
        Course createdCourse = courseService.createCourse(courseDto);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody CourseDto courseDto) {
        Course updatedCourse = courseService.updateCourse(id, courseDto);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
