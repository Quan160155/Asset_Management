/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.AssetList;
import dao.BorrowList;
import dao.PersonList;
import dao.RequestList;
import dto.Person;
import program.EmployeeProgram;
import program.I_Menu;
import program.ManagerProgram;
import program.Menu;

/**
 * User view
 *
 * @author QUAN
 */
public class AssetManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("Loading all the data...");
        AssetList assetList = new AssetList();
        PersonList personList = new PersonList();
        BorrowList borrowList = new BorrowList();
        RequestList requestList = new RequestList();
        System.out.println("Load Complete!");

        Person person = null;
        ManagerProgram manager = new ManagerProgram();
        EmployeeProgram employee = new EmployeeProgram();

        I_Menu menu = new Menu();
        menu.addItem("1 - Login");
        menu.addItem("2 - Search Asset by Name");
        menu.addItem("3 - Quit");

        I_Menu menuManager = new Menu();
        menuManager.addItem("1 - Login");
        menuManager.addItem("2 - Search Asset by Name");
        menuManager.addItem("3 - Create New Asset");
        menuManager.addItem("4 - Update Asset Information");
        menuManager.addItem("5 - Approve the request of employee");
        menuManager.addItem("6 - Show list of borrow asset");
        menuManager.addItem("7 - Quit");

        I_Menu menuEmployee = new Menu();
        menuEmployee.addItem("1 - Login");
        menuEmployee.addItem("2 - Search Asset by Name");
        menuEmployee.addItem("3 - Borrow the Assets");
        menuEmployee.addItem("4 - Cancel request");
        menuEmployee.addItem("5 - Return asset");
        menuEmployee.addItem("6 - Quit");

        int choice;
        int choiceManager;
        int choiceEmployee;
        boolean cont = false;

        do {
            System.out.print("\n   -- BMLT Company -- ");
            System.out.print("\n -- Asset Management -- \n");

            //A menu for user who are not login
            if (person == null) {
                menu.showMenu();
                choice = menu.getChoice();
                switch (choice) {
                    case 1:
                        person = personList.login();
                        break;
                    case 2:
                        assetList.searchAssetByName();
                        break;
                    case 3:
                        cont = menu.confirmYesNo("Do you want to quit (Y/N)?: ");
                        break;
                }
            } //A menu for Manager
            else if (person.getRole().equals("MA")) {
                menuManager.showMenu();
                choiceManager = menuManager.getChoice();
                switch (choiceManager) {
                    case 1:
                        manager.login();
                        break;
                    case 2:
                        manager.searchAssetByName();
                        break;
                    case 3:
                        manager.createAsset();
                        break;
                    case 4:
                        manager.updateAsset();
                        break;
                    case 5:
                        manager.approveRequest();
                        break;
                    case 6:
                        manager.showBorrowList();
                        break;
                    case 7:
                        cont = menu.confirmYesNo("Do you want to quit (Y/N)?: ");
                        break;
                }
            } //A menu for Employee
            else if (person.getRole().equals("EM")) {
                menuEmployee.showMenu();
                choiceEmployee = menuManager.getChoice();
                switch (choiceEmployee) {
                    case 1:
                        employee.login();
                        break;
                    case 2:
                        employee.searchAssetByName();
                        break;
                    case 3:
                        employee.borrowAssets(person);
                        break;
                    case 4:
                        employee.cancelRequest(person);
                        break;
                    case 5:
                        employee.returnRequest(person);
                        break;
                    case 6:
                        cont = menu.confirmYesNo("Do you want to quit (Y/N)?: ");
                        break;
                }
            }
        } while (!cont);
    }
}
