/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author kevin
 */

import adt.LinkedList;
import adt.ListInterface;
import entity.Job;
import java.io.*;

/**
 * Handles saving and loading job data.
 */
public class JobDAO {
    private String fileName = "jobs.dat";

    public void saveToFile(ListInterface<Job> jobs) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(jobs);
        } catch (IOException e) {
            System.out.println("Error saving jobs: " + e.getMessage());
        }
    }

    public ListInterface<Job> retrieveFromFile() {
        ListInterface<Job> jobs = new LinkedList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            jobs = (ListInterface<Job>) in.readObject();
        } catch (Exception e) {
            System.out.println("Error reading jobs: " + e.getMessage());
        }
        return jobs;
    }
}

