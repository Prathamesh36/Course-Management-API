package com.CMS.service;

import com.CMS.entities.Progress;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProgressService {
    List<Progress> getProgressByUserId(Long id);
    Progress updateProgress(Long userId, Progress progress);

}
