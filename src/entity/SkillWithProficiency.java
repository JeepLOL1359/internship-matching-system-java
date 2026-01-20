/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author kitki
 */
public class SkillWithProficiency {
    private Skill skill;
    private int proficiency; // from 1 (beginner) to 5 (expert)

    public SkillWithProficiency(Skill skill, int proficiency) {
        this.skill = skill;
        this.proficiency = proficiency;
    }

    public Skill getSkill() {
        return skill;
    }

    public int getProficiency() {
        return proficiency;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public void setProficiency(int proficiency) {
        this.proficiency = proficiency;
    }

    @Override
    public String toString() {
        return skill.getName() + " (Level " + proficiency + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof SkillWithProficiency)) return false;
        SkillWithProficiency other = (SkillWithProficiency) obj;
        return skill.equals(other.skill); // same skill = same object
    }
}
