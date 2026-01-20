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
import entity.Interview;
import java.io.*;

public class InterviewDAO {
    private String fileName = "interviews.dat";

    public void saveToFile(ListInterface<Interview> interviews) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(interviews);
        } catch (IOException e) {
            System.out.println("Error saving interviews: " + e.getMessage());
        }
    }

    public ListInterface<Interview> retrieveFromFile() {
        ListInterface<Interview> interviews = new LinkedList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            interviews = (ListInterface<Interview>) in.readObject();
        } catch (Exception e) {
            System.out.println("Error reading interviews: " + e.getMessage());
        }
        return interviews;
    }
}

