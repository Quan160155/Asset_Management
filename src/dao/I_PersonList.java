/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Person;

/**
 * List of methods for Person List 
 * @author QUAN
 */
public interface I_PersonList {
    //Return the index found based on the id, if not return -1
    public int searchById(String id);
    
    //This method will run only when the user is not login (Person object in main class is null)
    public Person login();
}
