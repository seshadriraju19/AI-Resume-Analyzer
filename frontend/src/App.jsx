import { useState } from 'react'
import ReactMarkdown from 'react-markdown'
import './App.css'

function App() {
  const [file, setFile] = useState(null)
  const [error, setError] = useState('')
  const [analysis, setAnalysis] = useState(null)
  const [loading, setLoading] = useState(false)
  const [jobDescription, setJobDescription] = useState('')
  const handleAnalyze = async () => {
    if (!file) {
    setError('Please select a PDF file first.')
    return
}

try {
    setLoading(true)
        setError('')

        // Step 1: Create a Resume in Spring Boot
        const resumeResponse = await fetch(
            'http://localhost:8080/api/resumes',
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    name: 'Uploaded Resume',
                    email: 'upload@example.com',
                    phone: '0000000000'
                })
            }
        )

        if (!resumeResponse.ok) {
            throw new Error('Failed to create resume.')
        }

        const resume = await resumeResponse.json()

        console.log('Resume created:', resume)
        console.log('Resume ID:', resume.id)

        // Step 2: Send the PDF to Spring Boot
        const formData = new FormData()

        formData.append('file', file)

        const extractResponse = await fetch(
            `http://localhost:8080/api/resumes/extract-text?id=${resume.id}`,
            {
                method: 'POST',
                body: formData
            }
        )

        if (!extractResponse.ok) {
            throw new Error('Failed to extract PDF text.')
        }

        const updatedResume = await extractResponse.json()

        console.log('PDF text extracted:', updatedResume)
        const analyzeResponse = await fetch(
    `http://localhost:8080/api/resumes/${resume.id}/analyze`,
    {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            jobDescription: jobDescription
        })
    }
)

if (!analyzeResponse.ok) {
    throw new Error('Failed to analyze resume.')
}

const analysisText = await analyzeResponse.text()

console.log('AI Analysis:', analysisText)

const analysisData = JSON.parse(analysisText)
console.log("PARSED ANALYSIS:", analysisData)
setAnalysis(analysisData)

} catch (error) {
    console.error(error)
    setError('Something went wrong while analyzing the resume.')
} finally {
    setLoading(false)
}
}                        
return (
    <div className="app">

      <header className="navbar">
        <h2>AI Resume Analyzer</h2>

        <nav>
          <a href="#">Home</a>
          <a href="#">About</a>
        </nav>
      </header>

      <main className="hero">

        <h1>Analyze Your Resume with AI</h1>

        <p>
          Upload your resume and get AI-powered feedback
          to improve your chances of getting hired.
        </p>

        <div className="upload-box">

          <h2>Upload Your Resume</h2>

          <p>PDF files are supported</p>

          <input
          type="file"
          accept=".pdf"
          onChange={(event) => {
            const selectedFile = event.target.files[0]
            if (!selectedFile) {
              setFile(null)
              return
            }
            
            if (selectedFile.type !== 'application/pdf') {
              setFile(null)
              setError('Please select a PDF file.')
              return
            }
            
            setError('')
            setFile(selectedFile)
            }}/>
            {error && <p>{error}</p>}

            {file && <p>Selected file: {file.name}</p>}
            <div className="job-description">
              <h2>Job Description</h2>
              <p>
                Paste the job description you want to compare your resume with.
                </p>
                <textarea
                value={jobDescription}
                onChange={(event) => setJobDescription(event.target.value)}
                placeholder="Paste the job description here..."rows="10"
                />
                </div>

          <button onClick={handleAnalyze} disabled={loading}>{loading ? 'Analyzing Resume...' : 'Analyze Resume'}
          </button>
          {analysis && (
            <div className="analysis-result">
              <h2>AI Resume Analysis</h2>
              <div className="score">
                <h3>Overall Score</h3>
                <div className="score-value">
                  {analysis.overallScore} <span>/ 10</span>
                  </div>
                  </div>
                 <div className="strengths">
                  <h3>Strengths</h3>
                  <ul>
                    {analysis.strengths.map((strength, index) => (
                      <li key={index}>{strength}</li>
                      ))}
                    </ul>
                    </div>
                    <div className="weaknesses">
                      <h3>Weaknesses</h3>
                      <ul>
                        {analysis.weaknesses.map((weakness, index) => (
                          <li key={index}>{weakness}</li>
                          ))}
                          </ul>
                          </div>
                         
                          <div className="missing-skills">
                            <h3>Missing Skills</h3>
                            <ul>
                              {analysis.missingSkills.map((skill, index) => (
                                <li key={index}>{skill}</li>
                                ))}
                                </ul>
                                <div className="suggestions">
                                  <h3>Suggestions</h3>
                                  <ul>
                                    {analysis.suggestions.map((suggestion, index)=>(
                                      <li key={index}>{suggestion}</li>
                                      ))}
                                      </ul>
                                      </div>
                                      <div className="job-roles">
                                        <h3>Suitable Job Roles</h3>
                                        <ul>
                                          {analysis.jobRoles.map((role, index) => (
                                            <li key={index}>{role}</li>
                                            ))}
                                            </ul>
                                            </div>

                                      </div>
                                      </div>
          )}
          </div>
      </main>
    </div>
)

}

export default App