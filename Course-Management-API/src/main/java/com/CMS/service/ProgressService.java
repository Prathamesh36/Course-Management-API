package com.CMS.service;

import com.CMS.entities.Progress;
import com.CMS.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProgressService {
  //  List<Progress> getProgressByUserId(Long id);
    Progress updateProgress(Long userId, Progress progress);


}
