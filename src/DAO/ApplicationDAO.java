/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author kitki
 */

import adt.LinkedList;
import adt.ListInterface;
import entity.Application;
import java.io.*;

public class ApplicationDAO {
    private String fileName = "applications.dat";

    public void saveToFile(ListInterface<Application> applications) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(applications);
        } catch (IOException e) {
            System.out.println("Error saving applications: " + e.getMessage());
        }
    }

    public ListInterface<Application> retrieveFromFile() {
        ListInterface<Application> applications = new LinkedList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            applications = (ListInterface<Application>) in.readObject();
        } catch (Exception e) {
            System.out.println("Error reading applications: " + e.getMessage());
        }
        return applications;
    }
}
