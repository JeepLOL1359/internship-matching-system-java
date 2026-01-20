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
import entity.Skill;
import entity.SkillType;

public class SkillsDirectory {
    private static final LinkedList<SkillType> skillTypes = new LinkedList<>();

    static {
        LinkedList<Skill> programmingSkills = new LinkedList<>();
        programmingSkills.insertSorted(new Skill("Java"));
        programmingSkills.insertSorted(new Skill("Python"));
        programmingSkills.insertSorted(new Skill("C++"));
        programmingSkills.insertSorted(new Skill("HTML"));
        programmingSkills.insertSorted(new Skill("CSS"));
        programmingSkills.insertSorted(new Skill("JavaScript"));
        programmingSkills.insertSorted(new Skill("TypeScript"));
        programmingSkills.insertSorted(new Skill("SQL"));

        LinkedList<Skill> frameworkSkills = new LinkedList<>();
        frameworkSkills.insertSorted(new Skill("Spring", "Spring Boot"));
        frameworkSkills.insertSorted(new Skill("React"));
        frameworkSkills.insertSorted(new Skill("Node.js"));
        frameworkSkills.insertSorted(new Skill("Angular"));
        frameworkSkills.insertSorted(new Skill("Django"));
        frameworkSkills.insertSorted(new Skill(".NET"));
        frameworkSkills.insertSorted(new Skill("Express.js"));

        LinkedList<Skill> devOpsSkills = new LinkedList<>();
        devOpsSkills.insertSorted(new Skill("Git"));
        devOpsSkills.insertSorted(new Skill("Docker"));
        devOpsSkills.insertSorted(new Skill("Kubernetes"));
        devOpsSkills.insertSorted(new Skill("Jenkins"));
        devOpsSkills.insertSorted(new Skill("Linux CLI"));
        devOpsSkills.insertSorted(new Skill("Bash"));

        LinkedList<Skill> cloudSkills = new LinkedList<>();
        cloudSkills.insertSorted(new Skill("AWS"));
        cloudSkills.insertSorted(new Skill("Azure"));
        cloudSkills.insertSorted(new Skill("Google Cloud"));
        cloudSkills.insertSorted(new Skill("Firebase"));

        LinkedList<Skill> databaseSkills = new LinkedList<>();
        databaseSkills.insertSorted(new Skill("MySQL"));
        databaseSkills.insertSorted(new Skill("PostgreSQL"));
        databaseSkills.insertSorted(new Skill("MongoDB"));
        databaseSkills.insertSorted(new Skill("Redis"));
        databaseSkills.insertSorted(new Skill("GraphQL"));

        LinkedList<Skill> otherSkills = new LinkedList<>();
        otherSkills.insertSorted(new Skill("Machine Learning"));
        otherSkills.insertSorted(new Skill("Data Analysis"));
        otherSkills.insertSorted(new Skill("Cybersecurity"));

        skillTypes.insertSorted(new SkillType("Programming Languages", "PL", programmingSkills));
        skillTypes.insertSorted(new SkillType("Frameworks & Tools", "FW", frameworkSkills));
        skillTypes.insertSorted(new SkillType("DevOps & Tools", "DV", devOpsSkills));
        skillTypes.insertSorted(new SkillType("Cloud & Infrastructure", "CL", cloudSkills));
        skillTypes.insertSorted(new SkillType("Databases & Query Languages", "DB", databaseSkills));
        skillTypes.insertSorted(new SkillType("Other Useful Skills", "OT", otherSkills));
    }

    public static LinkedList<SkillType> getAllSkillTypes() {
        return skillTypes;
    }

    public static LinkedList<Skill> getAllSkills() {
        LinkedList<Skill> all = new LinkedList<>();
        for (int i = 0; i < skillTypes.size(); i++) {
            SkillType type = skillTypes.get(i);
            LinkedList<Skill> skills = type.getSkills();
            for (int j = 0; j < skills.size(); j++) {
                all.add(skills.get(j));
            }
        }
        return all;
    }
}