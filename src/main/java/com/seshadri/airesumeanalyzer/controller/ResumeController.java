package com.seshadri.airesumeanalyzer.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import com.seshadri.airesumeanalyzer.entity.Resume;
import com.seshadri.airesumeanalyzer.service.ResumeService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RequestMapping("/api/resumes")
@RestController
public class ResumeController {

    private final ResumeService resumeService;
    @PostMapping
public Resume createResume(@RequestBody Resume resume) {
    return resumeService.saveResume(resume);
}
@GetMapping
public List<Resume> getAllResumes() {
    return resumeService.getAllResumes();
}
@GetMapping("/{id}")
public Resume getResumeById(@PathVariable Long id) {
    return resumeService.getResumeById(id);
}
@PutMapping("/{id}")
public Resume updateResume(@PathVariable Long id, @RequestBody Resume resume) {
    return resumeService.updateResume(id, resume);
}
@DeleteMapping("/{id}")
public void deleteResume(@PathVariable Long id) {
    resumeService.deleteResume(id);
}

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }
}