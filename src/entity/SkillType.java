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

public class SkillType implements Comparable<SkillType> {
    private String name;
    private String code;
    private LinkedList<Skill> skills;

    public SkillType(String name, String code, LinkedList<Skill> skills) {
        this.name = name;
        this.code = code;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public LinkedList<Skill> getSkills() {
        return skills;
    }

    @Override
    public int compareTo(SkillType other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    @Override
    public String toString() {
        return name;
    }
}