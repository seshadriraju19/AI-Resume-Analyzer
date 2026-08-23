package com.seshadri.airesumeanalyzer.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class ResumeAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
    private double overallScore;

@Column(columnDefinition = "TEXT")
private String strengths;

@Column(columnDefinition = "TEXT")
private String weaknesses;

@Column(columnDefinition = "TEXT")
private String missingSkills;

@Column(columnDefinition = "TEXT")
private String suggestions;

@Column(columnDefinition = "TEXT")
private String jobRoles;

}