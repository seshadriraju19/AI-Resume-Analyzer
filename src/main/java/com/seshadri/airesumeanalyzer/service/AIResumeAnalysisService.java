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
            Analyze the following resume and provide a professional review.

            Give the response in these sections:
            1. Overall Score
            2. Strengths
            3. Weaknesses
            4. Missing Skills
            5. Suggestions for Improvement
            6. Suitable Job Roles

            Resume:
            %s
            """.formatted(resumeText);

    return chatClient.prompt()
            .user(prompt)
            .call()
            .content();
}
}