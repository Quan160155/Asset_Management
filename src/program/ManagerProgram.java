/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import dao.AssetList;
import dao.BorrowList;
import dao.RequestList;
import dto.Person;
import dto.Request;
import util.Utils;

/**
 * This is a management room, feature all the options for a Manager
 *
 * @author QUAN
 */
public class ManagerProgram {

    RequestList requestList = new RequestList();
    BorrowList borrowList = new BorrowList();
    AssetList assetList = new AssetList();

    //1 - Login (This method show the user again that the user is already login as a Manager)
    public void login() {
        System.out.println("Already Login");
    }

    //2 - Search Asset by Name (No need login)
    public void searchAssetByName() {
        System.out.print("\n -- Search Asset by Name -- \n");
        assetList.searchAssetByName();
    }

    //3 - Create new Asset
    public void createAsset() {
        System.out.print("\n -- Create new Asset -- \n");
        assetList.createAsset();
    }

    //4 - Update Asset Information
    public void updateAsset() {
        System.out.print("\n -- Update Asset -- \n");
        assetList.updateAsset();
    }

    //5 - Approve the Request of Employee
    public void approveRequest() {
        System.out.print("\n -- Approve Request -- \n");
        if (requestList.isEmpty()) {
            System.out.println("There is no request available.");
            return;
        }

        String rID;
        System.out.println("Here's a list of requests available: ");
        requestList.showAll();
        rID = Utils.getString("Input request ID you choose to approve: ");

        Request request = requestList.checkExist(rID);
        if (request == null) {
            System.out.println("This request ID does not exist.");
        } else if (assetList.quantityCheck(request.getAssetID(), request.getQuantity()) < 0) {
            System.out.println("This Asset is out of stock!");
        } else {
            borrowList.createBorrow(request);
            requestList.removeRequest(request);
            assetList.updateApproveQuantity(request.getAssetID(), request.getQuantity());
        }
    }

    //6 - Show list of borrowList Assets
    public void showBorrowList() {
        System.out.print("\n -- Show Borrow List -- \n");
        if (borrowList.isEmpty()) {
            System.out.println("Borrow List is empty.");
            return;
        }
        System.out.println("Here's the Borrow List:");
        borrowList.showAll();
    }
}
