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
 * Properties of a Borrow in Borrow List
 * @author QUAN
 */
public class Borrow implements Serializable {

    //Properties
    private String bID;
    private String assetID;
    private String employeeID;
    private int quantity;
    private Date borrowDateTime;

    //Constructor
    public Borrow() {
    }

    public Borrow(String bID, String assetID, String employeeID) {
        this.bID = bID;
        this.assetID = assetID;
        this.employeeID = employeeID;
    }

    public Borrow(String bID, String assetID, String employeeID, int quantity, Date borrowDateTime) {
        this.bID = bID;
        this.assetID = assetID;
        this.employeeID = employeeID;
        this.quantity = quantity;
        this.borrowDateTime = borrowDateTime;
    }

    //Getter, Setter
    //No set for IDs
    public String getbID() {
        return bID;
    }

    public String getAssetID() {
        return assetID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getBorrowDateTime() {
        return borrowDateTime;
    }

    public void setBorrowDateTime(Date borrowDateTime) {
        this.borrowDateTime = borrowDateTime;
    }

    public void showInfo() {
        System.out.printf("|%-6s|%-9s|%-12s|%-10d|%-17s|\n", getbID(), getAssetID(), getEmployeeID(), getQuantity(), new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(getBorrowDateTime()));
    }
}
