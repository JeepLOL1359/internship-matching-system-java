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
import entity.Applicant;

import java.io.*;

/**
 *
 * @author kitki
 */
public class ApplicantDAO {
    private String fileName = "applicants.dat";

    public void saveToFile(ListInterface<Applicant> applicantList) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(applicantList);
            ooStream.close();
        } catch (IOException ex) {
            System.out.println("Error saving applicants: " + ex.getMessage());
        }
    }

    public ListInterface<Applicant> retrieveFromFile() {
        File file = new File(fileName);
        ListInterface<Applicant> applicantList = new LinkedList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            applicantList = (ListInterface<Applicant>) oiStream.readObject();
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Applicant file not found.");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error loading applicants: " + ex.getMessage());
        }
        return applicantList;
    }
}

