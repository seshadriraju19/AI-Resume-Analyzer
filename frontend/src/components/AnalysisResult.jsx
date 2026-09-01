
import './AnalysisResult.css'

    function AnalysisResult({ analysis }) {

        if (!analysis) {
            return null
        }

    return (
  <div className="analysis-result">

    <h2>AI Resume Analysis</h2>

    {/* ATS SCORE */}
    <div className="score-card">
      <h3>ATS Match Score</h3>

      <div className="score-circle">
 <div
  className="score-value"
  style={{
    background: `conic-gradient(
      ${
        analysis.overallScore >= 8
          ? '#22c55e'
          : analysis.overallScore >= 6
          ? '#2563eb'
          : analysis.overallScore >= 4
          ? '#f59e0b'
          : '#ef4444'
      }
      ${(analysis.overallScore / 10) * 360}deg,
      #e2e8f0 ${(analysis.overallScore / 10) * 360}deg
    )`
  }}
>
  <div className="score-inner">
    {analysis.overallScore}
    <span>/10</span>
  </div>
</div>
</div>

      <p>
        {analysis.overallScore >= 8
          ? "Excellent Match"
          : analysis.overallScore >= 6
          ? "Good Match"
          : analysis.overallScore >= 4
          ? "Moderate Match"
          : "Needs Improvement"}
      </p>
    </div>

    {/* STRENGTHS */}
    <div className="analysis-card strengths">
      <h3>✓ Strengths</h3>

      <ul>
        {analysis.strengths.map((strength, index) => (
          <li key={index}>{strength}</li>
        ))}
      </ul>
    </div>

    {/* WEAKNESSES */}
    <div className="analysis-card weaknesses">
      <h3>⚠ Areas to Improve</h3>

      <ul>
        {analysis.weaknesses.map((weakness, index) => (
          <li key={index}>{weakness}</li>
        ))}
      </ul>
    </div>

    {/* MISSING SKILLS */}
    <div className="analysis-card missing-skills">
      <h3>Missing Skills</h3>

      {analysis.missingSkills.length > 0 ? (
        <div className="skill-tags">
          {analysis.missingSkills.map((skill, index) => (
            <span key={index} className="skill-tag">
              {skill}
            </span>
          ))}
        </div>
      ) : (
        <p>No major missing skills identified.</p>
      )}
    </div>

    {/* SUGGESTIONS */}
    <div className="analysis-card suggestions">
      <h3>AI Recommendations</h3>

      <ol>
        {analysis.suggestions.map((suggestion, index) => (
          <li key={index}>{suggestion}</li>
        ))}
      </ol>
    </div>

    {/* JOB ROLES */}
<div className="analysis-card job-roles">
    <h3>Suitable Job Roles</h3>

    <div className="role-tags">
        {analysis.jobRoles.map((role, index) => (
            <span key={index} className="role-tag">
                {role}
            </span>
        ))}
    </div>
</div>

</div>
    )
}



export default AnalysisResult