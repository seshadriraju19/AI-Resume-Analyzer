package com.seshadri.airesumeanalyzer.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIResumeAnalysisService {

    private final ChatClient chatClient;

    public AIResumeAnalysisService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }
    public String analyzeResume(String resumeText, String jobDescription) {

   String prompt = """
    Analyze the following resume against the provided job description professionally and accurately.

    Return ONLY valid JSON.
    Do not use Markdown.
    Do not add explanations before or after the JSON.

    Use exactly this JSON structure:

    {
      "overallScore": 0.0,
      "strengths": [],
      "weaknesses": [],
      "missingSkills": [],
      "suggestions": [],
      "jobRoles": []
    }

    Rules:

    - overallScore must be a number from 0 to 10.
    - overallScore represents the actual match between the resume and job description.
    - Base the score on required skills, preferred skills,relevant experience,projects, education, and certifications.
    - Do not give a near-perfect score merely because most keywords match.
    - Deduct points for important requirements that are absent from the resume.
    - strengths must contain concise points supported by evidence from the resume and relevant to the job description.
    - weaknesses must contain genuine gaps in the resume that may reduce the candidate's suitability for the job description.
    - missingSkills must contain skills required or strongly preferred by the job description that are not clearly present in the resume.
    - suggestions must contain practical and specific actions the candidate can take to improve their suitability for the job.
    - jobRoles must contain realistic job roles based on the candidate's actual skills, experience, and the provided job description.
    - Do not add any fields other than the six specified fields.
    - Do not wrap the JSON in Markdown code fences.

    ATS SCORING:

Calculate overallScore using the following approximate weighting:

Required technical skills: 40%%
Preferred skills and technology: 20%%
Relevant projects and practical experience: 20%%
Education and certifications: 10%%
Overall relevance to the job description: 10%%

First evaluate each category based only on evidence from the resume.
Then combine the results into a final score from 0 to 10.

Do not inflate the score because of a large number of unrelated
keywords or technologies.
A missing critical required skill should significantly reduce the score.

    IMPORTANT ANALYSIS RULES:

    1. Analyze ONLY information that is actually present in the resume.
       Do not invent skills, experience, internships, certifications, projects,
       companies, dates, or technologies.

    2. Treat internships, training programs, apprenticeships, and relevant
       practical experience as experience when they are explicitly mentioned
       in the resume.

       IMPORTANT EXPERIENCE VALIDATION RULE:

Before writing any weakness related to professional experience, carefully inspect
the ENTIRE resume.

- Do NOT claim "no professional experience", "no internship experience",
  "no industry experience", or similar statements unless the resume genuinely
  contains no internship, apprenticeship, trainee role, freelance work,
  contract work, or employment experience.

- If an internship, apprenticeship, trainee role, freelance work, contract work,
  or employment is explicitly listed anywhere in the resume, treat it as
  professional experience.

- Do NOT ignore experience simply because it is short-term, recent, remote,
  unpaid, or an internship.

- Do NOT treat a certification as employment unless the resume explicitly
  describes it as an internship or work experience.

- Do NOT treat a training program such as QSpiders Java Full Stack Development
  as employment unless the resume explicitly describes it as an internship or
  work role.

- If there is no professional experience in the resume, you may mention the
  absence of professional experience as a weakness.

- If the resume contains professional experience, evaluate the quality and
  relevance of that experience instead of saying that the candidate has no
  professional experience.

    3. EXPERIENCE AND INTERNSHIPS:
       - Carefully inspect the entire resume for internships, apprenticeships,
         trainee programs, freelance work, contract work, and professional roles.
       - An internship, apprenticeship, trainee role, or relevant freelance/contract
         role MUST be treated as professional experience.
       - If the resume contains an internship or trainee experience, DO NOT say
         that the candidate has "no professional experience" or "no internships".
       - Do NOT confuse "no full-time employment" with "no professional experience".
       - Only identify a lack of professional experience when the resume genuinely
         contains no internship, apprenticeship, trainee role, freelance work,
         contract work, or employment experience.

    3A. PROFESSIONAL EXPERIENCE ACCURACY:
       - If the resume explicitly mentions an internship, training program, apprenticeship,trainee role, freelance work,contract work, or employment, recognize it as experience.
       - Never state that the candidate has "no professional
       experience" when such experience is explicitly present.
       - QSpiders Java Full Stack Development should be treated as
       training/certification only unless the resume explicitly describes it as an internship or work role.
       - Do not invent an internship or employment relationship that is not explicitly stated.

    4. Carefully distinguish between:
       - education
       - internships
       - work experience
       - projects
       - certifications
       - technical skills

    5. DATE VALIDATION:
       - Treat the current date as August 2026.
       - Dates such as July 2026, August 2026, and "Present" are valid and must NOT
         be considered future dates.
       - Do NOT call a project date incorrect merely because it is recent.
       - Only flag a date when there is clear internal evidence of an actual
         contradiction or impossible timeline in the resume.

    6. When identifying missing skills, compare the job description with the
       skills, projects, education, certifications, internships, and experience
       actually present in the resume.
       Do not list a technology as missing if it is already clearly present.

    7. Strengths should be based on concrete evidence from the resume and should
       preferably relate to requirements mentioned in the job description.

    8. Weaknesses should identify genuine gaps between the resume and the job
       description.

    9. Suggestions should be actionable and specific. Recommend improvements
       such as relevant technologies, projects, testing practices, deployment
       skills, certifications, or resume improvements when appropriate.

    10. Suitable job roles must match the candidate's actual skill level.
        Do not recommend senior roles when the resume shows entry-level or
        junior-level experience.

    RESUME:

    %s

    JOB DESCRIPTION:

    %s
    """.formatted(resumeText, jobDescription);

    return chatClient.prompt()
            .user(prompt)
            .call()
            .content();
}
}