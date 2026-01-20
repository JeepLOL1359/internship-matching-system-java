/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import utility.FormatTime;

/**
 *
 * @author kitki
 */
public class Interview implements Comparable<Interview> {
    private String interviewId;
    private Applicant applicant;
    private Job job;
    private String date;      // Format: YYYY-MM-DD
    private String time;      // Format: HH:MM
    private String status;    // Example: "Scheduled", "Completed", "Passed", "Rejected"
    private int reportedExperience; //In year
    
    public Interview() {
    }

    public Interview(String interviewId, Applicant applicant, Job job, String date, String time, String status, int reportedExperience) {
        this.interviewId = interviewId;
        this.applicant = applicant;
        this.job = job;
        this.date = FormatTime.formatDate(date);
        this.time = FormatTime.formatTime(time);
        this.status = status;
        this.reportedExperience = reportedExperience;
    }

    // Getters and Setters
    public String getInterviewId() { return interviewId; }
    public void setInterviewId(String interviewId) { this.interviewId = interviewId; }

    public Applicant getApplicant() { return applicant; }
    public void setApplicant(Applicant applicant) { this.applicant = applicant; }

    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getReportedExperience() {return reportedExperience;}
    public void setReportedExperience(int reportedExperience) {this.reportedExperience = reportedExperience;}

    @Override
    public int compareTo(Interview other) {
        return this.interviewId.compareToIgnoreCase(other.interviewId);
    }
    
    @Override
    public String toString() {
        return "Interview ID: " + interviewId + ", Applicant: " + applicant.getName() + ", Job: " + job.getTitle() + ", Date: " + date + " " + time + ", Status: " + status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Interview)) return false;
        Interview other = (Interview) obj;
        return interviewId.equals(other.interviewId);
    }
}

