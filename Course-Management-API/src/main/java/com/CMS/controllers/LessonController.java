package com.CMS.controllers;

import com.CMS.entities.Lesson;
import com.CMS.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @GetMapping("/{courseId}/lessons")
    public ResponseEntity<List<Lesson>> getAllLessonsByCourseId(@PathVariable Long courseId) {
        List<Lesson> lessons = lessonService.getAllLessonsByCourseId(courseId);
        return  ResponseEntity.ok(lessons);
    }

    @GetMapping("/lessons/{id}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable Long id) {
        Lesson lesson = lessonService.getLessonById(id);
        if (lesson != null) {
            return ResponseEntity.ok(lesson);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/{courseId}/lessons")
    public ResponseEntity<Lesson> createLesson(@PathVariable Long courseId, @RequestBody Lesson lesson) {
        Lesson createdLesson = lessonService.createLesson(courseId, lesson);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLesson);
    }

    @PutMapping("/lessons/{id}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable Long id, @RequestBody Lesson lesson) {
        Lesson updatedLesson = lessonService.updateLesson(id, lesson);
        if (updatedLesson != null) {
            return ResponseEntity.ok(updatedLesson);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/lessons/{id}")
    public void deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
    }
}
