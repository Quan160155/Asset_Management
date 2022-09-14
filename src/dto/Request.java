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
 * Properties of a Request in Request List
 * @author QUAN
 */
public class Request implements Serializable {

    //Properties
    private String rID;
    private String assetID;
    private String employeeID;
    private int quantity;
    private Date requestDateTime;

    //Constructor
    public Request() {
    }

    public Request(String rID, String assetID, String employeeID, int quantity, Date requestDateTime) {
        this.rID = rID;
        this.assetID = assetID;
        this.employeeID = employeeID;
        this.quantity = quantity;
        this.requestDateTime = requestDateTime;
    }

    public Request(String rID, String assetID, String employeeID) {
        this.rID = rID;
        this.assetID = assetID;
        this.employeeID = employeeID;
    }

    //Getter, setter
    //No set for IDs
    public String getrID() {
        return rID;
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

    public Date getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(Date requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public void showInfo() {
        System.out.printf("|%-6s|%-9s|%-12s|%-10d|%-17s|\n", getrID(), getAssetID(), getEmployeeID(), getQuantity(), new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(getRequestDateTime()));
    }
}
