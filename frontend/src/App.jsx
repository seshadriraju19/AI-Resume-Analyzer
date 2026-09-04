import { useState } from 'react'
import './App.css'
import AnalysisResult from './components/AnalysisResult'
import AnalysisHistory from './components/AnalysisHistory'
import Login from './pages/Login'
import Register from './pages/Register'

function App() {

  const [file, setFile] = useState(null)
  const [error, setError] = useState('')
  const [analysis, setAnalysis] = useState(null)
  const [loading, setLoading] = useState(false)
  const [jobDescription, setJobDescription] = useState('')
  const [historyRefresh, setHistoryRefresh] = useState(0)

  const [token, setToken] = useState(
    localStorage.getItem('token')
  )

  const [showRegister, setShowRegister] = useState(false)

  const handleAnalyze = async () => {

    if (!file) {
      setError('Please select a PDF file first.')
      return
    }

    if (!jobDescription.trim()) {
      setError('Please enter a job description.')
      return
    }

    try {
      setLoading(true)
      setError('')

      // Step 1: Create a Resume
      const resumeResponse = await fetch(
        'http://localhost:8080/api/resumes',
        {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
          },
          body: JSON.stringify({
            name: file.name,
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

      // Step 2: Send PDF to Spring Boot
      const formData = new FormData()

      formData.append('file', file)

      const extractResponse = await fetch(
        `http://localhost:8080/api/resumes/extract-text?id=${resume.id}`,
        {
          method: 'POST',
          headers: {
            'Authorization': `Bearer ${token}`
          },
          body: formData
        }
      )

      if (!extractResponse.ok) {
        throw new Error('Failed to extract PDF text.')
      }

      const updatedResume = await extractResponse.json()

      console.log('PDF text extracted:', updatedResume)

      // Step 3: Analyze Resume
      const analyzeResponse = await fetch(
        `http://localhost:8080/api/resumes/${resume.id}/analyze`,
        {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
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

      console.log('PARSED ANALYSIS:', analysisData)

      setAnalysis(analysisData)
      setHistoryRefresh(prev => prev + 1)

    } catch (error) {

      console.error(error)
      setError('Something went wrong while analyzing the resume.')

    } finally {

      setLoading(false)

    }
  }

  const handleLogout = () => {

    localStorage.removeItem('token')
    setToken(null)

  }

  // Show authentication pages when user is not logged in
  if (!token) {

    if (showRegister) {
      return (
        <Register
          onRegisterSuccess={() => setShowRegister(false)}
          onShowLogin={() => setShowRegister(false)}
        />
      )
    }

    return (
      <Login
        onLoginSuccess={(newToken) => setToken(newToken)}
        onShowRegister={() => setShowRegister(true)}
      />
    )
  }

  // Main Resume Analyzer
  return (
    <div className="app">

      <header className="navbar">

        <h2>AI Resume Analyzer</h2>

        <nav>
          <a href="#">Home</a>
          <a href="#">About</a>

          <button onClick={handleLogout}>
            Logout
          </button>
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

            }}
          />

          {error && <p>{error}</p>}

          {file && (
            <p>Selected file: {file.name}</p>
          )}

          <div className="job-description">

            <h2>Job Description</h2>

            <p>
              Paste the job description you want to compare
              your resume with.
            </p>

            <textarea
              value={jobDescription}
              onChange={(event) =>
                setJobDescription(event.target.value)
              }
              placeholder="Paste the job description here..."
              rows="10"
            />

          </div>

          <button
            onClick={handleAnalyze}
            disabled={loading}
          >
            {loading
              ? 'Analyzing Resume...'
              : 'Analyze Resume'}
          </button>

          <AnalysisResult analysis={analysis} />

          <AnalysisHistory
            refreshTrigger={historyRefresh}
            token={token}
          />

        </div>

      </main>

    </div>
  )
}

export default App