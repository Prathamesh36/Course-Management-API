package com.CMS.controllers;

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
    public List<Course> getAllCourses(@RequestParam(value="pageNumber", defaultValue="1", required =false)Integer pageNumber,
                                      @RequestParam(value="pageSize", defaultValue="10", required =false)Integer pageSize,
                                      @RequestParam(value="sortBy", defaultValue="id", required =false)String sortBy,
                                      @RequestParam(value="sortDir", defaultValue = "asc", required = false) String sortDir) {
        return courseService.getAllCourses(pageNumber, pageSize, sortBy, sortDir);
    }

    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<Course>> searchCourseByTitle(@PathVariable("keywords") String keywords){
        List<Course> result = courseService.searchCourses((keywords));
        return new ResponseEntity<List<Course>>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }
}
