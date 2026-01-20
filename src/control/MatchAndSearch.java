/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

/**
 *
 * @author kitki
 */
import adt.ListInterface;
import adt.LinkedList;
import entity.*;
import DAO.LocationDirectory;
import DAO.SkillsDirectory;
import boundary.JobSearchMenu;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.Function;

public class MatchAndSearch {

    private ListInterface<Applicant> applicantList;
    private ListInterface<Job> jobList;
    private ListInterface<Application> applicationList;

    public MatchAndSearch(ListInterface<Applicant> applicants, ListInterface<Job> jobs, ListInterface<Application> applications) {
        this.applicantList = applicants;
        this.jobList = jobs;
        this.applicationList = applications;
    }

    // === Matching Engine ===
    public void runMatchingEngine(Scanner scanner) {
        JobSearchMenu jobSearchMenu = new JobSearchMenu(this);
        jobSearchMenu.displayJobSearchMenu(jobList, applicantList, applicationList, scanner);
    }

    private ListInterface<Application> matchApplicantsToJob(Job job, String today, ListInterface<Applicant> applicants) {
        ListInterface<Application> matches = new LinkedList<>();
        int appCounter = 1;

        for (int i = 0; i < applicants.size(); i++) {
            Applicant applicant = applicants.get(i);
            double totalScore = calculateTotalMatchScore(applicant, job);

            if (totalScore > 0) {
                String appId = "APP" + String.format("%03d", appCounter++);
                Application application = new Application(appId, applicant.getApplicantId(), job.getJobId(), today, totalScore);
                matches.insertSorted(application);
            }
        }

        return matches;
    }

    private double calculateTotalMatchScore(Applicant applicant, Job job) {
        double skillScore = computeSkillScore(applicant, job) / 14.0;
        double experienceScore = computeExperienceScore(applicant.getWorkingExperience(), job.getRequiredExperience()) / 5.0;
        double locationScore = computeLocationScore(applicant.getLocationPreference(), job.getLocation()) / 4.0;

        double rawScore = (skillScore * 0.6 + experienceScore * 0.3 + locationScore * 0.1) * 100;

        // Subtract 1 for every 1 exceeded
        if (rawScore > 100.0) {
            rawScore -= (rawScore - 100.0); // Same as rawScore = 100
        }

        // Final precision round to 2 decimal places
        return Math.round(rawScore * 100.0) / 100.0;
    }

    private double computeSkillScore(Applicant applicant, Job job) {
        SkillWithProficiency[] applicantSkills = {
            applicant.getSkill_1(),
            applicant.getSkill_2(),
            applicant.getSkill_3()
        };

        SkillWithProficiency[] jobSkills = {
            job.getRequiredSkills_1(),
            job.getRequiredSkills_2(),
            job.getRequiredSkills_3()
        };

        int[] weights = {6, 5, 3};
        double score = 0.0;

        for (int i = 0; i < jobSkills.length; i++) {
            SkillWithProficiency required = jobSkills[i];
            if (required == null) continue;

            for (SkillWithProficiency appSkill : applicantSkills) {
                if (appSkill != null && appSkill.getSkill().equals(required.getSkill())) {
                    int applicantLevel = appSkill.getProficiency();
                    int requiredLevel = required.getProficiency();

                    if (applicantLevel > requiredLevel) {
                        score += weights[i]; // Exceeds → full weight
                    } else if (applicantLevel == requiredLevel) {
                        score += weights[i] - 1; // Same → weight - 1
                    } else {
                        score += Math.max(0, weights[i] - (requiredLevel - applicantLevel)); // Below → weight - gap
                    }
                }
            }
        }

        return score;
    }

    private double computeExperienceScore(int applicantExp, int requiredExp) {
        if (applicantExp == 0) return 1.0;
        if (applicantExp > requiredExp) return 5.0;
        if (applicantExp == requiredExp) return 3.0;
        return 3.0;
    }

    private double computeLocationScore(Location applicantLoc, Location jobLoc) {
        if (applicantLoc == null || jobLoc == null) return 1.0;
        if (applicantLoc.equals(jobLoc)) return 4.0;

        String appState = getStateName(applicantLoc);
        String jobState = getStateName(jobLoc);
        return (appState != null && appState.equals(jobState)) ? 3.0 : 1.0;
    }

    private String getStateName(Location loc) {
        ListInterface<State> states = LocationDirectory.getAllStates();
        for (int i = 0; i < states.size(); i++) {
            State state = states.get(i);
            ListInterface<Location> districts = state.getDistricts();
            for (int j = 0; j < districts.size(); j++) {
                if (districts.get(j).equals(loc)) return state.getName();
            }
        }
        return null;
    }

