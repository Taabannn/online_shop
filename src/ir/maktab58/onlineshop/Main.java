package ir.maktab58.onlineshop;

import ir.maktab58.onlineshop.exceptions.OnlineShopExceptions;
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
            System.out.println("""
                    **********Welcome**********
                    1) Signup as a customer (if you have not an account you can create it now)
                    2) Login as a customer (if you've already an account)
                    3) Login as an admin
                    4) Exit""");
            String choice = inputLine.nextLine().trim();
            try {
                switch (choice) {
                    case "1" : onlineShop.registerCustomer();
                    case "2" : onlineShop.loginAsACustomer();
                    case "3" : onlineShop.loginAsAnAdmin();
                    case "4" : break;
                    default: throw OnlineShopExceptions.builder()
                            .message("Invalid input command. Your choice must be an integer between 1 to 4.")
                            .errorCode(400).build();
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
