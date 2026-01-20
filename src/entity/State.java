/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author kitki
 */

import adt.LinkedList; 

public class State implements Comparable<State> {
    private String name;
    private String code;
    private LinkedList<Location> districts;

    public State(String name, String code, LinkedList<Location> districts) {
        this.name = name;
        this.code = code;
        this.districts = districts;
    }

    public String getName() { 
        return name; 
    }
    
    public String getCode() { 
        return code; 
    }
    
    public LinkedList<Location> getDistricts() { 
        return districts; 
    }
    
    @Override
    public int compareTo(State other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
