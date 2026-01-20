/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

/**
 *
 * @author Jordon
 */

import java.util.Scanner;
import adt.LinkedList;
import adt.ListInterface; 
import entity.*;

public class InterviewSchedulerEngine {

    private static int interviewCounter = 1;

    public static void handleScheduleInterviews(ListInterface<Application> applications,
                                                 ListInterface<Applicant> applicants,
                                                 ListInterface<Job> jobs,
                                                 ListInterface<Interview> interviews,
                                                 Scanner scanner) {
        if (applications.isEmpty()) {
            System.out.println("No applications found. Please match applicants to jobs first (Option 4).\n");
        } else {
            scheduleInterviews(applications, applicants, jobs, interviews, scanner);
        }
    }

    public static void handleUpdateInterviewResults(ListInterface<Interview> interviews,
                                                    ListInterface<Applicant> applicants,
                                                    Scanner scanner) {
        if (interviews.isEmpty()) {
            System.out.println("No interviews found to update. Please schedule interviews first (Option 5).\n");
        } else {
            updateInterviewResults(interviews, applicants, scanner);
        }
    }

    public static void handleListSuccessfulApplicants(ListInterface<Interview> interviews,
                                                      ListInterface<Application> applications, ListInterface<Applicant> applicants) {
        if (applications.isEmpty()) {
            System.out.println("No applications found. Please match applicants to jobs first (Option 4).\n");
        } else {
            listSuccessfulApplicants(interviews, applications, applicants);
        }
    }

    public static void handleViewInterviewList(ListInterface<Interview> interviews) {
        if (interviews.isEmpty()) {
            System.out.println("No interviews scheduled yet.\n");
        } else {
            System.out.println("\n=== All Scheduled Interviews ===");
            for (int i = 1; i <= interviews.getNumberOfEntries(); i++) {
                System.out.println(interviews.getEntry(i));
            }
        }
    }

    public static void scheduleInterviews(ListInterface<Application> applications, ListInterface<Applicant> applicants, ListInterface<Job> jobs, ListInterface<Interview> interviews, Scanner scanner) {
        for (int i = 0; i < applications.size(); i++) {
            Application app = applications.get(i);
            Applicant applicant = findApplicantById(applicants, app.getApplicantId());
            Job job = findJobById(jobs, app.getJobId());

            if (applicant != null && job != null) {
                boolean alreadyInterviewed = false;
                for (int j = 0; j < interviews.getNumberOfEntries(); j++) {
                    Interview existing = interviews.getEntry(j + 1);
                    if (
                        existing.getApplicant().getApplicantId().equals(applicant.getApplicantId()) &&
                        existing.getJob().getJobId().equals(job.getJobId()) &&
                        !existing.getStatus().equalsIgnoreCase("Scheduled")
                    ) {
                        alreadyInterviewed = true;
                        break;
                    }
                }

                if (alreadyInterviewed) {
                    System.out.println("Applicant " + applicant.getApplicantId() + " has already been interviewed for job " + job.getJobId() + ". Skipping.");
                    continue; // skip to next application
                }

                System.out.println("\nInterview for: " + applicant.getName() + " - " + job.getTitle());

                String date;
                do {
                    System.out.print("Enter interview date (YYYY-MM-DD): ");
                    date = scanner.nextLine();
                    if (!utility.FormatTime.isValidDate(date)) {
                        System.out.println("Invalid date format. Please enter in YYYY-MM-DD.");
                    }
                } while (!utility.FormatTime.isValidDate(date));

                String time;
                do {
                    System.out.print("Enter interview time (HH:mm): ");
                    time = scanner.nextLine();
                    if (!utility.FormatTime.isValidTime(time)) {
                        System.out.println("Invalid time format. Please enter in HH:mm (24h).");
                    }
                } while (!utility.FormatTime.isValidTime(time));

                String interviewId = "INT" + String.format("%03d", interviewCounter++);
                Interview interview = new Interview(interviewId, applicant, job, date, time, "Scheduled", applicant.getWorkingExperience());

                interviews.insertSorted(interview);
                System.out.println("Scheduled: " + interview);
            }
        }
    }

