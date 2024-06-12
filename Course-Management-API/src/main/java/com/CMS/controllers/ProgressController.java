package com.CMS.controllers;

import com.CMS.entities.Progress;
import com.CMS.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
//@RequestMapping("/users")
public class ProgressController {

    @Autowired
    private ProgressService progressService;

  //  @GetMapping("/{id}/progress")
    public List<Progress> getProgressByUserId(@PathVariable Long id) {
        return progressService.getProgressByUserId(id);
    }

  //  @PostMapping("/{id}/progress")
    public Progress updateProgress(@PathVariable Long id, @RequestBody Progress progress) {
        return progressService.updateProgress(id, progress);
    }

}
