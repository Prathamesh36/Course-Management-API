package com.CMS.controllers;

import com.CMS.dto.LessonDto;
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
    public ResponseEntity<List<LessonDto>> getAllLessonsByCourseId(@PathVariable Long courseId) {
        try {
            List<LessonDto> lessons = lessonService.getAllLessonsByCourseId(courseId);
            return ResponseEntity.ok(lessons);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/lessons/{id}")
    public ResponseEntity<LessonDto> getLessonById(@PathVariable Long id) {
        try {
            LessonDto lesson = lessonService.getLessonById(id);
            if (lesson != null) {
                return ResponseEntity.ok(lesson);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/{courseId}/lessons")
    public ResponseEntity<?> createLesson(@PathVariable Long courseId, @RequestBody LessonDto lessonDto) {
        try {
            LessonDto createdLesson = lessonService.createLesson(courseId, lessonDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdLesson);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create lesson: " + e.getMessage());
        }
    }

    @PutMapping("/lessons/{id}")
    public ResponseEntity<?> updateLesson(@PathVariable Long id, @RequestBody LessonDto lessonDto) {
        try {
            LessonDto updatedLesson = lessonService.updateLesson(id, lessonDto);
            if (updatedLesson != null) {
                return ResponseEntity.ok(updatedLesson);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found with id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update lesson: " + e.getMessage());
        }
    }

    @DeleteMapping("/lessons/{id}")
    public ResponseEntity<?> deleteLesson(@PathVariable Long id) {
        try {
            boolean isDeleted = lessonService.deleteLesson(id);
            if (isDeleted) {
                return ResponseEntity.status(HttpStatus.OK).body("Lesson deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found with id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete lesson: " + e.getMessage());
        }
    }
}