    public static void updateInterviewResults(ListInterface<Interview> interviews, ListInterface<Applicant> applicants, Scanner scanner) {
        for (int i = 0; i < interviews.size(); i++) {
            Interview interview = interviews.get(i);

            if (interview.getStatus().equals("Scheduled")) {
                System.out.println("\n" + interview);

                String status;
                boolean valid = false;

                do {
                    System.out.print("Enter result (Passed/Rejected): ");
                    status = scanner.nextLine();

                    if (status.equalsIgnoreCase("Passed") || status.equalsIgnoreCase("Rejected")) {
                        status = status.substring(0, 1).toUpperCase() + status.substring(1).toLowerCase();
                        interview.setStatus(status);
                        valid = true;
                        System.out.println("Status updated to: " + interview.getStatus());
                    } else {
                        System.out.println("Invalid status. Please enter only 'Passed' or 'Rejected'.");
                    }
                } while (!valid);
            }
        }
    }

    public static void listSuccessfulApplicants(ListInterface<Interview> interviews, ListInterface<Application> applications, ListInterface<Applicant> applicants) {
        System.out.println("\n=== Successful Applicants Ranked by Match Score ===");
        ListInterface<String> printedJobs = new LinkedList<>();

        for (int i = 0; i < interviews.size(); i++) {
            Interview interview = interviews.get(i);
            if (interview.getStatus().equalsIgnoreCase("Passed")) {
                String jobId = interview.getJob().getJobId();
                if (!printedJobs.contains(jobId)) {
                    printedJobs.add(jobId);
                    System.out.println("\n--- Job ID: " + jobId + " ---");

                    ListInterface<Application> ranked = new LinkedList<>();
                    for (int j = 0; j < interviews.size(); j++) {
                        Interview intv = interviews.get(j);
                        if (intv.getStatus().equalsIgnoreCase("Passed") && intv.getJob().getJobId().equals(jobId)) {
                            Application app = findApplication(applications, intv.getApplicant().getApplicantId(), jobId);
                            if (app != null) {
                                int pos = 1;
                                for (int k = 1; k <= ranked.getNumberOfEntries(); k++) {
                                    if (app.getMatchScore() < ranked.getEntry(k).getMatchScore()) {
                                        pos = k + 1;
                                    } else {
                                        break;
                                    }
                                }
                                ranked.add(pos, app);
                            }
                        }
                    }

                    for (int m = 1; m <= ranked.getNumberOfEntries(); m++) {
                        Application app = ranked.getEntry(m);
                        Applicant applicant = findApplicantById(applicants, app.getApplicantId());
                        String name = (applicant != null) ? applicant.getName() : "Unknown";
                        System.out.println("Applicant: " + app.getApplicantId() + " (" + name + ") | Job: " + jobId + " | Match Score: " + app.getMatchScore());
                    }
                }
            }
        }
    }

    private static Applicant findApplicantById(ListInterface<Applicant> applicants, String id) {
        for (int i = 0; i < applicants.size(); i++) {
            if (applicants.get(i).getApplicantId().equals(id)) return applicants.get(i);
        }
        return null;
    }

    private static Job findJobById(ListInterface<Job> jobs, String id) {
        for (int i = 0; i < jobs.size(); i++) {
            if (jobs.get(i).getJobId().equals(id)) return jobs.get(i);
        }
        return null;
    }

    private static Application findApplication(ListInterface<Application> apps, String applicantId, String jobId) {
        for (int i = 0; i < apps.size(); i++) {
            Application app = apps.get(i);
            if (app.getApplicantId().equals(applicantId) && app.getJobId().equals(jobId)) return app;
        }
        return null;
    }
}
