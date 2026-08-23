package com.seshadri.airesumeanalyzer.service;

import org.springframework.stereotype.Service;

import com.seshadri.airesumeanalyzer.entity.ResumeAnalysis;
import com.seshadri.airesumeanalyzer.repository.ResumeAnalysisRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seshadri.airesumeanalyzer.dto.AIResumeAnalysisResponse;
import com.seshadri.airesumeanalyzer.entity.Resume;
import com.seshadri.airesumeanalyzer.entity.ResumeAnalysis;
import com.seshadri.airesumeanalyzer.repository.ResumeAnalysisRepository;
import com.seshadri.airesumeanalyzer.repository.ResumeRepository;
import org.springframework.stereotype.Service;

@Service
public class ResumeAnalysisService {

    private final ResumeAnalysisRepository resumeAnalysisRepository;
    private final ResumeRepository resumeRepository;
    private final ObjectMapper objectMapper;

    public ResumeAnalysisService(
        ResumeAnalysisRepository resumeAnalysisRepository,
        ResumeRepository resumeRepository,
        ObjectMapper objectMapper) {

    this.resumeAnalysisRepository = resumeAnalysisRepository;
    this.resumeRepository = resumeRepository;
    this.objectMapper = objectMapper;
}
    
    public ResumeAnalysis saveAnalysis(ResumeAnalysis analysis) {
    return resumeAnalysisRepository.save(analysis);
    
}
public ResumeAnalysis getAnalysisByResumeId(Long resumeId) {

    return resumeAnalysisRepository.findByResumeId(resumeId)
            .orElseThrow(() -> new RuntimeException(
                    "Analysis not found for resume id: " + resumeId
            ));
}

public ResumeAnalysis createAndSaveAnalysis(
        Long resumeId,
        String aiResponse) throws Exception {

    // 1. Find the resume
    Resume resume = resumeRepository.findById(resumeId)
            .orElseThrow(() ->
                    new RuntimeException("Resume not found with id: " + resumeId));

    // 2. Convert AI JSON into Java DTO
    AIResumeAnalysisResponse response =
            objectMapper.readValue(
                    aiResponse,
                    AIResumeAnalysisResponse.class
            );

    // 3. Create ResumeAnalysis entity
    ResumeAnalysis analysis = new ResumeAnalysis();

    analysis.setResume(resume);
    analysis.setOverallScore(response.getOverallScore());

    analysis.setStrengths(
            objectMapper.writeValueAsString(response.getStrengths())
    );

    analysis.setWeaknesses(
            objectMapper.writeValueAsString(response.getWeaknesses())
    );

    analysis.setMissingSkills(
            objectMapper.writeValueAsString(response.getMissingSkills())
    );

    analysis.setSuggestions(
            objectMapper.writeValueAsString(response.getSuggestions())
    );

    analysis.setJobRoles(
            objectMapper.writeValueAsString(response.getJobRoles())
    );

    // 4. Save analysis
    return resumeAnalysisRepository.save(analysis);
}
}