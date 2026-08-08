package com.seshadri.airesumeanalyzer.service;


import com.seshadri.airesumeanalyzer.entity.Resume;
import com.seshadri.airesumeanalyzer.repository.ResumeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;



@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;

    public ResumeService(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }
    public Resume saveResume(Resume resume) {
    return resumeRepository.save(resume);
}
public List<Resume> getAllResumes() {
    return resumeRepository.findAll();
}
public Resume getResumeById(Long id) {
    return resumeRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Resume not found with id: " + id
            ));
}
public void deleteResume(Long id) {
    if (!resumeRepository.existsById(id)) {
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Resume not found with id: " + id
        );
    }

    resumeRepository.deleteById(id);
}
public Resume updateResume(Long id, Resume resume) {

    if (!resumeRepository.existsById(id)) {
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Resume not found with id: " + id
        );
    }

    resume.setId(id);
    return resumeRepository.save(resume);
}
}