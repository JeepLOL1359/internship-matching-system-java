/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author kitki
 */
public class Location implements Comparable<Location> {
    private String name;
    private String[] aliases;

    public Location(String name, String... aliases) {
        this.name = name;
        this.aliases = aliases;
    }

    public String getName() { 
        return name; 
    }
    
    public String[] getAliases() { 
        return aliases; 
    }
    
    @Override
    public int compareTo(Location other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Location)) return false;

        Location other = (Location) obj;

        // Compare name
        if (name.equalsIgnoreCase(other.name)) return true;

        // Compare all aliases (cross-check both ways)
        if (aliases != null && other.aliases != null) {
            for (String a1 : aliases) {
                for (String a2 : other.aliases) {
                    if (a1.equalsIgnoreCase(a2)) return true;
                }
            }
        }

        return false;
    }
}

