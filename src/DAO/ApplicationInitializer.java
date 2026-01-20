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

public class ApplicationInitializer {
    public ListInterface<Application> initializeApplications() {
        ListInterface<Application> applications = new LinkedList<>();
        return applications;
    }
}

