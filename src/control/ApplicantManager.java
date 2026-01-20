/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

/**
 *
 * @author lyle
 */

import adt.*;
import entity.*;
import java.util.Scanner;

public class ApplicantManager {

    private ListInterface<Applicant> applicantList;

    public ApplicantManager(ListInterface<Applicant> listImplementation) {
        this.applicantList = listImplementation; // Dependency Injection
    }

    // === Core Functionalities (No Scanner) ===
    public void addApplicant(Applicant applicant) {
        applicantList.insertSorted(applicant);
        System.out.println("Applicant added: " + applicant);
    }

    public boolean updateApplicant(String id, Applicant updatedData) {
        Applicant existing = searchApplicantById(id);
        if (existing != null) {
            existing.setName(updatedData.getName());
            existing.setEmail(updatedData.getEmail());
            existing.setPhone(updatedData.getPhone());
            existing.setWorkingExperience(updatedData.getWorkingExperience());
            existing.setQualification(updatedData.getQualification());
            existing.setSkill_1(updatedData.getSkill_1());
            existing.setSkill_2(updatedData.getSkill_2());
            existing.setSkill_3(updatedData.getSkill_3());
            existing.setLocationPreference(updatedData.getLocationPreference());
            System.out.println("Applicant updated: " + existing);
            return true;
        }
        System.out.println("Applicant not found.");
        return false;
    }

    public boolean removeApplicantById(String id) {
        for (int i = 1; i <= applicantList.getNumberOfEntries(); i++) {
            if (applicantList.getEntry(i).getApplicantId().equalsIgnoreCase(id)) {
                applicantList.remove(i);
                System.out.println("Applicant removed successfully.");
                return true;
            }
        }
        System.out.println("Applicant not found.");
        return false;
    }

    public Applicant searchApplicantById(String id) {
        for (int i = 1; i <= applicantList.getNumberOfEntries(); i++) {
            if (applicantList.getEntry(i).getApplicantId().equalsIgnoreCase(id)) {
                return applicantList.getEntry(i);
            }
        }
        return null;
    }

    public ListInterface<Applicant> filterApplicantsByLocation(Location location) {
        ListInterface<Applicant> filtered = new LinkedList<>();
        for (int i = 1; i <= applicantList.getNumberOfEntries(); i++) {
            Applicant app = applicantList.getEntry(i);
            if (location.equals(app.getLocationPreference())) {
                filtered.add(app);
            }
        }
        return filtered;
    }

    public void displayAllApplicants() {
        if (applicantList.isEmpty()) {
            System.out.println("No applicants found.");
        } else {
            for (int i = 1; i <= applicantList.getNumberOfEntries(); i++) {
                System.out.println(applicantList.getEntry(i));
            }
        }
    }

    public ListInterface<Applicant> getAllApplicants() {
        return applicantList;
    }

    // === Interactive Scanner-based I/O Layer ===
    public void handleAddApplicant(Scanner scanner, ListInterface<Location> locations, ListInterface<Skill> skills) {
        System.out.print("Enter Applicant ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Experience (years): ");
        int experience = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Qualification: ");
        String qualification = scanner.nextLine();

        SkillWithProficiency skill1 = getSkillInput(scanner, skills, 1);
        SkillWithProficiency skill2 = getSkillInput(scanner, skills, 2);
        SkillWithProficiency skill3 = getSkillInput(scanner, skills, 3);

        System.out.print("Enter Preferred Location: ");
        Location location = MatchAndSearch.fuzzyMatchLocation(locations, scanner.nextLine());

        addApplicant(new Applicant(id, name, email, phone, experience, qualification, skill1, skill2, skill3, location));
    }

    public void handleUpdateApplicant(Scanner scanner, ListInterface<Location> locations, ListInterface<Skill> skills) {
        System.out.print("Enter Applicant ID to update: ");
        String id = scanner.nextLine();

        Applicant existing = searchApplicantById(id);
        if (existing == null) {
            System.out.println("Applicant not found.");
            return;
        }

        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter New Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter New Experience (years): ");
        int experience = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter New Qualification: ");
        String qualification = scanner.nextLine();

        SkillWithProficiency skill1 = getSkillInput(scanner, skills, 1);
        SkillWithProficiency skill2 = getSkillInput(scanner, skills, 2);
        SkillWithProficiency skill3 = getSkillInput(scanner, skills, 3);

        System.out.print("Enter New Preferred Location: ");
        Location location = MatchAndSearch.fuzzyMatchLocation(locations, scanner.nextLine());

        Applicant updated = new Applicant(id, name, email, phone, experience, qualification, skill1, skill2, skill3, location);
        updateApplicant(id, updated);
    }

    public void handleRemoveApplicant(Scanner scanner) {
        System.out.print("Enter Applicant ID to remove: ");
        String id = scanner.nextLine();
        removeApplicantById(id);
    }

    public void handleSearchApplicant(Scanner scanner) {
        System.out.print("Enter Applicant ID to search: ");
        String id = scanner.nextLine();
        Applicant applicant = searchApplicantById(id);
        if (applicant != null) {
            System.out.println(applicant);
        } else {
            System.out.println("Applicant not found.");
        }
    }

    public void handleFilterByLocation(Scanner scanner, ListInterface<Location> locations) {
        System.out.print("Enter location name: ");
        String locName = scanner.nextLine();
        Location location = MatchAndSearch.fuzzyMatchLocation(locations, locName);

        if (location == null) {
            System.out.println("No matching location found.");
            return;
        }

        ListInterface<Applicant> filtered = filterApplicantsByLocation(location);
        if (filtered.isEmpty()) {
            System.out.println("No applicants found in that location.");
        } else {
            for (int i = 1; i <= filtered.getNumberOfEntries(); i++) {
                System.out.println(filtered.getEntry(i));
            }
        }
    }

    // === Skill Input Helper ===

    private SkillWithProficiency getSkillInput(Scanner scanner, ListInterface<Skill> skills, int num) {
        System.out.print("Enter Skill " + num + ": ");
        Skill skill = MatchAndSearch.fuzzyMatchSkill(skills, scanner.nextLine());

        System.out.print("Enter Skill " + num + " Proficiency (1-4): ");
        int level = scanner.nextInt();
        scanner.nextLine();

        return new SkillWithProficiency(skill, level);
    }
}
