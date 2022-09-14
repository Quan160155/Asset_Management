/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import static dao.LoadStoreFile.loadData;
import static dao.LoadStoreFile.storeData;
import dto.Asset;
import dto.Borrow;
import dto.Employee;
import dto.Manager;
import dto.Person;
import dto.Request;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Load these data to files before run the main program
 *
 * @author QUAN
 */
public class InitializeData implements Serializable{

    public static void main(String[] args) {
        asset();
        person();
        borrow();
        request();
    }

    public static void asset() {
        ArrayList<Asset> list = new ArrayList<>();
        try {
            list.add(new Asset("A001", "Samsung projector", "White", 500, 3.2, 10));
            list.add(new Asset("A002", "Macbook pro 2016", "Silver", 500, 2.2, 5));
            storeData("asset.dat", list);
        } catch (Exception e) {
            System.out.println(e);
        }

        ArrayList<Asset> nlist = loadData("asset.dat");
        System.out.printf("+---------+-------------------------+----------+-------+--------+---------+\n");
        System.out.printf("|%-9s|%-25s|%-10s|%-7s|%-8s|%-9s|\n", "assetID", "name", "color", "price", "weight", "quantity");
        System.out.printf("+---------+-------------------------+----------+-------+--------+---------+\n");
        for (Asset asset : nlist) {
            asset.showInfo();
        }
        System.out.printf("+---------+-------------------------+----------+-------+--------+---------+\n");
    }

    public static void person() {
        ArrayList<Person> list = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            list.add(new Employee("E160001", "Nguyen Hong Hiep", formatter.parse("12/06/2000"), "male", "123456"));
            list.add(new Employee("E160240", "Tran Dinh Khanh", formatter.parse("15/07/2002"), "male", "123456"));
            list.add(new Employee("E140449", "Le Buu Nhan", formatter.parse("10/07/2002"), "male", "123456"));
            list.add(new Employee("E160798", "Truong Le Minh", formatter.parse("03/12/2002"), "male", "123456"));
            list.add(new Manager("E160052", "Hoa Doan", formatter.parse("05/06/1990"), "male", "123456"));
            storeData("employee.dat", list);
        } catch (Exception e) {
            System.out.println(e);
        }

        ArrayList<Person> nlist = loadData("employee.dat");
        System.out.printf("+----------+-------------------------+------------+------+-------+----------+\n");
        System.out.printf("|%-10s|%-25s|%-12s|%-6s|%-7s|%-10s|\n", "employID", "name", "birthDate", "role", "sex", "password");
        System.out.printf("+----------+-------------------------+------------+------+-------+----------+\n");
        for (Person person : nlist) {
            person.showInfo();
        }
        System.out.printf("+----------+-------------------------+------------+------+-------+----------+\n");
    }

    public static void borrow() {
        ArrayList<Borrow> list = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            list.add(new Borrow("B001", "A001", "E160001", 1, formatter.parse("23-12-2021 15:13:46")));
            list.add(new Borrow("B002", "A001", "E160001", 2, formatter.parse("25-12-2021 16:14:56")));
            list.add(new Borrow("B003", "A002", "E160798", 3, formatter.parse("15-12-2021 17:15:52")));
            list.add(new Borrow("B007", "A001", "E160240", 2, formatter.parse("26-12-2021 12:16:53")));
            storeData("borrow.dat", list);
        } catch (Exception e) {
            System.out.println(e);
        }

        ArrayList<Borrow> nlist = loadData("borrow.dat");
        System.out.printf("+------+---------+------------+----------+-------------------+\n");
        System.out.printf("|%-6s|%-9s|%-12s|%-10s|%-19s|\n", "bID", "assetID", "employeeID", "quantity", "borrowDateTime");
        System.out.printf("+------+---------+------------+----------+-------------------+\n");
        for (Borrow borrow : nlist) {
            borrow.showInfo();
        }
        System.out.printf("+------+---------+------------+----------+-------------------+\n");
    }

    public static void request() {
        ArrayList<Request> list = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            list.add(new Request("R001", "A001", "E140449", 1, formatter.parse("23-12-2021 13:17:56")));
            list.add(new Request("R002", "A002", "E160001", 1, formatter.parse("24-12-2021 12:18:56")));
            list.add(new Request("R003", "A001", "E160798", 1, formatter.parse("23-12-2021 11:19:56")));
            list.add(new Request("R007", "A002", "E160240", 1, formatter.parse("24-12-2021 10:10:56")));
            storeData("request.dat", list);
        } catch (Exception e) {
            System.out.println(e);
        }

        ArrayList<Request> nlist = loadData("request.dat");
        System.out.printf("+------+---------+------------+----------+-------------------+\n");
        System.out.printf("|%-6s|%-9s|%-12s|%-10s|%-19s|\n", "rID", "assetID", "employeeID", "quantity", "requestDateTime");
        System.out.printf("+------+---------+------------+----------+-------------------+\n");
        for (Request request : nlist) {
            request.showInfo();
        }
        System.out.printf("+------+---------+------------+----------+-------------------+\n");
    }
}
