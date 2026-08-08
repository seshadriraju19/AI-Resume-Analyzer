package com.seshadri.airesumeanalyzer.repository;

import com.seshadri.airesumeanalyzer.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

}