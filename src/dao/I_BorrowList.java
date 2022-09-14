/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Borrow;
import dto.Request;

/**
 * List of methods for Borrow List 
 * @author QUAN
 */
public interface I_BorrowList {
    //Insert new approved information (For options 5 in Manager Program)
    public void createBorrow(Request request);
    //Show all information of Borrow List (For options 6 in Manager Program)
    public void showAll();
    //Count how many borrowed assets this employee have (For Option 5 in Employee Program)
    public int countBorrowByEmployee(String employeeID);
    //Show the list of borrowed assets available of this employee (For Option 5 in Employee Program)
    public void showByEmployee(String employeeID);
    //Check if Employee input the wrong existed ID, return all information of this ID if input correctly (For Option 5 in Employee Program)  
    public Borrow checkExist(String borrowID);
    //Remove Borrowed asset based on Employee choice (For Option 5 in Employee Program)
    public void removeBorrow(Borrow borrow);
}
