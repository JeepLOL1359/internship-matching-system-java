/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

/**
 *
 * @author kitki
 */

import adt.ListInterface;
import adt.LinkedList;
import control.MatchAndSearch;
import DAO.SkillsDirectory;
import entity.*;
import java.time.LocalDate;

import java.util.Scanner;

public class JobSearchMenu {

    private MatchAndSearch matchingEngine;

    public JobSearchMenu(MatchAndSearch matchingEngine) {
        this.matchingEngine = matchingEngine;
    }

    public void displayJobSearchMenu(ListInterface<Job> jobs, ListInterface<Applicant> applicants,
                                     ListInterface<Application> applications, Scanner scanner) {
        
        boolean exact = false;
        int choice;
        do {
            System.out.println("\n=== Job Search & Match Menu ===");
            System.out.println("1. View Applications List");
            System.out.println("2. Search and Match by Job ID");
            System.out.println("3. Search and Match by Required Skill");
            System.out.println("4. Search and Match by Location");
            System.out.println("5. Search and Match by Keyword");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    matchingEngine.displayAllApplications();
                    break;
                case 2:
                    matchingEngine.searchJobs(scanner, "id", exact, applicants);
                    break;
                case 3:
                    matchingEngine.searchJobs(scanner, "skill", exact, applicants);
                    break;
                case 4:
                    matchingEngine.searchJobs(scanner, "location", exact, applicants);
                    break;
                case 5:
                    matchingEngine.searchJobs(scanner, "keyword", exact, applicants);
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }
   
}
