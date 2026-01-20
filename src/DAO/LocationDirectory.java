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
import entity.Location;
import entity.State;

/**
 * The LocationDirectory class serves as a centralized list of predefined locations 
 * that are used throughout the application for matching applicants to jobs based 
 * on location preference. 
 * 
 * It provides a static list of common locations (e.g., "Kuala Lumpur", "Penang") 
 * along with their common aliases (e.g., "KL", "PNG") to ensure consistent and 
 * accurate location matching across the system.
 * 
 * Just stores all the known locations.
 * Provides a static method to access them: getAllLocations().
 * Has no search logic — just a static list of Location objects.
 */
public class LocationDirectory {

    private static final LinkedList<State> states = new LinkedList<>();

    static {
        LinkedList<Location> selangorDistricts = new LinkedList<>();
        selangorDistricts.insertSorted(new Location("Gombak", "GBK"));
        selangorDistricts.insertSorted(new Location("Klang", "KLG"));
        selangorDistricts.insertSorted(new Location("Kuala Langat", "KLGT"));
        selangorDistricts.insertSorted(new Location("Kuala Selangor", "KLS"));
        selangorDistricts.insertSorted(new Location("Petaling", "PTG"));
        selangorDistricts.insertSorted(new Location("Sabak Bernam", "SBK"));
        selangorDistricts.insertSorted(new Location("Sepang", "SPG"));
        selangorDistricts.insertSorted(new Location("Ulu Langat", "UL"));
        selangorDistricts.insertSorted(new Location("Ulu Selangor", "US"));
        selangorDistricts.insertSorted(new Location("Kuala Lumpur", "KL", "K.L.", "KualaLumpur"));
        selangorDistricts.insertSorted(new Location("Putrajaya", "PJY"));

        LinkedList<Location> johorDistricts = new LinkedList<>();
        johorDistricts.insertSorted(new Location("Batu Pahat", "BP"));
        johorDistricts.insertSorted(new Location("Johor Bahru", "JB"));
        johorDistricts.insertSorted(new Location("Kluang", "KLN"));
        johorDistricts.insertSorted(new Location("Kota Tinggi", "KT"));
        johorDistricts.insertSorted(new Location("Kulai", "KUL"));
        johorDistricts.insertSorted(new Location("Mersing", "MS"));
        johorDistricts.insertSorted(new Location("Muar", "MR"));
        johorDistricts.insertSorted(new Location("Pontian", "PTN"));
        johorDistricts.insertSorted(new Location("Segamat", "SGMT"));
        johorDistricts.insertSorted(new Location("Tangkak", "TGK"));
        
        // Add all states to predefinedStates
        states.insertSorted(new State("Selangor", "SGR", selangorDistricts));
        states.insertSorted(new State("Johor", "JHR", johorDistricts));
    }

    public static LinkedList<State> getAllStates() {
        return states;
    }

    public static LinkedList<Location> getAllDistricts() {
        LinkedList<Location> all = new LinkedList<>();
        for (int i = 0; i < states.size(); i++) {
            State s = states.get(i);
            LinkedList<Location> districts = s.getDistricts();
            for (int j = 0; j < districts.size(); j++) {
                all.add(districts.get(j));
            }
        }
        return all;
    }
}
