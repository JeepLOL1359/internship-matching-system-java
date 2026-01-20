/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author kevin
 */
public class Job implements Comparable<Job> {
    private String jobId;
    private String title;
    private String companyName;
    private String description;
    private SkillWithProficiency requiredSkills_1;
    private SkillWithProficiency requiredSkills_2;    
    private SkillWithProficiency requiredSkills_3;
    private Location location;
    private double salary; //In ringgit
    private int requiredExperience; //In years
    
    public Job() {
    }

    public Job(String jobId, String title, String companyName, String description, Location location, double salary, int requiredExperience) {
        this.jobId = jobId;
        this.title = title;
        this.companyName = companyName;
        this.description = description;
        this.location = location;
        this.salary = salary;
        this.requiredExperience = requiredExperience;
    }
        
    public Job(String jobId, String title, String companyName, String description, 
            SkillWithProficiency requiredSkills_1, SkillWithProficiency requiredSkills_2, 
            SkillWithProficiency requiredSkills_3, Location location, 
            double salary, int requiredExperience) {
        this.jobId = jobId;
        this.title = title;
        this.companyName = companyName;
        this.description = description;
        this.requiredSkills_1= requiredSkills_1;
        this.requiredSkills_2= requiredSkills_2;
        this.requiredSkills_3= requiredSkills_3;
        this.location = location;
        this.salary = salary;
        this.requiredExperience = requiredExperience;
    }

    // Getters and Setters
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SkillWithProficiency getRequiredSkills_1() {
        return requiredSkills_1;
    }

    public void setRequiredSkills_1(SkillWithProficiency requiredSkills_1) {
        this.requiredSkills_1 = requiredSkills_1;
    }

    public SkillWithProficiency getRequiredSkills_2() {
        return requiredSkills_2;
    }

    public void setRequiredSkills_2(SkillWithProficiency requiredSkills_2) {
        this.requiredSkills_2 = requiredSkills_2;
    }

    public SkillWithProficiency getRequiredSkills_3() {
        return requiredSkills_3;
    }

    public void setRequiredSkills_3(SkillWithProficiency requiredSkills_3) {
        this.requiredSkills_3 = requiredSkills_3;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getRequiredExperience() {
        return requiredExperience;
    }

    public void setRequiredExperience(int requiredExperience) {
        this.requiredExperience = requiredExperience;
    }

    

    @Override
    public int compareTo(Job other) {
        return this.jobId.compareToIgnoreCase(other.jobId);
    }
    
    @Override
    public String toString() {
        return String.format(
            "\nJob ID: %s\n" +
            "Title: %s\n" +
            "Company: %s\n" +
            "Description: %s\n" +
            "Company Location: %s\n" +
            "Required Experience: %d years\n" +
            "Salary: RM %.2f\n" +
            "Required Skill 1: %s\n" +
            "Required Skill 2: %s\n" +
            "Required Skill 3: %s\n",
            jobId,
            title,
            companyName,
            description,
            location != null ? location.getName() : "N/A",
            requiredExperience,
            salary,
            requiredSkills_1 != null ? requiredSkills_1 : "None",
            requiredSkills_2 != null ? requiredSkills_2 : "None",
            requiredSkills_3 != null ? requiredSkills_3 : "None"
        );
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Job)) return false;
        Job other = (Job) obj;
        return jobId.equals(other.jobId);
    }
}