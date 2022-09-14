/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Request;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import util.Utils;

/**
 * Details of Request List features implements from I_RequestList
 *
 * @author QUAN
 */
public class RequestList extends ArrayList<Request> implements I_RequestList {

    public static final String FILENAME = "request.dat";

    public RequestList() {
        loadData(FILENAME);
    }

    //Everytime there's any change available in Asset List, it will store to "FILENAME" file immediately
    public void storeData(String fileName) {
        File file = new File(fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Request request : this) {
                oos.writeObject(request);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //This method will run only once and immediately at the time user run the project 
    public void loadData(String fileName) {
        ArrayList<Request> list = new ArrayList<>();
        File file = new File(fileName);
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (fis.available() > 0) {
                list.add((Request) ois.readObject());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        this.clear();
        this.addAll(list);
    }

    //Show all the information of available request (For Option 5 in Manager Program)
    @Override
    public void showAll() {
        System.out.printf("+------+---------+------------+----------+-------------------+\n");
        System.out.printf("|%-6s|%-9s|%-12s|%-10s|%-19s|\n", "rID", "assetID", "employeeID", "quantity", "requestDateTime");
        System.out.printf("+------+---------+------------+----------+-------------------+\n");
        for (int i = 0; i < this.size(); i++) {
            this.get(i).showInfo();
        }
        System.out.printf("+------+---------+------------+----------+-------------------+\n");
    }

    //Check if Manager or Employee input the wrong existed ID, return all information of this ID if input correctly 
    //For Option 5 in Manager Program and Option 4 in Employee Program  
    @Override
    public Request checkExist(String requestID) {
        Request request = null;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getrID().equals(requestID)) {
                request = this.get(i);
            }
        }
        return request;
    }

    //Remove the request based on the rID the Manager input for approve or Employee input for cancel and store the new data to File 
    //For Option 5 in Manager Program and Option 4 in Employee Program
    @Override
    public void removeRequest(Request request) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).equals(request)) {
                this.remove(i);
                this.storeData(FILENAME);
            }
        }
    }

    //Add new Request to the list and store to file (For Option 3 in Employee Program)
    @Override
    public void createRequest(String employeeID, String assetID, int quantity) {
        boolean cont = true;
        String newRequestID;

        //Based on the user's computer clock in real time
        LocalDateTime now = LocalDateTime.now();
        Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
        Date requestDateTime = Date.from(instant);

        do {
            newRequestID = Utils.getString("Please input new id for Request List (This cannot be changed): ");
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i).getrID().equals(newRequestID)) {
                    System.out.println("Duplicate ID, please try again!");
                    cont = true;
                    break;
                } else {
                    cont = false;
                }
            }
        } while (cont == true);
        Request request = new Request(newRequestID, assetID, employeeID, quantity, requestDateTime);
        this.add(request);
        this.storeData(FILENAME);
        System.out.println("New request added!");
    }

    //Count how many request this employee have (For Option 4 in Employee Program)
    @Override
    public int countRequestByEmployee(String employeeID) {
        int count = 0;
        for (Request request : this) {
            if (request.getEmployeeID().equals(employeeID)) {
                count++;
            }
        }
        return count;
    }

    //Show the list of requested assets available of this employee (For Option 4 in Employee Program)
    @Override
    public void showByEmployee(String employeeID) {
        System.out.printf("+------+---------+------------+----------+-------------------+\n");
        System.out.printf("|%-6s|%-9s|%-12s|%-10s|%-19s|\n", "rID", "assetID", "employeeID", "quantity", "requestDateTime");
        System.out.printf("+------+---------+------------+----------+-------------------+\n");
        for (Request request : this) {
            if (request.getEmployeeID().equals(employeeID)) {
                request.showInfo();
            }
        }
        System.out.printf("+------+---------+------------+----------+-------------------+\n");
    }
}
