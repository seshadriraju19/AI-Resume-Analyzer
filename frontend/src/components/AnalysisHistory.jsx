import { useEffect, useState } from "react";
import AnalysisResult from "./AnalysisResult";

function AnalysisHistory({ refreshTrigger }) {
  const [history, setHistory] = useState([]);
  const [loading, setLoading] = useState(true);
  const [selectedAnalysis, setSelectedAnalysis] = useState(null);

  useEffect(() => {
    fetch("http://localhost:8080/api/resumes/analysis/history")
      .then((response) => response.json())
      .then((data) => {
        setHistory(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching analysis history:", error);
        setLoading(false);
      });
  }, [refreshTrigger]);

  const handleDelete = async (id) => {
    try {
      const response = await fetch(
        `http://localhost:8080/api/resumes/analysis/${id}`,
        {
          method: "DELETE",
        }
      );

      if (response.ok) {
        setHistory((prevHistory) =>
          prevHistory.filter((analysis) => analysis.id !== id)
        );

        if (selectedAnalysis?.id === id) {
          setSelectedAnalysis(null);
        }
      } else {
        console.error("Failed to delete analysis");
      }
    } catch (error) {
      console.error("Error deleting analysis:", error);
    }
  };

  const handleViewAnalysis = (analysis) => {
    const parsedAnalysis = {
      ...analysis,
      strengths: JSON.parse(analysis.strengths || "[]"),
      weaknesses: JSON.parse(analysis.weaknesses || "[]"),
      missingSkills: JSON.parse(analysis.missingSkills || "[]"),
      suggestions: JSON.parse(analysis.suggestions || "[]"),
      jobRoles: JSON.parse(analysis.jobRoles || "[]"),
    };

    setSelectedAnalysis(parsedAnalysis);
  };

  if (loading) {
    return <p>Loading analysis history...</p>;
  }

  return (
    <div className="analysis-history">
      <h2>Analysis History</h2>

      {history.length === 0 ? (
        <p>No previous analyses found.</p>
      ) : (
        history.map((analysis) => (
          <div className="history-card" key={analysis.id}>
            <h3>Analysis #{analysis.id}</h3>

            <p>
              <strong>ATS Score:</strong>{" "}
              {analysis.overallScore}/10
            </p>

            <p>
              <strong>Resume:</strong>{" "}
              {analysis.resume?.name || "Uploaded Resume"}
            </p>

            <button onClick={() => handleViewAnalysis(analysis)}>
              View Analysis
            </button>

            <button onClick={() => handleDelete(analysis.id)}>
              Delete Analysis
            </button>

            {/* Show detailed analysis inside the selected card */}
            {selectedAnalysis?.id === analysis.id && (
              <div className="selected-analysis">
                <button onClick={() => setSelectedAnalysis(null)}>
                  Close Analysis
                </button>

                <AnalysisResult analysis={selectedAnalysis} />
              </div>
            )}
          </div>
        ))
      )}
    </div>
  );
}

export default AnalysisHistory;