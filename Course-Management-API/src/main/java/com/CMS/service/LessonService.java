package com.CMS.service;

import com.CMS.entities.Lesson;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LessonService {
    List<Lesson> getAllLessonsByCourseId(Long courseId);
    Lesson getLessonById(Long id);
    Lesson createLesson(Long courseId, Lesson lesson);
    Lesson updateLesson(Long id, Lesson lesson);
    void deleteLesson(Long id);
}
