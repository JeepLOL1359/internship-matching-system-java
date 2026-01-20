/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author kitki
 */

import adt.LinkedList;
import adt.ListInterface;
import control.ApplicantManager;
import entity.Applicant;
import entity.Location;
import entity.Skill;
import entity.SkillWithProficiency;
import control.MatchAndSearch;

/**
 *
 * @author kitki
 */
public class ApplicantInitializer {

    public ListInterface<Applicant> initializeApplicants() {
        ListInterface<Applicant> applicants = new LinkedList<>();
        ApplicantManager manager = new ApplicantManager(applicants);

        // Predefined skills and locations
        LinkedList<Skill> skills = SkillsDirectory.getAllSkills();
        LinkedList<Location> locations = LocationDirectory.getAllDistricts();

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
        
        // Get all predefined skills
        Skill java = MatchAndSearch.findSkillByName(skills, "Java");
        Skill sql = MatchAndSearch.findSkillByName(skills, "SQL");
        Skill python = MatchAndSearch.findSkillByName(skills, "Python");
        Skill html = MatchAndSearch.findSkillByName(skills, "HTML");
        Skill cplusplus = MatchAndSearch.findSkillByName(skills, "C++");

        manager.addApplicant(new Applicant("A001", "John", "john@mail.com", "011-1111111", 3, "Diploma",
                new SkillWithProficiency(java, 4), new SkillWithProficiency(sql, 4), new SkillWithProficiency(python, 4), gombak));

        manager.addApplicant(new Applicant("A002", "Jane", "jane@mail.com", "011-2222222", 2, "Degree",
                new SkillWithProficiency(java, 1), new SkillWithProficiency(sql, 1), new SkillWithProficiency(python, 2), muar));

        manager.addApplicant(new Applicant("A003", "Jack", "jack@mail.com", "011-3333333", 3, "Degree",
                new SkillWithProficiency(java, 4), new SkillWithProficiency(sql, 4), new SkillWithProficiency(python, 1), kualaLumpur));

        manager.addApplicant(new Applicant("A004", "Alice", "alice@mail.com", "011-4444444", 2, "Diploma",
                new SkillWithProficiency(java, 2), new SkillWithProficiency(sql, 2), new SkillWithProficiency(python, 3), gombak));

        manager.addApplicant(new Applicant("A005", "Brian", "brian@mail.com", "011-5555555", 1, "Degree",
                new SkillWithProficiency(python, 4), new SkillWithProficiency(html, 2), new SkillWithProficiency(cplusplus, 2), petaling));

        manager.addApplicant(new Applicant("A006", "Chris", "chris@mail.com", "011-6666666", 1, "Diploma",
                new SkillWithProficiency(java, 2), new SkillWithProficiency(python, 1), new SkillWithProficiency(sql, 1), johorBahru));

        manager.addApplicant(new Applicant("A007", "Diana", "diana@mail.com", "011-7777777", 4, "Master",
                new SkillWithProficiency(sql, 4), new SkillWithProficiency(cplusplus, 4), new SkillWithProficiency(html, 3), klang));

        manager.addApplicant(new Applicant("A008", "Edward", "edward@mail.com", "011-8888888", 3, "Degree",
                new SkillWithProficiency(java, 4), new SkillWithProficiency(cplusplus, 3), new SkillWithProficiency(html, 2), putrajaya));

        manager.addApplicant(new Applicant("A009", "Fiona", "fiona@mail.com", "011-9999999", 2, "Diploma",
                new SkillWithProficiency(sql, 2), new SkillWithProficiency(python, 2), new SkillWithProficiency(cplusplus, 2), segamat));

        manager.addApplicant(new Applicant("A010", "George", "george@mail.com", "011-1010101", 5, "Master",
                new SkillWithProficiency(java, 4), new SkillWithProficiency(sql, 3), new SkillWithProficiency(html, 3), kluang));

        manager.addApplicant(new Applicant("A011", "Hannah", "hannah@mail.com", "011-1111222", 0, "Diploma",
                new SkillWithProficiency(html, 1), new SkillWithProficiency(sql, 1), new SkillWithProficiency(python, 1), kotaTinggi));

        manager.addApplicant(new Applicant("A012", "Ian", "ian@mail.com", "011-2222333", 1, "Degree",
                new SkillWithProficiency(java, 2), new SkillWithProficiency(sql, 2), new SkillWithProficiency(cplusplus, 1), kulai));

        manager.addApplicant(new Applicant("A013", "Jenny", "jenny@mail.com", "011-3333444", 2, "Degree",
                new SkillWithProficiency(python, 3), new SkillWithProficiency(html, 4), new SkillWithProficiency(cplusplus, 3), klSelangor));

        manager.addApplicant(new Applicant("A014", "Kyle", "kyle@mail.com", "011-4444555", 3, "Degree",
                new SkillWithProficiency(java, 3), new SkillWithProficiency(sql, 2), new SkillWithProficiency(python, 1), uluSelangor));

        manager.addApplicant(new Applicant("A015", "Lily", "lily@mail.com", "011-5555666", 4, "Master",
                new SkillWithProficiency(java, 4), new SkillWithProficiency(sql, 4), new SkillWithProficiency(python, 4), putrajaya));


        return applicants;
    }

    public static void main(String[] args) {
        ApplicantInitializer init = new ApplicantInitializer();
        ListInterface<Applicant> list = init.initializeApplicants();
        System.out.println("Applicants:\n" + list);
    }
}

