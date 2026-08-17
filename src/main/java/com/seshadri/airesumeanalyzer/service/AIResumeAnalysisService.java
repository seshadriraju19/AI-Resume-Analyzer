package com.seshadri.airesumeanalyzer.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIResumeAnalysisService {

    private final ChatClient chatClient;

    public AIResumeAnalysisService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }
    public String analyzeResume(String resumeText) {

   String prompt = """
        Analyze the following resume professionally.

        Return the response EXACTLY in the following format:

        OVERALL_SCORE:
        Give a score from 0 to 10.

        STRENGTHS:
        - List the candidate's strongest skills or qualities.

        WEAKNESSES:
        - List the major weaknesses or missing areas.

        MISSING_SKILLS:
        - List important technical skills that are missing.

        SUGGESTIONS:
        - Give practical suggestions to improve the resume.

        JOB_ROLES:
        - List suitable job roles for this candidate.

        IMPORTANT:
        - Follow the section names exactly.
        - Do not add extra sections.
        - Keep each section concise.
        - Do not use Markdown headings.
        - Do not add introductory or concluding text.

        RESUME:
        %s
        """.formatted(resumeText);

    return chatClient.prompt()
            .user(prompt)
            .call()
            .content();
}
}