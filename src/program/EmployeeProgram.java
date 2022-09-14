/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import dao.AssetList;
import dao.BorrowList;
import dao.RequestList;
import dto.Borrow;
import dto.Person;
import dto.Request;
import util.Utils;

/**
 * This is a management room, feature all the options for an Employee
 *
 * @author QUAN
 */
public class EmployeeProgram {

    RequestList requestList = new RequestList();
    BorrowList borrowList = new BorrowList();
    AssetList assetList = new AssetList();

    //1 - Login (This method show the user again that the user is already login as an Employee)
    public void login() {
        System.out.println("Already Login");
    }

    //2 - Search Asset by Name (No need login)
    public void searchAssetByName() {
        System.out.print("\n -- Search Asset by Name -- \n");
        assetList.searchAssetByName();
    }

    //3 - Borrow the Assets (Input Asset ID and Quantity Employee want to request)
    public void borrowAssets(Person person) {
        System.out.print("\n -- Borrow Assets -- \n");
        if (assetList.isEmpty()) {
            System.out.println("Asset List is empty.");
            return;
        }

        boolean cont;
        do {
            System.out.println("Available Assets:");
            assetList.showAll();
            String assetID = Utils.getString("Input Asset ID you want to request: ");
            if (assetList.searchById(assetID) == -1) {
                System.out.println("This Asset is not existed.");
            } else {
                int quantity = Utils.getInt("Input the Quantity you want to request: ", 0, 1000);
                requestList.createRequest(person.getEmployID(), assetID, quantity);
            }
            cont = Utils.confirmYesNo("Continue (Y/N)?: ");
        } while (cont);
    }

    //4 - Cancel Request
    public void cancelRequest(Person person) {
        System.out.print("\n -- Cancel Request -- \n");
        boolean cont;
        int count = 0;
        do {
            count = requestList.countRequestByEmployee(person.getEmployID());
            if (count == 0) {
                System.out.println("Your assets requested list is empty.");
                return;
            }

            System.out.println("You have " + count + " assets requested: ");
            requestList.showByEmployee(person.getEmployID());

            String rID = Utils.getString("Input Request ID you want to cancel: ");
            Request request = requestList.checkExist(rID);

            //Prevent 2 things: "No request existed with this ID" and "Accidentally remove others Employee's requests"
            if (request == null || !request.getEmployeeID().equals(person.getEmployID())) {
                System.out.println("No asset requested with this Request ID.");
            } else {
                boolean confirm = Utils.confirmYesNo("Do you want to cancel (Y/N)?: ");
                if (confirm) {
                    requestList.removeRequest(request);
                    System.out.println("Request Deleted Successful.");
                } else {
                    System.out.println("Delete Failed.");
                }
            }
            cont = Utils.confirmYesNo("Continue (Y/N)?: ");
        } while (cont);
    }

    //5 - Return Request when Employee is done
    public void returnRequest(Person person) {
        System.out.print("\n -- Return Request -- \n");
        boolean cont;
        int count = 0;
        do {
            count = borrowList.countBorrowByEmployee(person.getEmployID());
            if (count == 0) {
                System.out.println("You don't have any borrowed assets.");
                return;
            }

            System.out.println("You have borrowed " + count + " assets: ");
            borrowList.showByEmployee(person.getEmployID());

            String bID = Utils.getString("Input Borrow ID you want to return: ");
            Borrow borrow = borrowList.checkExist(bID);

            //Prevent 2 things: "No borrowed asset existed with this ID" and "Accidentally remove others Employee's borrowed assets"
            if (borrow == null || !borrow.getEmployeeID().equals(person.getEmployID())) {
                System.out.println("No asset you have borrowed existed with this Borrow ID.");
            } else {
                boolean confirm = Utils.confirmYesNo("Do you want to return (Y/N)?: ");
                if (confirm) {
                    borrowList.removeBorrow(borrow);
                    assetList.updateReturnQuantity(borrow.getAssetID(), borrow.getQuantity());
                    System.out.println("Return Successful.");
                } else {
                    System.out.println("Return Canceled.");
                }
            }
            cont = Utils.confirmYesNo("Continue (Y/N)?: ");
        } while (cont);
    }
}
