package com.CMS.service;

import com.CMS.entities.Course;
import com.CMS.entities.Lesson;
import com.CMS.exception.ResourceNotFoundException;
import com.CMS.repositories.CourseRepo;
import com.CMS.repositories.LessonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService{

    @Autowired
    private LessonRepo lessonRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Override
    public List<Lesson> getAllLessonsByCourseId(Long courseId) {
        return lessonRepo.findByCourseId(courseId);
    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Lesson not found"));
    }

    @Override
    public Lesson createLesson(Long courseId, Lesson lesson) {
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        lesson.setCourse(course);
        lesson.setCourseId(courseId);
        return lessonRepo.save(lesson);
    }

    @Override
    public Lesson updateLesson(Long id, Lesson lesson) {
        Lesson existingLesson = lessonRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Lesson not found"));
        existingLesson.setTitle(lesson.getTitle());
        existingLesson.setContent(lesson.getContent());
        return lessonRepo.save(existingLesson);
    }

    @Override
    public void deleteLesson(Long id) {
        lessonRepo.deleteById(id);
    }
}
