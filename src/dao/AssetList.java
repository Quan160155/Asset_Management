/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Asset;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import util.Utils;

/**
 *
 * Details of Asset List methods implements from I_AssetList
 *
 * @author QUAN
 */
public class AssetList extends ArrayList<Asset> implements I_AssetList {

    public static final String FILENAME = "asset.dat";

    public AssetList() {
        loadData(FILENAME);
    }

    //Everytime there's any change available in Asset List, it will store to "FILENAME" file immediately    
    public void storeData(String fileName) {
        File file = new File(fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Asset asset : this) {
                oos.writeObject(asset);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //This method will run only once and immediately at the time user run the project 
    public final void loadData(String fileName) {
        ArrayList<Asset> list = new ArrayList<>();
        File file = new File(fileName);
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (fis.available() > 0) {
                list.add((Asset) ois.readObject());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        this.clear();
        this.addAll(list);
    }

    //Show information of one asset that have this ID, support update
    @Override
    public void showOne(String id) {
        int index = searchById(id);
        System.out.printf("+---------+-------------------------+----------+-------+--------+---------+\n");
        System.out.printf("|%-9s|%-25s|%-10s|%-7s|%-8s|%-9s|\n", "assetID", "name", "color", "price", "weight", "quantity");
        System.out.printf("+---------+-------------------------+----------+-------+--------+---------+\n");
        this.get(index).showInfo();
        System.out.printf("+---------+-------------------------+----------+-------+--------+---------+\n");
        System.out.println();
    }

    //Return the ID if found otherwise return -1, support create
    @Override
    public int searchById(String id) {
        int index = -1;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getAssetID().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }

    //Return the list of Assets contains name inputted by user if found (For option 2 in Manager and Empoyee programs) 
    @Override
    public void searchAssetByName() {
        if (this.isEmpty()) {
            System.out.println("Asset list is empty.");
        } else {
            String name;
            ArrayList<Asset> subList = new ArrayList<>();
            name = Utils.getString("Input Name to search: ");

            for (Asset asset : this) {
                if (asset.getName().contains(name)) {
                    subList.add(asset);
                }
            }
            if (!subList.isEmpty()) {
                Collections.sort(subList, new Comparator<Asset>() {
                    @Override
                    public int compare(Asset o1, Asset o2) {
                        return o2.getName().compareTo(o1.getName());
                    }
                });
                System.out.printf("+---------+-------------------------+----------+-------+--------+---------+\n");
                System.out.printf("|%-9s|%-25s|%-10s|%-7s|%-8s|%-9s|\n", "assetID", "name", "color", "price", "weight", "quantity");
                System.out.printf("+---------+-------------------------+----------+-------+--------+---------+\n");
                for (int i = 0; i < subList.size(); i++) {
                    subList.get(i).showInfo();
                }
                System.out.printf("+---------+-------------------------+----------+-------+--------+---------+\n");
            } else {
                System.out.println("No asset with this name found.");
            }
        }
    }

    //Create a new Asset, then add to the list and store to "asset.dat" (For option 3 in Manager program) 
    @Override
    public void createAsset() {
        boolean cont;
        do {
            String assetID;
            assetID = Utils.getString("Please input new asset id (This cannot be changed): ");

            if (searchById(assetID) != -1) {
                System.out.println("Duplicate ID");
            } else {
                Asset asset = new Asset(assetID);
                asset.getInfo();
                this.add(asset);
                System.out.println("Asset Added!");
                storeData(FILENAME);
            }
            cont = Utils.confirmYesNo("Add more asset (Y/N)?: ");
        } while (cont);
    }

    //Update available Asset, then store to "asset.dat" (For option 4 in Manager program) 
    @Override
    public void updateAsset() {
        if (this.isEmpty()) {
            System.out.println("Asset list is empty.");
        } else {
            String assetID = Utils.getString("Input Asset ID: ");
            int index = searchById(assetID);
            if (index != -1) {
                this.get(index).updateInfo();
                System.out.println("Result: ");
                showOne(assetID);
                this.storeData(FILENAME);
            } else {
                System.out.println("Asset does not exist.");
            }
        }
    }

    //Check if Quantity of this assetID is out of stock (For Option 5 in Manager Program)
    @Override
    public int quantityCheck(String assetID, int requestQuantity) {
        int quantity = 0;
        for (Asset asset : this) {
            if (asset.getAssetID().equals(assetID)) {
                quantity = asset.getQuantity() - requestQuantity;
            }
        }
        return quantity;
    }

    //Update Quantity after Manager approve successful (For Option 5 in Manager Program)
    @Override
    public void updateApproveQuantity(String assetID, int requestQuantity) {
        for (Asset asset : this) {
            if (asset.getAssetID().equals(assetID)) {
                asset.updateApproveQuantity(requestQuantity);
            }
        }
        this.storeData(FILENAME);
    }

    //Show the Asset List (For Option 3 in Employee Program)
    @Override
    public void showAll() {
        System.out.printf("+---------+-------------------------+----------+-------+--------+---------+\n");
        System.out.printf("|%-9s|%-25s|%-10s|%-7s|%-8s|%-9s|\n", "assetID", "name", "color", "price", "weight", "quantity");
        System.out.printf("+---------+-------------------------+----------+-------+--------+---------+\n");
        for (int i = 0; i < this.size(); i++) {
            this.get(i).showInfo();
        }
        System.out.printf("+---------+-------------------------+----------+-------+--------+---------+\n");
    }
    
    //Update Quantity after Employee return borrowed asset successful (For Option 5 in Employee Program)
    @Override
    public void updateReturnQuantity(String assetID, int borrowQuantity) {
        for (Asset asset : this) {
            if (asset.getAssetID().equals(assetID)) {
                asset.updateReturnQuantity(borrowQuantity);
            }
        }
        this.storeData(FILENAME);
    }
}
