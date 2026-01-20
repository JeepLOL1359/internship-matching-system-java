/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

/**
 *
 * @author kevin
 */
import adt.*;
import entity.*;
import java.util.Scanner;

public class JobPostingEngine {

    private ListInterface<Job> jobList;

    public JobPostingEngine() {
        jobList = new LinkedList<>();
    }

    // === Reuse Previous Core Job CRUD Methods ===
    public void createJob(String jobId, String title, String companyName, String description,
                          SkillWithProficiency requiredSkill1, SkillWithProficiency requiredSkill2,
                          SkillWithProficiency requiredSkill3, Location location, double salary, int requiredExperience) {
        Job newJob = new Job(jobId, title, companyName, description, requiredSkill1, requiredSkill2, requiredSkill3, location, salary, requiredExperience);
        jobList.insertSorted(newJob);
        System.out.println("Job created successfully: " + newJob);
    }

    public void updateJob(String jobId, String title, String companyName, String description,
                           SkillWithProficiency requiredSkill1, SkillWithProficiency requiredSkill2,
                           SkillWithProficiency requiredSkill3, Location location, double salary, int requiredExperience) {
        for (int i = 1; i <= jobList.getNumberOfEntries(); i++) {
            if (jobList.getEntry(i).getJobId().equals(jobId)) {
                jobList.replace(i, new Job(jobId, title, companyName, description, requiredSkill1, requiredSkill2, requiredSkill3, location, salary, requiredExperience));
                System.out.println("Job updated successfully.");
                return;
            }
        }
        System.out.println("Job not found.");
    }

    public void removeJob(String jobId) {
        for (int i = 1; i <= jobList.getNumberOfEntries(); i++) {
            if (jobList.getEntry(i).getJobId().equalsIgnoreCase(jobId)) {
                jobList.remove(i);
                System.out.println("Job removed successfully with ID: " + jobId);
                return;
            }
        }
        System.out.println("Job not found with ID: " + jobId);
    }

    public void displayAllJobs() {
        for (int i = 1; i <= jobList.getNumberOfEntries(); i++) {
            System.out.println(jobList.getEntry(i));
        }
    }
    
    public ListInterface<Job> getAllJobs() {
        return jobList;
    }

    public Job searchByJobId(String jobId) {
        for (int i = 1; i <= jobList.getNumberOfEntries(); i++) {
            if (jobList.getEntry(i).getJobId().equalsIgnoreCase(jobId)) {
                return jobList.getEntry(i);
            }
        }
        return null;
    }

    // === Interactive Scanner-based I/O Layer ===
    public void handleCreateJob(Scanner scanner, ListInterface<Location> locations, ListInterface<Skill> skills) {
        System.out.println("\n--- Create New Job ---");
        System.out.print("Enter Job ID: ");
        String jobId = scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Company Name: ");
        String company = scanner.nextLine();
        System.out.print("Enter Description: ");
        String desc = scanner.nextLine();

        SkillWithProficiency swp1 = getSkillWithProficiency(scanner, skills, "Skill 1");
        SkillWithProficiency swp2 = getSkillWithProficiency(scanner, skills, "Skill 2");
        SkillWithProficiency swp3 = getSkillWithProficiency(scanner, skills, "Skill 3");

        System.out.print("Enter Job Location: ");
        Location location = MatchAndSearch.fuzzyMatchLocation(locations, scanner.nextLine());

        double salary = getValidatedSalary(scanner);
        System.out.print("Enter Required Experience (years): ");
        int exp = scanner.nextInt();
        scanner.nextLine();

        createJob(jobId, title, company, desc, swp1, swp2, swp3, location, salary, exp);
    }

    public void handleUpdateJob(Scanner scanner, ListInterface<Location> locations, ListInterface<Skill> skills) {
        System.out.println("\n--- Update Existing Job ---");
        System.out.print("Enter Job ID to update: ");
        String updateId = scanner.nextLine();
        Job job = searchByJobId(updateId);

        if (job == null) {
            System.out.println("Job not found.");
            return;
        }

        System.out.print("Enter New Title: ");
        String newTitle = scanner.nextLine();
        System.out.print("Enter New Company Name: ");
        String newCompany = scanner.nextLine();
        System.out.print("Enter New Description: ");
        String newDesc = scanner.nextLine();

        SkillWithProficiency swp1 = getSkillWithProficiency(scanner, skills, "Skill 1");
        SkillWithProficiency swp2 = getSkillWithProficiency(scanner, skills, "Skill 2");
        SkillWithProficiency swp3 = getSkillWithProficiency(scanner, skills, "Skill 3");

        System.out.print("Enter New Location: ");
        Location location = MatchAndSearch.fuzzyMatchLocation(locations, scanner.nextLine());

        double salary = getValidatedSalary(scanner);
        System.out.print("Enter Required Experience (years): ");
        int exp = scanner.nextInt();
        scanner.nextLine();

        updateJob(updateId, newTitle, newCompany, newDesc, swp1, swp2, swp3, location, salary, exp);
    }

    public void handleRemoveJob(Scanner scanner) {
        System.out.println("\n--- Remove Job ---");
        System.out.print("Enter Job ID to remove: ");
        String removeId = scanner.nextLine();
        removeJob(removeId);
    }

    public ListInterface<Job> filterJobsBySalaryRange(double minSalary, double maxSalary) {
        ListInterface<Job> result = new LinkedList<>();
        for (int i = 1; i <= jobList.getNumberOfEntries(); i++) {
            Job job = jobList.getEntry(i);
            if (job.getSalary() >= minSalary && job.getSalary() <= maxSalary) {
                result.add(job);
            }
        }
        return result;
    }
    
    public ListInterface<Job> filterJobsByLocation(String locationName) {
        ListInterface<Job> result = new LinkedList<>();
        for (int i = 1; i <= jobList.getNumberOfEntries(); i++) {
            Job job = jobList.getEntry(i);
            if (job.getLocation().getName().equalsIgnoreCase(locationName)) {
                result.add(job);
            }
        }
        return result;
    }
    
    public ListInterface<Job> filterJobsByCompany(String companyName) {
        ListInterface<Job> result = new LinkedList<>();
        for (int i = 1; i <= jobList.getNumberOfEntries(); i++) {
            Job job = jobList.getEntry(i);
            if (job.getCompanyName().toLowerCase().contains(companyName.toLowerCase())) {
                result.add(job);
            }
        }
        return result;
    }

    private static SkillWithProficiency getSkillWithProficiency(Scanner scanner, ListInterface<Skill> skills, String label) {
        System.out.print("Enter " + label + ": ");
        Skill skill = MatchAndSearch.fuzzyMatchSkill(skills, scanner.nextLine());
        System.out.print("Enter " + label + " Proficiency (1-4): ");
        int level = scanner.nextInt();
        scanner.nextLine();
        return new SkillWithProficiency(skill, level);
    }

    private static double getValidatedSalary(Scanner scanner) {
        double salary = -1;
        while (salary < 0) {
            System.out.print("Enter Salary: ");
            if (scanner.hasNextDouble()) {
                salary = scanner.nextDouble();
                if (salary < 0) {
                    System.out.println("Salary cannot be negative.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid salary (numbers only).");
                scanner.next();
            }
            scanner.nextLine();
        }
        return salary;
    }

    public void printJobs(ListInterface<Job> jobs) {
        if (jobs.isEmpty()) {
            System.out.println("No jobs found.");
        } else {
            for (int i = 1; i <= jobs.getNumberOfEntries(); i++) {
                System.out.println(jobs.getEntry(i));
            }
        }
    }

}
