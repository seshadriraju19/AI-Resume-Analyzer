import { useState } from 'react'
import ReactMarkdown from 'react-markdown'
import './App.css'

function App() {
  const [file, setFile] = useState(null)
  const [error, setError] = useState('')
  const [analysis, setAnalysis] = useState('')
  const [loading, setLoading] = useState(false)
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
    `http://localhost:8080/api/resumes/${resume.id}/analyze`
)

if (!analyzeResponse.ok) {
    throw new Error('Failed to analyze resume.')
}

const analysis = await analyzeResponse.text()

console.log('AI Analysis:', analysis)
setAnalysis(analysis)

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

          <button onClick={handleAnalyze} disabled={loading}>{loading ? 'Analyzing Resume...' : 'Analyze Resume'}
          </button>
          {analysis && (
            <div className="analysis-result">
              <h2>AI Resume Analysis</h2>
              <ReactMarkdown>{analysis}</ReactMarkdown>
              </div>
            )}

        </div>

      </main>

    </div>
  )

}

export default App