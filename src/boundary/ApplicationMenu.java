    package boundary;

    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */

    /**
     *
     * @author kitki
     */
    import adt.ListInterface;
    import control.MatchAndSearch;
    import java.util.Scanner;
    public class ApplicationMenu {

    private final MatchAndSearch matchEngine;

    public ApplicationMenu(MatchAndSearch matchEngine) {
        this.matchEngine = matchEngine;
    }

        public void handleApplicationSubmenu(Scanner scanner) {
            int choice;
            do {
                System.out.println("\n=== Application Management ===");
                System.out.println("1. View All Applications");
                System.out.println("2. Search by Application ID");
                System.out.println("3. Search by Applicant ID");
                System.out.println("4. Search by Job ID");
                System.out.println("5. Remove by Application ID");
                System.out.println("6. Remove by Applicant ID");
                System.out.println("7. Remove by Job ID");
                System.out.println("0. Return to Main Menu");
                System.out.print("Enter choice: ");
                choice = scanner.nextInt(); scanner.nextLine();

                switch (choice) {
                    case 1:
                        matchEngine.displayAllApplications();
                        break;
                    case 2:
                        matchEngine.searchApplicationByField(scanner, "application");
                        break;
                    case 3:
                        matchEngine.searchApplicationByField(scanner, "applicant");
                        break;
                    case 4: 
                        matchEngine.searchApplicationByField(scanner, "job");
                        break;
                    case 5: 
                        matchEngine.removeApplicationByField(scanner, "application");
                        break;
                    case 6: 
                        matchEngine.removeApplicationByField(scanner, "applicant");
                        break;
                    case 7:
                        matchEngine.removeApplicationByField(scanner, "job");
                        break;
                    case 0:
                        System.out.println("Returning...");
                        break;
                    default:
                        System.out.println("Invalid option.");
                        break;
                }
            } while (choice != 0);
        }

    }
