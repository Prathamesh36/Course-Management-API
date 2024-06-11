package com.CMS.repositories;

import com.CMS.entities.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgressRepo extends JpaRepository<Progress, Long> {
    List<Progress> findByUserId(Long userId);
    /*
    List<Progress> findByUserId(Long userId);
    Optional<Progress> findByUserIdAndCourseId(Long userId, Long courseId);*/
}
