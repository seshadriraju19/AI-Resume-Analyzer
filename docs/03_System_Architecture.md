# System Architecture

## Project Architecture

The AI Resume Analyzer follows a three-tier architecture.

```
                +----------------------+
                |      Frontend        |
                | React.js Application |
                +----------+-----------+
                           |
                           | REST API
                           |
                +----------v-----------+
                |      Backend         |
                | Spring Boot API      |
                +----------+-----------+
                           |
            +--------------+--------------+
            |                             |
     +------v------+              +-------v-------+
     | PostgreSQL  |              |   OpenAI API  |
     |  Database   |              | AI Processing |
     +-------------+              +---------------+
```

---

## Components

### Frontend

Responsibilities:

- User Authentication
- Resume Upload
- Dashboard
- Reports
- Charts

Technology:

- React
- HTML
- CSS
- JavaScript

---

### Backend

Responsibilities:

- Authentication
- Resume Processing
- ATS Score Calculation
- AI Communication
- REST APIs

Technology:

- Java
- Spring Boot
- Spring Security
- JWT

---

### Database

Responsibilities:

- Store Users
- Store Resume Details
- Store Reports
- Store Analysis History

Technology:

- PostgreSQL

---

### AI Layer

Responsibilities:

- Resume Analysis
- ATS Suggestions
- Skill Gap Detection
- Improvement Suggestions

Technology:

- OpenAI API

---

## Architecture Style

- REST API
- Layered Architecture
- MVC Pattern

---

## Future Improvements

- Docker
- Redis Cache
- AWS Deployment
- Microservices
- Kubernetes

---

Prepared by

K. Sri Seshadri Raju