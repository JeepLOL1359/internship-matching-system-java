/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author kitki
 */
public class Application implements Comparable<Application> {
    private String applicationId;
    private String applicantId;
    private String jobId;
    private String applicationDate; // Format: YYYY-MM-DD
    private double matchScore;      // 0.0 to 100.0

    public Application() {
    }
        
    public Application(String applicationId, String applicantId, String jobId, String applicationDate, double matchScore) {
        this.applicationId = applicationId;
        this.applicantId = applicantId;
        this.jobId = jobId;
        this.applicationDate = applicationDate;
        this.matchScore = matchScore;
    }

    // Getters and Setters
    public String getApplicationId() {
        return applicationId;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public String getJobId() {
        return jobId;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public double getMatchScore() {
        return matchScore;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public void setMatchScore(double matchScore) {
        this.matchScore = matchScore;
    }
    
    @Override
    public int compareTo(Application other) {
        return this.applicationId.compareToIgnoreCase(other.applicationId);
    }

    @Override
    public String toString() {
        return "Application ID: " + applicationId + ", Applicant ID: " + applicantId + ", Job ID: " + jobId + ", Match Score: " + matchScore;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Application)) return false;
        Application other = (Application) obj;
        return applicationId.equals(other.applicationId);
    }
}

