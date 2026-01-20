/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

/**
 *
 * @author kitki
 */

import DAO.*;
import adt.LinkedList;
import adt.ListInterface;
import entity.*;
import control.*;
import java.util.Scanner;

public class ProgramTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // === Load static data ===
        LinkedList<Location> locations = LocationDirectory.getAllDistricts(); 
        LinkedList<Skill> skills = SkillsDirectory.getAllSkills();

        // === Load dynamic data ===
        JobPostingEngine jobPostingEngine = new JobPostingEngine();
        ListInterface<Job> jobList = jobPostingEngine.getAllJobs();
        ListInterface<Job> initializedJobs = new JobInitializer().initializeJobs();
        for (int i = 0; i < initializedJobs.size(); i++) {
            jobList.insertSorted(initializedJobs.get(i));
        }

        LinkedList<Applicant> applicants = (LinkedList<Applicant>) new ApplicantInitializer().initializeApplicants();
        LinkedList<Application> application = (LinkedList<Application>) new ApplicationInitializer().initializeApplications();
        ListInterface<Interview> interviews = new InterviewInitializer().initializeInterviews();

        MatchAndSearch matchingEngine = new MatchAndSearch(applicants, jobPostingEngine.getAllJobs(), application);

        // === Menus ===
        JobPostingMenu jobMenu = new JobPostingMenu();
        ApplicantMenu applicantMenu = new ApplicantMenu();
        InterviewMenu interviewMenu = new InterviewMenu();
        ApplicationMenu applicationMenu = new ApplicationMenu(matchingEngine);
        JobSearchMenu jobSearchMenu = new JobSearchMenu(matchingEngine);
        ApplicantManager applicantManager = new ApplicantManager(applicants);

        boolean exitSystem = false;
        do {
            System.out.println("\n===== Internship Application System =====");
            System.out.println("Select Role:");
            System.out.println("1. Admin");
            System.out.println("2. Student");
            System.out.println("3. Company");
            System.out.println("0. Exit System");
            System.out.print("Enter role: ");
            int roleChoice = scanner.nextInt();
            scanner.nextLine();

            switch (roleChoice) {
                case 1:
                    showAdminMenu(scanner, applicantManager, jobPostingEngine, matchingEngine, interviews, jobMenu, applicantMenu, 
                            interviewMenu, applicationMenu, jobSearchMenu, locations, skills, applicants, application);
                    break;
                case 2:
                    showStudentMenu(scanner, applicantManager, jobPostingEngine, matchingEngine, interviews, jobMenu, applicantMenu, 
                            interviewMenu, applicationMenu, jobSearchMenu, locations, skills, applicants, application);
                    break;
                case 3:
                    showCompanyMenu(scanner, applicantManager, jobPostingEngine, matchingEngine, interviews, jobMenu, applicantMenu, 
                            interviewMenu, applicationMenu, jobSearchMenu, locations, skills, applicants, application);
                    break;
                case 0:
                    exitSystem = true;
                    break;
                default:
                    System.out.println("Invalid role! Returning to role selection...");
            }

        } while (!exitSystem);

        System.out.println("Goodbye! System exited.");
    }

    private static void showAdminMenu(Scanner scanner, ApplicantManager applicantManager, JobPostingEngine jobPostingEngine,
                                      MatchAndSearch matchingEngine, ListInterface<Interview> interviews,
                                      JobPostingMenu jobMenu, ApplicantMenu applicantMenu, InterviewMenu interviewMenu,
                                      ApplicationMenu applicationMenu, JobSearchMenu jobSearchMenu,
                                      ListInterface<Location> locations, ListInterface<Skill> skills,
                                      ListInterface<Applicant> applicants, ListInterface<Application> application) {
        int option;
        do {
            System.out.println("\n===== Admin Menu =====");
            System.out.println("1. View Reports");
            System.out.println("2. Job Management");
            System.out.println("3. Applicant Management");
            System.out.println("4. Interview Management");
            System.out.println("5. Application Management");
            System.out.println("6. Search Jobs");
            System.out.println("0. Logout to Role Selection");
            System.out.print("Enter choice: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                    case 1:
                        handleReports(scanner, applicantManager, jobPostingEngine, matchingEngine, interviews);
                        break;
                    case 2:
                        jobMenu.handleJobSubmenu(jobPostingEngine, locations, skills, scanner);
                        break;
                    case 3: 
                        applicantMenu.handleApplicantSubmenu(scanner, applicantManager, (LinkedList<Location>) locations, (LinkedList<Skill>) skills);
                        break;
                    case 4: 
                        interviewMenu.handleInterviewSubmenu(interviews, application, applicants, jobPostingEngine.getAllJobs(), scanner);
                        break;
                    case 5:
                        applicationMenu.handleApplicationSubmenu(scanner);
                        break;
                    case 6:
                        jobSearchMenu.displayJobSearchMenu(jobPostingEngine.getAllJobs(), applicants, application, scanner);
                        break;
                    case 0: 
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option!");
            }
        } while (option != 0);
    }

    private static void showStudentMenu(Scanner scanner, ApplicantManager applicantManager, JobPostingEngine jobPostingEngine,
                                        MatchAndSearch matchingEngine, ListInterface<Interview> interviews,
                                        JobPostingMenu jobMenu, ApplicantMenu applicantMenu, InterviewMenu interviewMenu,
                                        ApplicationMenu applicationMenu, JobSearchMenu jobSearchMenu,
                                        ListInterface<Location> locations, ListInterface<Skill> skills,
                                        LinkedList<Applicant> applicants, LinkedList<Application> application) {

        int option;
        do {
            System.out.println("\n===== Student Menu =====");
            System.out.println("1. Applicant Management");
            System.out.println("2. Search and Apply Jobs");
            System.out.println("0. Logout to Role Selection");
            System.out.print("Enter choice: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    applicantMenu.handleStudentApplicantSubmenu(scanner, applicantManager, (LinkedList<Location>) locations, (LinkedList<Skill>) skills);
                    break;

                case 2:
                    // Only when choosing Search and Apply Jobs --> Ask for Applicant ID
                    System.out.println("\n===== Applicant Login =====");
                    System.out.print("Enter your Applicant ID: ");
                    String applicantId = scanner.nextLine();

                    Applicant loggedInApplicant = null;
                    for (int i = 0; i < applicants.size(); i++) {
                        Applicant a = applicants.get(i);
                        if (a.getApplicantId().equalsIgnoreCase(applicantId)) {
                            loggedInApplicant = a;
                            break;
                        }
                    }

                    if (loggedInApplicant == null) {
                        System.out.println("Applicant ID not found! Returning to Student Menu...");
                    } else {
                        System.out.println("Login successful. Welcome, " + loggedInApplicant.getName() + "!");

                        // Create a temp LinkedList for this student
                        LinkedList<Applicant> tempStudent = new LinkedList<>();
                        tempStudent.add(loggedInApplicant);

                        // Proceed to job search
                        jobSearchMenu.displayJobSearchMenu(jobPostingEngine.getAllJobs(), tempStudent, application, scanner);
                    }
                    break;

                case 0:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option!");
            }
        } while (option != 0);
    }

    private static void showCompanyMenu(Scanner scanner, ApplicantManager applicantManager, JobPostingEngine jobPostingEngine,
                                      MatchAndSearch matchingEngine, ListInterface<Interview> interviews,
                                      JobPostingMenu jobMenu, ApplicantMenu applicantMenu, InterviewMenu interviewMenu,
                                      ApplicationMenu applicationMenu, JobSearchMenu jobSearchMenu,
                                      ListInterface<Location> locations, ListInterface<Skill> skills,
                                      ListInterface<Applicant> applicants, ListInterface<Application> application) {
        int option;
        do {
            System.out.println("\n===== Company Menu =====");
            System.out.println("1. Job Management");
            System.out.println("2. Find Applicants");
            System.out.println("0. Logout to Role Selection");
            System.out.print("Enter choice: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    jobMenu.handleJobSubmenu(jobPostingEngine, locations, skills, scanner);
                    break;
                case 2:
                    applicantMenu.handleCompanyApplicantSubmenu(scanner, applicantManager, (LinkedList<Location>) locations, (LinkedList<Skill>) skills);
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        } while (option != 0);
    }

    private static void handleReports(Scanner scanner, ApplicantManager applicantManager, JobPostingEngine jobPostingEngine,
                                      MatchAndSearch matchingEngine, ListInterface<Interview> interviews) {
        int reportOption;
        do {
            System.out.println("\n=== Reporting Dashboard ===");
            System.out.println("1. View Applicants List");
            System.out.println("2. View Jobs List");
            System.out.println("3. View Applications List");
            System.out.println("4. View Interview List");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");
            reportOption = scanner.nextInt();
            scanner.nextLine();

            switch (reportOption) {
                case 1:
                    applicantManager.displayAllApplicants(); 
                    break;
                case 2:
                    jobPostingEngine.displayAllJobs();
                    break;
                case 3:
                    matchingEngine.displayAllApplications();
                    break;
                case 4:
                    InterviewSchedulerEngine.handleViewInterviewList(interviews);
                    break;
                case 0:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (reportOption != 0);
    }
}
