package com.CMS.service;

import com.CMS.dto.LessonDto;
import com.CMS.entities.Course;
import com.CMS.entities.Lesson;
import com.CMS.exception.ResourceNotFoundException;
import com.CMS.repositories.CourseRepo;
import com.CMS.repositories.LessonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonServiceImpl implements LessonService{

    @Autowired
    private LessonRepo lessonRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Override
    public List<LessonDto> getAllLessonsByCourseId(Long courseId) {
        List<Lesson> lessons = lessonRepo.findByCourseId(courseId);
        return lessons.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public LessonDto getLessonById(Long id) {
        return lessonRepo.findById(id).map(this::convertToDto).orElse(null);
    }

    @Override
    public LessonDto createLesson(Long courseId, LessonDto lessonDto) {
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        Lesson lesson = new Lesson();
        lesson.setCourse(course);
        lesson.setCourseId(courseId);
        lesson.setCourse(course);
        Lesson savedLesson = lessonRepo.save(lesson);
        return convertToDto(savedLesson);
    }

    @Override
    public LessonDto updateLesson(Long id, LessonDto lessonDto) {
        Lesson lesson = lessonRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Lesson not found"));
        lesson.setTitle(lessonDto.getTitle());
        lesson.setContent(lessonDto.getContent());
        Lesson updatedLesson = lessonRepo.save(lesson);
        return convertToDto(updatedLesson);
    }

    @Override
    public boolean deleteLesson(Long id) {
        if (lessonRepo.existsById(id)) {
            lessonRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private LessonDto convertToDto(Lesson lesson) {
        LessonDto lessonDto = new LessonDto();
        lessonDto.setId(lesson.getLessonId());
        lessonDto.setTitle(lesson.getTitle());
        lessonDto.setContent(lesson.getContent());
        lessonDto.setCourseId(lesson.getCourse().getCourseId());
        return lessonDto;
    }
}
