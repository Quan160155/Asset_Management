/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 * List of methods for Asset List
 *
 * @author QUAN
 */
public interface I_AssetList {

    //Show info of one asset have this id
    public void showOne(String id);

    //Return the ID if found otherwise return -1, support create
    public int searchById(String id);

    //Return the list of Assets contains name inputted by user if found (For option 2 in Manager and Empoyee programs) 
    public void searchAssetByName();

    //Create a new Asset, then add to the list and store to "asset.dat" (For option 3 in Manager program) 
    public void createAsset();

    //Update available Asset, then store to "asset.dat" (For option 4 in Manager program) 
    public void updateAsset();

    //Check if Quantity of this assetID is out of stock (For Option 5 in Manager Program)
    public int quantityCheck(String assetID, int requestQuantity);

    //Update Quantity after approve successful (For Option 5 in Manager Program)
    public void updateApproveQuantity(String assetID, int requestQuantity);

    //Show Asset List
    public void showAll();
    
    //Update Quantity after Employee return borrowed asset successful (For Option 5 in Employee Program)
    public void updateReturnQuantity(String assetID, int borrowQuantity);
}
