/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * An abstract class for Loading and Storing Files
 *
 * @author QUAN
 */
public abstract class LoadStoreFile {

    public static void storeData(String fileName, ArrayList list) {
        File file = new File(fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Object obj : list) {
                oos.writeObject(obj);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static <E> ArrayList<E> loadData(String fileName) {
        ArrayList<E> list = new ArrayList<>();
        File file = new File(fileName);
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (fis.available() > 0) {
                list.add((E) ois.readObject());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
