/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Person;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import util.Utils;

/**
 * Details of Person List methods implements from I_PersonList
 *
 * @author QUAN
 */
public class PersonList extends ArrayList<Person> implements I_PersonList{

    public static final String FILENAME = "employee.dat";

    public PersonList() {
        loadData(FILENAME);
    }
    
    //Everytime there's any change available in Asset List, it will store to "FILENAME" file immediately
    public void storeData(String fileName) {
        File file = new File(fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Person person : this) {
                oos.writeObject(person);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //This method will run only once and immediately at the time user run the project 
    public final void loadData(String fileName) {
        ArrayList<Person> list = new ArrayList<>();
        File file = new File(fileName);
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (fis.available() > 0) {
                list.add((Person) ois.readObject());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        this.clear();
        this.addAll(list);
    }

    //Return the index found based on the id, if not return -1
    @Override
    public int searchById(String id) {
        int index = -1;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getEmployID().equals(id)) {
                index = i;
            }
        }
        return index;
    }
    
    //This method will run only when the user is not login (Person object in main class is null)
    @Override
    public Person login() {
        String id = Utils.getString("Input ID: ");
        String password = Utils.getString("Input password: ");
        int index = searchById(id);
        if (index != -1) {
            Person person = this.get(index);
            if (person.getPassword().equals(password)) {
                System.out.println("Login sucessful!");
                return person;
            }
        } else {
            System.out.println("Incorrect ID or Password.");
        }
        return null;
    }
}
