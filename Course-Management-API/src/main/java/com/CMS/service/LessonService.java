package com.CMS.service;

import com.CMS.dto.LessonDto;
import com.CMS.entities.Lesson;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LessonService {
    List<LessonDto> getAllLessonsByCourseId(Long courseId);
    LessonDto getLessonById(Long id);
    LessonDto createLesson(Long courseId, LessonDto lessonDto);
    LessonDto updateLesson(Long id, LessonDto lessonDto);
    boolean deleteLesson(Long id);
}