    public void displayAllApplications() {
        if (applicationList.isEmpty()) {
            System.out.println("No applications found.");
        } else {
            for (int i = 0; i < applicationList.size(); i++) {
                System.out.println(applicationList.get(i));
            }
            System.out.println("\nTotal number of applications: " + getTotalApplications());
        }
    }
    
    public int getTotalApplications() {
        return applicationList.getNumberOfEntries();
    }
    
    // === Application Search with Optimized ID Matching ===
   
    public void searchApplicationByField(Scanner scanner, String field) {
        System.out.print("Enter " + field + " ID to search: ");
        String input = scanner.nextLine().trim();
        boolean found = false;

        if (field.equalsIgnoreCase("application")) {
            Application result = smartFindById(applicationList, input, Application::getApplicationId);
            if (result != null) {
                System.out.println("Found (by smart ID match): " + result);
                found = true;
            }
        } else if (field.equalsIgnoreCase("applicant")) {
            ListInterface<Application> results = smartFindAllByField(applicationList, input, Application::getApplicantId);
            for (int i = 0; i < results.size(); i++) {
                System.out.println("Found (by smart ID match): " + results.get(i));
                found = true;
            }
        } else if (field.equalsIgnoreCase("job")) {
            ListInterface<Application> results = smartFindAllByField(applicationList, input, Application::getJobId);
            for (int i = 0; i < results.size(); i++) {
                System.out.println("Found (by smart ID match): " + results.get(i));
                found = true;
            }
        }

        // Fallback: partial search
        if (!found) {
            for (int i = 0; i < applicationList.size(); i++) {
                Application app = applicationList.get(i);
                if (applicationMatchesField(app, input, field)) {
                    System.out.println(app);
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No matching application found.");
        }
    }
    
    public void removeApplicationByField(Scanner scanner, String field) {
        System.out.print("Enter " + field + " ID to remove: ");
        String input = scanner.nextLine().trim();
        boolean found = false;

        ListInterface<Application> toRemove = new LinkedList<>();

        if (field.equalsIgnoreCase("application")) {
            Application match = smartFindById(applicationList, input, Application::getApplicationId);
            if (match != null) {
                toRemove.add(match);
                found = true;
            }
        } else if (field.equalsIgnoreCase("applicant")) {
            toRemove = smartFindAllByField(applicationList, input, Application::getApplicantId);
            found = !toRemove.isEmpty();
        } else if (field.equalsIgnoreCase("job")) {
            toRemove = smartFindAllByField(applicationList, input, Application::getJobId);
            found = !toRemove.isEmpty();
        }

        // Fallback fuzzy/partial match
        if (!found) {
            for (int i = 0; i < applicationList.size(); i++) {
                Application app = applicationList.get(i);
                if (applicationMatchesField(app, input, field)) {
                    toRemove.add(app);
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No matching application to remove.");
            return;
        }

        System.out.println("The following applications will be removed:");
        for (int i = 0; i < toRemove.size(); i++) {
            System.out.println(toRemove.get(i));
        }

        System.out.print("Are you sure you want to proceed? (yes/no, or 1/0): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        if (confirmation.equals("yes") || confirmation.equals("1") || confirmation.equals("y")) {
            for (int i = applicationList.size() - 1; i >= 0; i--) {
                if (toRemove.contains(applicationList.get(i))) {
                    applicationList.remove(i + 1);
                }
            }
            System.out.println("Removed successfully.");
        } else {
            System.out.println("Operation cancelled.");
        }
    }

    private boolean applicationMatchesField(Application app, String input, String field) {
        field = field.toLowerCase();
        input = input.toLowerCase();
        switch (field) {
            case "application": return app.getApplicationId().toLowerCase().contains(input);
            case "applicant": return app.getApplicantId().toLowerCase().contains(input);
            case "job": return app.getJobId().toLowerCase().contains(input);
            default: return false;
        }
    }
    
    // === Location Search Utilities ===
    public static Location fuzzyMatchLocation(ListInterface<Location> locations, String input) {
        input = normalize(input);
        ScoredLocation bestMatch = null;

        for (int i = 0; i < locations.size(); i++) {
            Location loc = locations.get(i);
            String locName = normalize(loc.getName());
            int score = levenshteinDistance(input, locName);

            // Check aliases
            for (String alias : loc.getAliases()) {
                int aliasScore = levenshteinDistance(input, normalize(alias));
                score = Math.min(score, aliasScore);
            }

            // Exact match bonus
            if (locName.equals(input)) score -= 3;

            if (bestMatch == null || score < bestMatch.score) {
                bestMatch = new ScoredLocation(loc, score);
            }
        }

        return (bestMatch != null && bestMatch.score <= 3) ? bestMatch.location : null;
    }

    public static Location findLocationByName(ListInterface<Location> locations, String name) {
        for (int i = 0; i < locations.size(); i++) {
            Location loc = locations.get(i);
            if (loc.getName().equalsIgnoreCase(name)) return loc;
        }
        return null;
    }

    // === Private Support Classes/Methods ===
    private static int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) dp[i][j] = j;
                else if (j == 0) dp[i][j] = i;
                else {
                    dp[i][j] = Math.min(
                        dp[i - 1][j - 1] + (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1),
                        Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1)
                    );
                }
            }
        }
        return dp[a.length()][b.length()];
    }

    private static String normalize(String s) {
        return s.toLowerCase().replaceAll("[^a-z0-9]", "");
    }

    private static class ScoredLocation {
        Location location;
        int score;
        public ScoredLocation(Location location, int score) {
            this.location = location;
            this.score = score;
        }
    }

    // === Skill Search Utilities ===
    public static Skill fuzzyMatchSkill(ListInterface<Skill> skills, String input) {
        input = normalize(input);

        ScoredSkill bestMatch = null;

        for (int i = 0; i < skills.size(); i++) {
            Skill skill = skills.get(i);
            String skillName = normalize(skill.getName());
            int score = levenshteinDistance(input, skillName);

            // Check aliases too
            String[] aliases = skill.getAliases();
            if (aliases != null) {
                for (String alias : aliases) {
                    int aliasScore = levenshteinDistance(input, normalize(alias));
                    score = Math.min(score, aliasScore);
                }   
            }

            // Exact match bonus
            if (skillName.equals(input)) score -= 3;

            if (bestMatch == null || score < bestMatch.score) {
                bestMatch = new ScoredSkill(skill, score);
            }
        }

        return (bestMatch != null && bestMatch.score <= 3) ? bestMatch.skill : null;
    }

    public static Skill findSkillByName(ListInterface<Skill> list, String name) {
        for (int i = 0; i < list.size(); i++) {
            Skill skill = list.get(i);
            if (skill.getName().equalsIgnoreCase(name)) {
                return skill;
            }
        }
        return null;
    }

    // === Private Support Classes/Methods ===
    private static class ScoredSkill {
        Skill skill;
        int score;

        public ScoredSkill(Skill skill, int score) {
            this.skill = skill;
            this.score = score;
        }
    }

    // === ID Utility ===
    private static int extractIdNumber(String id) {
        try {
            return Integer.parseInt(id.replaceAll("\\D+", ""));
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    // === Job Search Utilities ===
    
    public void searchJobs(Scanner scanner, String mode, boolean exact, ListInterface<Applicant> applicants) {
        ListInterface<Job> result = new LinkedList<>();

        switch (mode.toLowerCase()) {
            case "id":
            {
                System.out.print("Enter Job ID: ");
                String jobId = scanner.nextLine();
                Job found = smartFindById(jobList, jobId, Job::getJobId);
                if (found != null) {
                    result.add(found);
                }
            }
            break;

            case "skill": 
            {
                System.out.print("Enter Skill: ");
                String skillInput = scanner.nextLine();
                Skill skill = exact ? findSkillByName(SkillsDirectory.getAllSkills(), skillInput)
                                    : fuzzyMatchSkill(SkillsDirectory.getAllSkills(), skillInput);

                if (skill == null) {
                    System.out.println("No skill matched.");
                    return;
                }

                for (int i = 0; i < jobList.size(); i++) {
                    Job job = jobList.get(i);
                    if ((job.getRequiredSkills_1() != null && job.getRequiredSkills_1().getSkill().equals(skill)) ||
                        (job.getRequiredSkills_2() != null && job.getRequiredSkills_2().getSkill().equals(skill)) ||
                        (job.getRequiredSkills_3() != null && job.getRequiredSkills_3().getSkill().equals(skill))) {
                        result.add(job);
                    }
                }
            }
            break;

            case "location":
            {
                System.out.print("Enter Location: ");
                String locInput = scanner.nextLine();
                Location location = exact ? findLocationByName(LocationDirectory.getAllDistricts(), locInput)
                                          : fuzzyMatchLocation(LocationDirectory.getAllDistricts(), locInput);

                if (location == null) {
                    System.out.println("No location matched.");
                    return;
                }

                for (int i = 0; i < jobList.size(); i++) {
                    if (location.equals(jobList.get(i).getLocation())) {
                        result.add(jobList.get(i));
                    }
                }
            }
            break;

            case "keyword":
            {
                System.out.print("Enter Keyword: ");
                String keyword = scanner.nextLine().toLowerCase();
                for (int i = 0; i < jobList.size(); i++) {
                    Job job = jobList.get(i);
                    if (exact) {
                        if (job.getTitle().equalsIgnoreCase(keyword) ||
                            job.getCompanyName().equalsIgnoreCase(keyword) ||
                            job.getDescription().equalsIgnoreCase(keyword)) {
                            result.add(job);
                        }
                    } else {
                        if (job.getTitle().toLowerCase().contains(keyword) ||
                            job.getCompanyName().toLowerCase().contains(keyword) ||
                            job.getDescription().toLowerCase().contains(keyword)) {
                            result.add(job);
                        }
                    }
                }
            }
            break;
            
            default:
            {
                System.out.println("Invalid search mode.");
                return;
            }
        }

        displayJobsAndHandleMatching(result, applicants, scanner);
    }

    private void displayJobsAndHandleMatching(ListInterface<Job> searchResults, ListInterface<Applicant> applicants, Scanner scanner) {
        String today = LocalDate.now().toString();
        if (searchResults.isEmpty()) {
            System.out.println("No matching jobs found.");
        } else {
            System.out.println("\nSearch Results:");
            for (int i = 0; i < searchResults.size(); i++) {
                System.out.println(searchResults.get(i));
            }

            System.out.print("\nEnter Job ID to perform matching or 0 to cancel: ");
            String jobChoice = scanner.nextLine();

            if (!jobChoice.equals("0")) {
                Job selectedJob = smartFindById(searchResults, jobChoice, Job::getJobId);
                if (selectedJob != null) {
                    ListInterface<Application> matches = matchApplicantsToJob(selectedJob, today, applicants);

                    int appCounter = applicationList.getNumberOfEntries() + 1;
                    boolean foundMatch = false;

                    for (int j = 0; j < matches.size(); j++) {
                        Application app = matches.get(j);
                        if (app.getMatchScore() >= 80.0) {
                            String appId = "APP" + String.format("%03d", appCounter++);
                            Application newApp = new Application(appId, app.getApplicantId(), app.getJobId(), app.getApplicationDate(), app.getMatchScore());
                            applicationList.insertSorted(newApp);
                            System.out.println(newApp);
                            foundMatch = true;
                        }
                    }

                    if (!foundMatch) {
                        System.out.println("No applicants matched with score ≥ 80.00.");
                    }
                } else {
                    System.out.println("Invalid Job ID.");
                }
            } else {
                System.out.println("Cancelled. Returning to search menu.");
            }
        }
    }
    
    public static <T> T smartFindById(ListInterface<T> list, String inputId, Function<T, String> idGetter) {
        int targetNum = extractIdNumber(inputId);
        if (targetNum == -1) return null;

        // Create a dummy object with formatted ID
        String prefix = inputId.replaceAll("[0-9]", "").toUpperCase();
        String padded = String.format("%03d", targetNum);
        String fullId = prefix + padded;

        // Generic dummy constructor call skipped; assume caller gives proper dummy
        T dummy = null;
        if (list.size() > 0) {
            try {
                // Assume T has a constructor that takes ID as 1st param
                dummy = (T) list.get(0).getClass()
                    .getConstructor(String.class, String.class, String.class, String.class, double.class)
                    .newInstance(fullId, "", "", "", 0.0);
            } catch (Exception e) {
                // fallback if cannot reflectively create
            }
        }

        if (dummy != null) {
            return list.find(dummy, (a, b) -> {
                int n1 = extractIdNumber(idGetter.apply(a));
                int n2 = extractIdNumber(idGetter.apply(b));
                return Integer.compare(n1, n2);
            });
        }

        // fallback to linear search if cannot use find
        for (int i = 0; i < list.size(); i++) {
            T item = list.get(i);
            if (extractIdNumber(idGetter.apply(item)) == targetNum) return item;
        }
        return null;
    }
    
    public static <T> ListInterface<T> smartFindAllByField(ListInterface<T> list, String inputId, java.util.function.Function<T, String> idGetter) {
        int targetNum = extractIdNumber(inputId);
        ListInterface<T> matches = new LinkedList<>();

        if (targetNum == -1) return matches;

        for (int i = 0; i < list.size(); i++) {
            T item = list.get(i);
            String id = idGetter.apply(item);
            int currentNum = extractIdNumber(id);
            if (currentNum == targetNum) {
                matches.add(item);
            }
        }
        return matches;
    }

}