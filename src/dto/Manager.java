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
 * Properties of a Manager in Person List
 * @author QUAN
 */
public class Manager extends Person implements Serializable {

    public static final String ROLE_MA = "MA";
    
    //Constructor
    public Manager() {
    }

    public Manager(String employID) {
        super(employID);
    }

    public Manager(String employID, String name, Date birthDate, String sex, String password) {
        super(employID, name, birthDate, sex, password);
        this.setRole(ROLE_MA);
    }
    
    @Override
    public void showInfo() {
        super.showInfo();
        System.out.printf("|%-10s|%-25s|%-12s|%-6s|%-7s|%-10s|\n", getEmployID(), getName(), new SimpleDateFormat("dd/MM/yyyy").format(getBirthDate()), getRole(), getSex(), getPassword());
    }
}
