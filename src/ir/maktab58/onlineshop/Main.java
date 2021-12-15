package ir.maktab58.onlineshop;

import ir.maktab58.onlineshop.service.OnlineShop;

import java.util.Scanner;

/**
 * @author Taban Soleymani
 */
public class Main {
    public static void main(String[] args) {
        OnlineShop onlineShop = new OnlineShop();
        Scanner inputLine = new Scanner(System.in);
        while (true){
            System.out.println("**********Welcome**********");
            System.out.println("1) Signup as a customer (if you have not an account you can create it now)");
            System.out.println("2) Login as a customer (if you've already an account)");
            System.out.println("3) Login as an admin");
            System.out.println("4) Exit");
            String choice = inputLine.nextLine().trim();
            if (choice.trim().equals("1")) {
                onlineShop.registerCustomer();
            } else if (choice.equals("2")) {
                onlineShop.loginAsACustomer();
            } else if (choice.equals("3")) {
                onlineShop.loginAsAnAdmin();
            } else if (choice.equals("4")) {
                break;
            } else {
                System.out.println("Invalid input command. Your choice must be an integer between 1 to 4.");
            }
        }
    }
}
