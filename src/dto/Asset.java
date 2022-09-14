/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import util.Utils;

/**
 * Properties of a Asset in Asset List
 * @author QUAN
 */
public class Asset implements Serializable {

    //Properties
    private String assetID;
    private String name;
    private String color;
    private int price;
    private double weight;
    private int quantity;

    //Constructor
    public Asset() {
    }

    public Asset(String assetID) {
        this.assetID = assetID;
    }

    public Asset(String assetID, String name, String color, int price, double weight, int quantity) {
        this.assetID = assetID;
        this.name = name;
        this.color = color;
        this.price = price;
        this.weight = weight;
        this.quantity = quantity;
    }

    //Getter, Setter
    //ID cannot be changed (No set)
    public String getAssetID() {
        return assetID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //Methods based around the asset properties
    public void getInfo() {
        name = Utils.getString("Asset Name: ");
        color = Utils.getString("Asset Color: ");
        price = Utils.getInt("Asset Price: ", 0, 100000);
        weight = Utils.getDouble("Asset Weight: ", 0, 10);
        quantity = Utils.getInt("Asset Quantity: ", 0, 1000);
    }

    public void updateInfo() {
        name = Utils.updateString("Update name: ", name);
        color = Utils.updateString("Update color: ", color);
        price = Utils.updateInt("Update price: ", 0, 100000, price);
        weight = Utils.updateDouble("Update weight: ", 0, 10, weight);
        quantity = Utils.updateInt("Update quantity: ", 0, 1000, quantity);
    }

    //Decrase quantity by the number of requested Quantity if Manager approve the request (For option 5 in Manager Program)
    public void updateApproveQuantity(int requestQuantity) {
        quantity = quantity - requestQuantity;
    }
    
    //Incrase quantity by the number of borrowed Quantity return by Employee (For option 5 in Employee Program)
    public void updateReturnQuantity(int borrowQuantity) {
        quantity = quantity + borrowQuantity;
    }

    public void showInfo() {
        System.out.printf("|%-9s|%-25s|%-10s|%-7d|%-8.2f|%-9s|\n", getAssetID(), getName(), getColor(), getPrice(), getWeight(), getQuantity());
    }
}
