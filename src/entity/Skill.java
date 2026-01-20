/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author kitki
 */

public class Skill implements Comparable<Skill> {
    private String name;
    private String[] aliases;

    public Skill(String name, String... aliases) {
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
    public int compareTo(Skill other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Skill)) return false;

        Skill other = (Skill) obj;

        if (this.name.equalsIgnoreCase(other.name)) return true;

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