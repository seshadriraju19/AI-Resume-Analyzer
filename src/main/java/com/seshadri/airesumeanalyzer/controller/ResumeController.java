package com.seshadri.airesumeanalyzer.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import com.seshadri.airesumeanalyzer.entity.Resume;
import com.seshadri.airesumeanalyzer.service.PdfTextExtractorService;
import com.seshadri.airesumeanalyzer.service.ResumeService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;
import com.seshadri.airesumeanalyzer.service.AIResumeAnalysisService;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/resumes")
@RestController
public class ResumeController {

    private final ResumeService resumeService;
    private final PdfTextExtractorService pdfTextExtractorService;
    private final AIResumeAnalysisService aiResumeAnalysisService;
    @PostMapping
public Resume createResume(@Valid @RequestBody Resume resume) {
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

   public ResumeController(ResumeService resumeService,
        PdfTextExtractorService pdfTextExtractorService,
        AIResumeAnalysisService aiResumeAnalysisService) {

    this.resumeService = resumeService;
    this.pdfTextExtractorService = pdfTextExtractorService;
    this.aiResumeAnalysisService = aiResumeAnalysisService;
}
    @PostMapping("/extract-text")
public Resume extractText(
        @RequestParam("id") Long id,
        @RequestParam("file") MultipartFile file) throws Exception {

    String extractedText = pdfTextExtractorService.extractText(file);

    return resumeService.saveExtractedText(id, extractedText);
}
@GetMapping("/{id}/analyze")
public String analyzeResume(@PathVariable Long id) {

    Resume resume = resumeService.getResumeById(id);

    return aiResumeAnalysisService.analyzeResume(resume.getResumeText());
}
}