/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
* This is comment, do not delete 2021.11.30
 * and open the template in the editor.
 */
package util;

import java.util.Scanner;

/**
 * Reusable methods in the whole project to reduce time and easier to read code
 *
 * @author QUAN
 */
public class Utils {

    public static String getString(String welcome) {
        boolean check = true;
        String result = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println("Input text!!!");
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static String updateString(String welcome, String oldData) {
        String result = oldData;
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            result = tmp;
        }
        return result;
    }

    public static int getInt(String welcome, int min, int max) {
        boolean check = true;
        int number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                if (number > max || number < min) {
                    System.out.println("Only number from " + min + " to " + max);
                } else {
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check);
        return number;
    }

    public static int updateInt(String welcome, int min, int max, int oldData) {
        boolean check = true;
        int number = oldData;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Integer.parseInt(tmp);
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static float getFloat(String welcome, float min, float max) {
        boolean check = true;
        float number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                number = Float.parseFloat(sc.nextLine());
                if (number > max || number < min) {
                    System.out.println("Only number from " + min + " to " + max);
                } else {
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check);
        return number;
    }

    public static float updateFloat(String welcome, float min, float max, float oldData) {
        boolean check = true;
        float number = oldData;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Float.parseFloat(tmp);
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }
    
    public static double getDouble(String welcome, double min, double max) {
        boolean check = true;
        double number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                number = Double.parseDouble(sc.nextLine());
                if (number > max || number < min) {
                    System.out.println("Only number from " + min + " to " + max);
                } else {
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check);
        return number;
    }

    public static double updateDouble(String welcome, double min, double max, double oldData) {
        boolean check = true;
        double number = oldData;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Double.parseDouble(tmp);
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static boolean confirmYesNo(String welcome) {
        boolean result = false;
        boolean cont;
        do {
            cont = false;
            String confirm = Utils.getString(welcome);
            if ("Y".equalsIgnoreCase(confirm)) {
                result = true;
            } else if ("Y".compareToIgnoreCase(confirm) != 0 && "N".compareToIgnoreCase(confirm) != 0) {
                System.out.println("Invalid Input!!!");
                cont = true;
            }
        } while (cont);
        return result;
    }

    public static boolean updateConfirmYesNo(String welcome, boolean oldData) {
        boolean result = false;
        boolean cont;
        do {
            cont = false;
            String confirm = Utils.updateString(welcome, oldData ? "Y" : "N");
            if ("Y".equalsIgnoreCase(confirm)) {
                result = true;
            } else if ("Y".compareToIgnoreCase(confirm) != 0 && "N".compareToIgnoreCase(confirm) != 0) {
                System.out.println("Invalid Input!!!");
                cont = true;
            }
        } while (cont);
        return result;
    }
}
