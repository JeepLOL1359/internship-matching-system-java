package boundary;

import adt.*;
import control.InterviewSchedulerEngine;
import entity.*;
import java.util.Scanner;

public class InterviewMenu {
    public void handleInterviewSubmenu(ListInterface<Interview> interviews,
                                   ListInterface<Application> applications,
                                   ListInterface<Applicant> applicants,
                                   ListInterface<Job> jobs,
                                   Scanner scanner) {

        int applicantOption;
        do {
            System.out.println("\n=== Interview Scheduler and Manager ===");
            System.out.println("1. View Interview List");
            System.out.println("2. Schedule Interviews");
            System.out.println("3. Update Interview Results");
            System.out.println("4. List Successful Applicants");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter choice: ");
            applicantOption = scanner.nextInt();
            scanner.nextLine();

            switch (applicantOption) {
                case 1:
                    InterviewSchedulerEngine.handleViewInterviewList(interviews);
                    break;
                case 2:
                    InterviewSchedulerEngine.handleScheduleInterviews(applications, applicants, jobs, interviews, scanner);
                    break;
                case 3:
                    InterviewSchedulerEngine.handleUpdateInterviewResults(interviews, applicants, scanner);
                    break;
                case 4:
                    InterviewSchedulerEngine.handleListSuccessfulApplicants(interviews, applications, applicants);
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (applicantOption != 0);
    }    

}
