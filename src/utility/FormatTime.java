/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author kitki
 */

public class FormatTime {

    // Formats date to "YYYY-MM-DD"
    public static String formatDate(String dateInput) {
        try {
            LocalDate date = LocalDate.parse(dateInput);
            return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format! Expected: YYYY-MM-DD");
            return null;
        }
    }

    // Formats time to "HH:mm"
    public static String formatTime(String timeInput) {
        try {
            LocalTime time = LocalTime.parse(timeInput);
            return time.format(DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format! Expected: HH:mm (e.g., 14:30)");
            return null;
        }
    }

    // Validates date format
    public static boolean isValidDate(String dateInput) {
        try {
            LocalDate.parse(dateInput);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // Validates time format
    public static boolean isValidTime(String timeInput) {
        try {
            LocalTime.parse(timeInput);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
