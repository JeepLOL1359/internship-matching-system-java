/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author kevin
 */

import adt.ListInterface;
import adt.LinkedList;
import control.JobPostingEngine;
import control.MatchAndSearch;
import entity.*;

public class JobInitializer {

    public ListInterface<Job> initializeJobs() {
        JobPostingEngine jobEngine = new JobPostingEngine();

        // Fetch all predefined locations and skills
        LinkedList<Location> locations = LocationDirectory.getAllDistricts();
        LinkedList<Skill> skills = SkillsDirectory.getAllSkills();

        // Skill lookup
        Skill java = MatchAndSearch.findSkillByName(skills, "Java");
        Skill sql = MatchAndSearch.findSkillByName(skills, "SQL");
        Skill python = MatchAndSearch.findSkillByName(skills, "Python");
        Skill html = MatchAndSearch.findSkillByName(skills, "HTML");
        Skill cplusplus = MatchAndSearch.findSkillByName(skills, "C++");

        // Location lookup
        Location gombak = MatchAndSearch.findLocationByName(locations, "Gombak"); 
        Location klang = MatchAndSearch.findLocationByName(locations, "Klang"); 
        Location klSelangor = MatchAndSearch.findLocationByName(locations, "Kuala Selangor"); 
        Location petaling = MatchAndSearch.findLocationByName(locations, "Petaling"); 
        Location uluSelangor = MatchAndSearch.findLocationByName(locations, "Ulu Selangor");
        Location johorBahru = MatchAndSearch.findLocationByName(locations, "Johor Bahru");
        Location kluang = MatchAndSearch.findLocationByName(locations, "Kluang");
        Location kotaTinggi = MatchAndSearch.findLocationByName(locations, "Kota Tinggi"); 
        Location kulai = MatchAndSearch.findLocationByName(locations, "Kulai");
        Location muar = MatchAndSearch.findLocationByName(locations, "Muar");   
        Location segamat = MatchAndSearch.findLocationByName(locations, "Segamat"); 
        Location kualaLumpur = MatchAndSearch.findLocationByName(locations, "Kuala Lumpur");  
        Location putrajaya = MatchAndSearch.findLocationByName(locations, "Putrajaya"); 

        // Create jobs using your engine
        jobEngine.createJob("J001", "Software Engineer", "Tech Titan", "Software Engineer needed at Tech Titan",
                new SkillWithProficiency(java, 2),
                new SkillWithProficiency(sql, 2),
                new SkillWithProficiency(python, 2),
                gombak, 3000, 2);

        jobEngine.createJob("J002", "Backend Developer", "Tech Titan", "Backend Developer needed at Tech Titan",
                new SkillWithProficiency(java, 2),
                new SkillWithProficiency(sql, 2),
                new SkillWithProficiency(python, 3),
                gombak, 3200, 1);

        jobEngine.createJob("J003", "Frontend Developer", "Tech Titan", "Frontend Developer needed at Tech Titan",
                new SkillWithProficiency(java, 4),
                new SkillWithProficiency(sql, 4),
                new SkillWithProficiency(python, 1),
                petaling, 3500, 3);

        jobEngine.createJob("J004", "System Analyst", "Tech Titan", "System Analyst needed at Tech Titan",
                new SkillWithProficiency(java, 4),
                new SkillWithProficiency(sql, 4),
                new SkillWithProficiency(python, 1),
                johorBahru, 4000, 4);

        jobEngine.createJob("J005", "Intern Developer", "Tech Titan", "Intern Developer needed at Tech Titan",
                new SkillWithProficiency(java, 4),
                new SkillWithProficiency(sql, 4),
                new SkillWithProficiency(python, 1),
                muar, 2500, 0);
        
        jobEngine.createJob("J006", "Mobile App Developer", "AppMasters", "Build cutting-edge mobile apps for Android and iOS",
                new SkillWithProficiency(java, 3),
                new SkillWithProficiency(python, 2),
                new SkillWithProficiency(sql, 2),
                klang, 3300, 2);

        jobEngine.createJob("J007", "Full Stack Developer", "Stackify", "Looking for a well-rounded full stack developer",
                new SkillWithProficiency(java, 4),
                new SkillWithProficiency(sql, 4),
                new SkillWithProficiency(html, 4),
                putrajaya, 4500, 4);

        jobEngine.createJob("J008", "Data Analyst", "DataWiz", "Analyze data and deliver business insights",
                new SkillWithProficiency(python, 4),
                new SkillWithProficiency(sql, 4),
                new SkillWithProficiency(html, 3),
                kualaLumpur, 4200, 3);

        jobEngine.createJob("J009", "Cybersecurity Specialist", "SecureNet", "Ensure system security and threat response",
                new SkillWithProficiency(python, 3),
                new SkillWithProficiency(sql, 2),
                new SkillWithProficiency(cplusplus, 3),
                kluang, 4700, 4);

        jobEngine.createJob("J010", "Junior Frontend Developer", "CreativeBytes", "Help design beautiful UI",
                new SkillWithProficiency(html, 3),
                new SkillWithProficiency(java, 2),
                new SkillWithProficiency(sql, 2),
                kotaTinggi, 2800, 1);

        jobEngine.createJob("J011", "Game Developer", "FunForge", "Develop exciting cross-platform games",
                new SkillWithProficiency(cplusplus, 4),
                new SkillWithProficiency(java, 3),
                new SkillWithProficiency(sql, 2),
                segamat, 5000, 5);

        jobEngine.createJob("J012", "AI Developer", "NeuroNet", "Work on state-of-the-art AI systems",
                new SkillWithProficiency(python, 4),
                new SkillWithProficiency(java, 3),
                new SkillWithProficiency(sql, 3),
                kulai, 6000, 4);

        jobEngine.createJob("J013", "Software Tester", "BugSquashers", "Test enterprise software systems",
                new SkillWithProficiency(sql, 3),
                new SkillWithProficiency(java, 2),
                new SkillWithProficiency(python, 2),
                uluSelangor, 3200, 2);

        jobEngine.createJob("J014", "Database Administrator", "DBGuard", "Manage large-scale production databases",
                new SkillWithProficiency(sql, 4),
                new SkillWithProficiency(python, 2),
                new SkillWithProficiency(cplusplus, 1),
                klSelangor, 4800, 3);

        jobEngine.createJob("J015", "Web Developer", "WebCraft", "Develop websites and web applications",
                new SkillWithProficiency(html, 4),
                new SkillWithProficiency(java, 3),
                new SkillWithProficiency(sql, 3),
                putrajaya, 3900, 3);

        return jobEngine.getAllJobs(); // Return the list to save into DAO
    }

    public static void main(String[] args) {
        JobInitializer initializer = new JobInitializer();
        ListInterface<Job> jobs = initializer.initializeJobs();
        new JobDAO().saveToFile(jobs);
        System.out.println("Jobs saved successfully.");
    }
}
