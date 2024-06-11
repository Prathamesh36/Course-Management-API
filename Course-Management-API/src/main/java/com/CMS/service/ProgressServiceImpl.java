package com.CMS.service;

import com.CMS.entities.Progress;
import com.CMS.entities.User;
import com.CMS.exception.ResourceNotFoundException;
import com.CMS.repositories.ProgressRepo;
import com.CMS.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgressServiceImpl  implements  ProgressService{

    @Autowired
    private ProgressRepo progressRepo;

    @Autowired
    private UserRepo userRepo;


    @Override
    public List<Progress> getProgressByUserId(Long id) {
        return progressRepo.findByUserId(id);
    }

    @Override
    public Progress updateProgress(Long userId, Progress progress) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        progress.setUser(user);
        return progressRepo.save(progress);
    }
}
