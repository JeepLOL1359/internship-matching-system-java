/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

/**
 *
 * @author kitki
 */

import entity.*;
import control.*;
import DAO.LocationDirectory;
import DAO.SkillsDirectory;

import java.util.Scanner;
import adt.LinkedList;

public class ApplicantMenu {
    
   public void handleApplicantSubmenu(Scanner scanner, ApplicantManager applicantManager, 
                                   LinkedList<Location> locations, LinkedList<Skill> skills){
        int choice;
        do {
            System.out.println("\n=== Applicant Manager ===");
            System.out.println("1. View Applicants List");
            System.out.println("2. Add New Applicant");
            System.out.println("3. Update Applicant");
            System.out.println("4. Remove Applicant");
            System.out.println("5. Filter Applicants by Location");
            System.out.println("6. Search Applicant by ID");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    applicantManager.displayAllApplicants();
                    break;
                case 2:
                    applicantManager.handleAddApplicant(scanner, locations, skills);
                    break;
                case 3:
                    applicantManager.handleUpdateApplicant(scanner, locations, skills);
                    break;
                case 4:
                    applicantManager.handleRemoveApplicant(scanner);
                    break;
                case 5:
                    applicantManager.handleFilterByLocation(scanner, locations);
                    break;
                case 6:
                    applicantManager.handleSearchApplicant(scanner);
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }

        } while (choice != 0);
    }
   
    public void handleCompanyApplicantSubmenu(Scanner scanner, ApplicantManager applicantManager, 
                                               LinkedList<Location> locations, LinkedList<Skill> skills) {
        int choice;
        do {
            System.out.println("\n=== Company Applicant Manager ===");
            System.out.println("1. View Applicants List");
            System.out.println("2. Filter Applicants by Location");
            System.out.println("3. Search Applicant by ID");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    applicantManager.displayAllApplicants();
                    break;
                case 2:
                    applicantManager.handleFilterByLocation(scanner, locations);
                    break;
                case 3:
                    applicantManager.handleSearchApplicant(scanner);
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }

        } while (choice != 0);
    }
    
    public void handleStudentApplicantSubmenu(Scanner scanner, ApplicantManager applicantManager, 
                                               LinkedList<Location> locations, LinkedList<Skill> skills) {
        int choice;
        do {
            System.out.println("\n=== Student Applicant Manager ===");
            System.out.println("1. View Applicants List");
            System.out.println("2. Add New Applicant");
            System.out.println("3. Update Applicant");
            System.out.println("4. Remove Applicant");
            System.out.println("5. Search Applicant by ID");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    applicantManager.displayAllApplicants();
                    break;
                case 2:
                    applicantManager.handleAddApplicant(scanner, locations, skills);
                    break;
                case 3:
                    applicantManager.handleUpdateApplicant(scanner, locations, skills);
                    break;
                case 4:
                    applicantManager.handleRemoveApplicant(scanner);
                    break;
                case 5:
                    applicantManager.handleSearchApplicant(scanner);
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }

        } while (choice != 0);
    }

} 
