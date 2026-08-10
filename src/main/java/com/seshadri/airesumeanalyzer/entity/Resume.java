package com.seshadri.airesumeanalyzer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    private String email;
    @NotBlank(message = "Phone is required")
    private String phone;
    @Column(columnDefinition = "TEXT")
    private String resumeText;
    private String skills;
    private String experience;
    private String education;
    private String projects;
    private String certifications;
    private String jobTitle;


}