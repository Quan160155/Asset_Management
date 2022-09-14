/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Properties of an Employee in Person List
 *
 * @author QUAN
 */
public class Employee extends Person implements Serializable {

    public static final String ROLE_EM = "EM";
    
    //Constructor
    public Employee() {
    }

    public Employee(String employID) {
        super(employID);
    }

    public Employee(String employID, String name, Date birthDate, String sex, String password) {
        super(employID, name, birthDate, sex, password);
        this.setRole(ROLE_EM);
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.printf("|%-10s|%-25s|%-12s|%-6s|%-7s|%-10s|\n", getEmployID(), getName(), new SimpleDateFormat("dd/MM/yyyy").format(getBirthDate()), getRole(), getSex(), getPassword());
    }
}
