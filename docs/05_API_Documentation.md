# API Documentation

## Base URL

/api/v1

---

# Authentication

## Register

POST /auth/register

Request

- Name
- Email
- Password

Response

- User Created

---

## Login

POST /auth/login

Request

- Email
- Password

Response

- JWT Token

---

# Resume

## Upload Resume

POST /resume/upload

Request

PDF Resume

Response

Resume Uploaded

---

## Analyze Resume

POST /resume/analyze/{resumeId}

Response

- ATS Score
- Missing Skills
- Missing Keywords
- AI Suggestions

---

## Get Analysis

GET /resume/{resumeId}

Response

Complete Report

---

## Delete Resume

DELETE /resume/{resumeId}

Response

Deleted Successfully

---

# Future APIs

- Admin Dashboard
- Recruiter Module
- Job Recommendation API

---

Prepared by

K. Sri Seshadri Raju