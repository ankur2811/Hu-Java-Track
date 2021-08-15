package com.example.Assignment2.Models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    @SequenceGenerator(
            name="user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )

    @Column(name="id")
    private Long id;
    private String displayname;
    private String firstname;
    private String lastname;
    private String about;
    private String areaofinterest;
    private String usertype;
    public int experience;
    private String domainexpertise;
    private String role;
    private String profilepicture;
    private String course;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="userId" ,referencedColumnName = "id")
    private List<UserCourseMapping> usercoursemap;

    public User() {
    }

    public User(Long id, String displayname, String firstname, String lastname, String about, String areaofinterest, String usertype, int experience, String domainexpertise, String role, String profilepicture) {
        this.id = id;
        this.displayname = displayname;
        this.firstname = firstname;
        this.lastname = lastname;
        this.about = about;
        this.areaofinterest = areaofinterest;
        this.usertype = usertype;
        this.experience = experience;
        this.domainexpertise = domainexpertise;
        this.role = role;
        this.profilepicture = profilepicture;
    }

    public User(String displayname, String firstname, String lastname, String about, String areaofinterest, String usertype, int experience, String domainexpertise, String role, String profilepicture) {
        this.displayname = displayname;
        this.firstname = firstname;
        this.lastname = lastname;
        this.about = about;
        this.areaofinterest = areaofinterest;
        this.usertype = usertype;
        this.experience = experience;
        this.domainexpertise = domainexpertise;
        this.role = role;
        this.profilepicture = profilepicture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAreaofinterest() {
        return areaofinterest;
    }

    public void setAreaofinterest(String areaofinterest) {
        this.areaofinterest = areaofinterest;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getDomainexpertise() {
        return domainexpertise;
    }

    public void setDomainexpertise(String domainexpertise) {
        this.domainexpertise = domainexpertise;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(String profilepicture) {
        this.profilepicture = profilepicture;
    }

    public List<UserCourseMapping> getUsercoursemap() {
        return usercoursemap;
    }

    public void setUsercoursemap(List<UserCourseMapping> usercoursemap) {
        this.usercoursemap = usercoursemap;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", displayname='" + displayname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", about='" + about + '\'' +
                ", areaofinterest='" + areaofinterest + '\'' +
                ", usertype='" + usertype + '\'' +
                ", experience=" + experience +
                ", domainexpertise='" + domainexpertise + '\'' +
                ", role='" + role + '\'' +
                ", profilepicture='" + profilepicture + '\'' +
                '}';
    }
}
