/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.LinkedList;
import adt.ListInterface;
import control.JobPostingEngine;
import control.MatchAndSearch;
import entity.Job;
import entity.Location;
import entity.Skill;
import entity.SkillWithProficiency;
import java.util.Scanner;

public class JobPostingMenu {

    public void handleJobSubmenu(JobPostingEngine jobEngine, ListInterface<Location> locations, ListInterface<Skill> skills, Scanner scanner) {
        int jobOption;
        do {
            System.out.println("\n=== Job Manager ===");
            System.out.println("1. View Registered Jobs List");
            System.out.println("2. Create New Job");
            System.out.println("3. Update Job");
            System.out.println("4. Remove Job");
            System.out.println("5. Filter Jobs");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");
            jobOption = scanner.nextInt();
            scanner.nextLine();

            switch (jobOption) {
                case 1:
                    jobEngine.displayAllJobs();
                    break;
                case 2:
                    jobEngine.handleCreateJob(scanner, locations, skills);
                    break;
                case 3:
                    jobEngine.handleUpdateJob(scanner, locations, skills);
                    break;
                case 4:
                    jobEngine.handleRemoveJob(scanner);
                    break;
                case 5:
                    handleFilterJobs(jobEngine,scanner, locations);
                    break;
                case 0:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (jobOption != 0);
    }
    
    public void handleFilterJobs(JobPostingEngine jobEngine, Scanner scanner, ListInterface<Location> locations) {
        int option;
        do {
            System.out.println("\n--- Filter Jobs ---");
            System.out.println("1. By Location");
            System.out.println("2. By Company");
            System.out.println("3. By Salary Range");
            System.out.println("0. Return");
            System.out.print("Enter choice: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                {
                    System.out.print("Enter location name: ");
                    String locInput = scanner.nextLine();
                    Location location = MatchAndSearch.fuzzyMatchLocation(locations, locInput);
                    if (location != null) {
                        ListInterface<Job> results = jobEngine.filterJobsByLocation(location.getName());
                        jobEngine.printJobs(results);
                    } else {
                        System.out.println("No matching location found.");
                    }
                }
                break;
                case 2: 
                {
                    System.out.print("Enter company name: ");
                    String companyInput = scanner.nextLine();
                    ListInterface<Job> results = jobEngine.filterJobsByCompany(companyInput);
                    jobEngine.printJobs(results);
                }
                break;
                case 3:
                {
                    System.out.print("Enter minimum salary: ");
                    double min = scanner.nextDouble();
                    System.out.print("Enter maximum salary: ");
                    double max = scanner.nextDouble();
                    scanner.nextLine();
                    ListInterface<Job> results = jobEngine.filterJobsBySalaryRange(min, max);
                    jobEngine.printJobs(results);
                }
                break;
                case 0:
                    System.out.println("Returning to main menu.");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        } while (option != 0);
    }


}