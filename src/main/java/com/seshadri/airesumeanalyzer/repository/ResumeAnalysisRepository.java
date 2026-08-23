package com.seshadri.airesumeanalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seshadri.airesumeanalyzer.entity.ResumeAnalysis;
import java.util.Optional;

public interface ResumeAnalysisRepository extends JpaRepository<ResumeAnalysis, Long> {
    Optional<ResumeAnalysis> findByResumeId(Long resumeId);

}