package com.CMS.repositories;

import com.CMS.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LessonRepo extends JpaRepository<Lesson,Long> {
    List<Lesson> findByCourseId(Long courseId);
    /*
    List<Lesson> findByCourseId(Long courseId);
    Optional<Lesson> findByLessonIdAndCourseId(Long lessonId, Long courseId);*/
}
