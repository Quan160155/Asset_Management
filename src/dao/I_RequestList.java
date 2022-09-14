/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Request;

/**
 * List of methods for Request List 
 * @author QUAN
 */
public interface I_RequestList {
    //Show all the information of available request (For Option 5 in Manager Program)
    public void showAll();
    //Check if Manager or Employee input the wrong existed ID, return all information of this ID if input correctly 
    //For Option 5 in Manager Program and Option 4 in Employee Program 
    public Request checkExist(String requestID);
    //Remove the request based on the rID the Manager input and store the new data to File (For Option 5 in Manager Program)
    public void removeRequest(Request request);
    //Add new request to the Request List (For Option 3 in Employee Program)
    public void createRequest(String employeeID, String assetID, int quantity);
    //Count how many request this employee have (For Option 4 in Employee Program)
    public int countRequestByEmployee(String employeeID);
    //Show the list of requested assets available of this employee (For Option 4 in Employee Program)
    public void showByEmployee(String employeeID);
}
