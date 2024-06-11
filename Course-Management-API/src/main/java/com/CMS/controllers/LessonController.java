package com.CMS.controllers;

import com.CMS.entities.Lesson;
import com.CMS.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @GetMapping("/{courseId}/lessons")
    public List<Lesson> getAllLessonsByCourseId(@PathVariable Long courseId) {
        return lessonService.getAllLessonsByCourseId(courseId);
    }

    @GetMapping("/lessons/{id}")
    public Lesson getLessonById(@PathVariable Long id) {
        return lessonService.getLessonById(id);
    }

    @PostMapping("/{courseId}/lessons")
    public Lesson createLesson(@PathVariable Long courseId, @RequestBody Lesson lesson) {
        return lessonService.createLesson(courseId, lesson);
    }

    @PutMapping("/lessons/{id}")
    public Lesson updateLesson(@PathVariable Long id, @RequestBody Lesson lesson) {
        return lessonService.updateLesson(id, lesson);
    }

    @DeleteMapping("/lessons/{id}")
    public void deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
    }
}
