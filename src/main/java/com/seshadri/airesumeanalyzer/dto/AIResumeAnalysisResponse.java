package com.seshadri.airesumeanalyzer.dto;

import java.util.List;

public class AIResumeAnalysisResponse {

    private double overallScore;
    private List<String> strengths;
    private List<String> weaknesses;
    private List<String> missingSkills;
    private List<String> suggestions;
    private List<String> jobRoles;

    public AIResumeAnalysisResponse() {
    }

    public double getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(double overallScore) {
        this.overallScore = overallScore;
    }

    public List<String> getStrengths() {
        return strengths;
    }

    public void setStrengths(List<String> strengths) {
        this.strengths = strengths;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<String> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }

    public List<String> getJobRoles() {
        return jobRoles;
    }

    public void setJobRoles(List<String> jobRoles) {
        this.jobRoles = jobRoles;
    }
}