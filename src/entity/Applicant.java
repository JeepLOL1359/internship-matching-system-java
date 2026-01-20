/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author kitki
 */

public class Applicant implements Comparable<Applicant> {
    private String applicantId;
    private String name;
    private String email;
    private String phone;
    private int workingExperience;
    private String qualification;
    private SkillWithProficiency skill_1;
    private SkillWithProficiency skill_2;
    private SkillWithProficiency skill_3;
    private Location locationPreference; 

    public Applicant() {
    }

    public Applicant(String applicantId, String name, String email, String phone, int workingExperience, String qualification, Location locationPreference) {
        this.applicantId = applicantId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.workingExperience = workingExperience;
        this.qualification = qualification;
        this.locationPreference = locationPreference;
    }
    
    public Applicant(String applicantId, String name, String email, String phone, int workingExperience, 
            String qualification, SkillWithProficiency skill_1, SkillWithProficiency skill_2, 
            SkillWithProficiency skill_3,Location locationPreference) {
        this.applicantId = applicantId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.workingExperience = workingExperience;
        this.qualification = qualification;
        this.skill_1= skill_1;
        this.skill_2= skill_2;
        this.skill_3= skill_3;
        this.locationPreference = locationPreference;
    }

    // Getters and Setters

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getWorkingExperience() {
        return workingExperience;
    }

    public void setWorkingExperience(int workingExperience) {
        this.workingExperience = workingExperience;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public SkillWithProficiency getSkill_1() {
        return skill_1;
    }

    public void setSkill_1(SkillWithProficiency skill_1) {
        this.skill_1 = skill_1;
    }

    public SkillWithProficiency getSkill_2() {
        return skill_2;
    }

    public void setSkill_2(SkillWithProficiency skill_2) {
        this.skill_2 = skill_2;
    }

    public SkillWithProficiency getSkill_3() {
        return skill_3;
    }

    public void setSkill_3(SkillWithProficiency skill_3) {
        this.skill_3 = skill_3;
    }

    public Location getLocationPreference() {
        return locationPreference;
    }

    public void setLocationPreference(Location locationPreference) {
        this.locationPreference = locationPreference;
    }

    
    @Override
    public int compareTo(Applicant other) {
        return this.applicantId.compareToIgnoreCase(other.applicantId);
    }
    
    @Override
    public String toString() {
        return "Applicant ID: " + applicantId + ", Name: " + name + ", Preferred Location: " + locationPreference;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Applicant)) return false;
        Applicant other = (Applicant) obj;
        return applicantId.equals(other.applicantId);
    }
}
