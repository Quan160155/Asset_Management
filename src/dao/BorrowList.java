/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Borrow;
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
 * Details of Borrow List methods implements from I_BorrowList
 *
 * @author QUAN
 */
public class BorrowList extends ArrayList<Borrow> implements I_BorrowList {

    public static final String FILENAME = "borrow.dat";

    public BorrowList() {
        loadData(FILENAME);
    }

    //Everytime there's any change available in Asset List, it will store to "fileName" file immediately
    public void storeData(String fileName) {
        File file = new File(fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Borrow borrow : this) {
                oos.writeObject(borrow);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //This method will run only once and immediately at the time user run the project 
    public void loadData(String fileName) {
        ArrayList<Borrow> list = new ArrayList<>();
        File file = new File(fileName);
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (fis.available() > 0) {
                list.add((Borrow) ois.readObject());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        this.clear();
        this.addAll(list);
    }

    //Insert new approved information (For options 5 in Manager Program)
    @Override
    public void createBorrow(Request request) {
        boolean cont = true;
        String newBorrowID;

        LocalDateTime now = LocalDateTime.now();
        Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
        Date borrowDateTime = Date.from(instant);

        do {
            newBorrowID = Utils.getString("Please input new id for Borrow List (This cannot be changed): ");
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i).getbID().equals(newBorrowID)) {
                    System.out.println("Duplicate ID, please try again!");
                    cont = true;
                    break;
                } else {
                    cont = false;
                }
            }
        } while (cont == true);
        Borrow borrow = new Borrow(newBorrowID, request.getAssetID(), request.getEmployeeID(), request.getQuantity(), borrowDateTime);
        this.add(borrow);
        this.storeData(FILENAME);
        System.out.println("New borrow added!");
    }

    //Show all information of Borrow List (For options 6 in Manager Program)
    @Override
    public void showAll() {
        System.out.printf("+------+---------+------------+----------+-------------------+\n");
        System.out.printf("|%-6s|%-9s|%-12s|%-10s|%-19s|\n", "bID", "assetID", "employeeID", "quantity", "borrowDateTime");
        System.out.printf("+------+---------+------------+----------+-------------------+\n");
        for (int i = 0; i < this.size(); i++) {
            this.get(i).showInfo();
        }
        System.out.printf("+------+---------+------------+----------+-------------------+\n");
    }

    //Count how many request this employee have (For Option 4 in Employee Program)
    @Override
    public int countBorrowByEmployee(String employeeID) {
        int count = 0;
        for (Borrow borrow : this) {
            if (borrow.getEmployeeID().equals(employeeID)) {
                count++;
            }
        }
        return count;
    }

    //Show the list of requested assets available of this employee (For Option 4 in Employee Program)
    @Override
    public void showByEmployee(String employeeID) {
        System.out.printf("+------+---------+------------+----------+-------------------+\n");
        System.out.printf("|%-6s|%-9s|%-12s|%-10s|%-19s|\n", "bID", "assetID", "employeeID", "quantity", "borrowDateTime");
        System.out.printf("+------+---------+------------+----------+-------------------+\n");
        for (Borrow borrow : this) {
            if (borrow.getEmployeeID().equals(employeeID)) {
                borrow.showInfo();
            }
        }
        System.out.printf("+------+---------+------------+----------+-------------------+\n");
    }
    
    //Check if Employee input the wrong existed ID, return all information of this ID if input correctly (For Option 5 in Employee Program)  
    @Override
    public Borrow checkExist(String borrowID) {
        Borrow borrow = null;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getbID().equals(borrowID)) {
                borrow = this.get(i);
            }
        }
        return borrow;
    }
    
    //Remove Borrowed asset based on Employee choice (For Option 5 in Employee Program)
    @Override
    public void removeBorrow(Borrow borrow) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).equals(borrow)) {
                this.remove(i);
                this.storeData(FILENAME);
            }
        }
    }
}
