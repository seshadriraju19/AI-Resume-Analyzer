# 🤖 AI Resume Analyzer

An AI-powered full-stack application that analyzes a candidate's resume against a job description and provides actionable insights to improve job suitability.

The application extracts resume content, sends it for AI-powered analysis, and presents structured results including ATS-style scoring, strengths, weaknesses, missing skills, improvement suggestions, and suitable job roles.

---

## 🚀 Key Features

- 📄 Upload and analyze resumes
- 📝 Compare resumes against job descriptions
- 🎯 AI-powered ATS-style resume scoring
- 💪 Identify candidate strengths
- ⚠️ Identify resume weaknesses and skill gaps
- 🧩 Detect missing or strongly preferred skills
- 💡 Generate actionable improvement suggestions
- 💼 Recommend suitable job roles based on the candidate's profile
- 🗄️ Persist resume analysis results using PostgreSQL
- 🔗 REST API built with Spring Boot
- ⚛️ React-based frontend for interacting with the application

---

## 🏗️ Application Architecture

```text
┌──────────────────────┐
│      React UI        │
│      Frontend        │
└──────────┬───────────┘
           │
           │ REST API
           ▼
┌──────────────────────┐
│    Spring Boot       │
│      Backend         │
├──────────────────────┤
│ Controllers          │
│ Services             │
│ Repositories         │
│ DTOs                 │
│ Exception Handling   │
└──────────┬───────────┘
           │
      ┌────┴─────┐
      ▼          ▼
┌───────────┐  ┌──────────────┐
│ PostgreSQL│  │   OpenAI API │
│ Database  │  │ AI Analysis  │
└───────────┘  └──────────────┘



---

## 🛠️ Tech Stack

| Layer | Technologies |
|---|---|
| Frontend | React, JavaScript, HTML5, CSS3 |
| Backend | Java, Spring Boot |
| Database | PostgreSQL |
| AI Integration | OpenAI API |
| Build Tool | Maven |
| API | REST API |

---

## 📂 Project Structure

```text
AI-Resume-Analyzer/
├── frontend/          # React frontend
├── src/               # Spring Boot backend
├── database/          # Database scripts / setup
├── docs/              # Project documentation
├── assets/            # Project assets
├── pom.xml            # Maven configuration
└── README.md          # Project documentation

---

## 🔄 How It Works

1. Upload a resume.
2. Provide a job description.
3. The application processes the resume content.
4. The backend sends the relevant data for AI analysis.
5. The AI evaluates the resume against the job requirements.
6. The application generates structured analysis results.
7. Results are displayed through the React interface.

---

## ⚙️ Getting Started

### Prerequisites

- Java
- Maven
- Node.js and npm
- PostgreSQL
- OpenAI API key

### Backend

```bash
./mvnw spring-boot:run

### Frontend

cd frontend
npm install
npm run dev

### 🔐 Security

API keys and sensitive configuration values should be stored using environment variables and should never be committed to the repository.

### 📌 Current Status

🚧 Active Development

The backend, REST API, database integration, React frontend, and AI analysis workflow are currently being developed and integrated.

---

## 🚀 Future Improvements

- User authentication and authorization
- Improved resume parsing
- Enhanced ATS scoring
- Advanced job-role recommendations
- Resume optimization suggestions
- Automated testing
- CI/CD integration
- Cloud deployment

---

## 👨‍💻 Author

**K. Sri Seshadri Raju**

Java Full Stack Developer | Computer Science Graduate

- [LinkedIn](https://www.linkedin.com/in/seshadri-kurapati-b18434298/)
- [GitHub](https://github.com/seshadiraju19)
- [Email](mailto:seshadiraju19@gmail.com)




