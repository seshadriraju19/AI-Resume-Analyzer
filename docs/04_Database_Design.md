# Database Design

## Database

PostgreSQL

---

# Tables

## Users

| Column | Type |
|---------|------|
| id | BIGINT |
| full_name | VARCHAR |
| email | VARCHAR |
| password | VARCHAR |
| created_at | TIMESTAMP |

---

## Resumes

| Column | Type |
|---------|------|
| id | BIGINT |
| user_id | BIGINT |
| file_name | VARCHAR |
| file_path | VARCHAR |
| uploaded_at | TIMESTAMP |

---

## Resume Analysis

| Column | Type |
|---------|------|
| id | BIGINT |
| resume_id | BIGINT |
| ats_score | INTEGER |
| missing_keywords | TEXT |
| missing_skills | TEXT |
| ai_suggestions | TEXT |
| analyzed_at | TIMESTAMP |

---

# Relationships

User

↓

Many Resumes

↓

Many Resume Analyses

---

# Future Tables

- Recruiters
- Job Postings
- Notifications
- Resume Templates

---

Prepared by

K. Sri Seshadri Raju