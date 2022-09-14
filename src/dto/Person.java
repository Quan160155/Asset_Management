/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Properties of a Person in Person List
 * @author QUAN
 */
public abstract class Person implements Serializable {

    //Properties
    private String employID;
    private String name;
    private Date birthDate;
    private String role;
    private String sex;
    private String password;

    //Constructor
    public Person() {
    }

    public Person(String employID) {
        this.employID = employID;
    }

    public Person(String employID, String name, Date birthDate, String sex, String password) {
        this.employID = employID;
        this.name = name;
        this.birthDate = birthDate;
        this.sex = sex;
        this.password = password;
    }

    //Getter,setter
    //No set for IDs
    public String getEmployID() {
        return employID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void showInfo(){
        
    }
}
