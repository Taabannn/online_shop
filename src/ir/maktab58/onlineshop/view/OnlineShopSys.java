package ir.maktab58.onlineshop.view;

import ir.maktab58.onlineshop.exceptions.OnlineShopExceptions;
import ir.maktab58.onlineshop.service.OnlineShop;

import java.util.Scanner;

/**
 * @author Taban Soleymani
 */
public class OnlineShopSys {
    private final OnlineShop onlineShop = new OnlineShop();
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        boolean exit = false;
        while (!exit){
            System.out.println("""
                    **********Welcome**********
                    1) Signup as a customer (if you have not an account you can create it now)
                    2) Login as a customer (if you've already an account)
                    3) Exit""");
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> getUserInfoForRegister();
                    case "2" -> getUserInfoForLogin();
                    case "3" -> exit = true;
                    default -> throw OnlineShopExceptions.builder()
                            .message("Invalid input command. Your choice must be an integer between 1 to 3.")
                            .errorCode(400).build();
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void getUserInfoForLogin() {
        System.out.println("Please enter your username.");
        String username = scanner.nextLine().trim();
        System.out.println("Please enter your password.");
        String password = scanner.nextLine().trim();
        int customerId = onlineShop.loginAsACustomer(username, password);
        if (customerId != 0)
            System.out.println("You've logged in successfully.");
        else
            throw OnlineShopExceptions.builder()
                    .message("Wrong username or pass.\nPlease try again.")
                    .errorCode(400).build();
        showCustomerMenu(customerId);
    }

    private void getUserInfoForRegister() {
        System.out.println("Please enter your name, family, username, password, initial balance, nationalCode and birthYear.");
        String inputLine = scanner.nextLine().trim();
        int customerId = onlineShop.registerCustomer(inputLine);
        if (customerId != 0)
            System.out.println("You've registered successfully.");
        else
            throw OnlineShopExceptions.builder()
                    .message("Something went wrong during your register time.\nPlease try again.")
                    .errorCode(500).build();
        showCustomerMenu(customerId);
    }

    private void showCustomerMenu(int customerId) {
        boolean exit = false;
        while (!exit) {
            System.out.println("""
                    **********Customer Menu**********
                    1) Add product to cart
                    2) Delete product from cart
                    3) Print added items to cart
                    4) Confirm shopping
                    5) Deposit your account
                    6) Back to main menu""");
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> addProductToCart(customerId);
                    case "2" -> deleteProductFromCart(customerId);
                    case "3" -> printAddedItemsToCart(customerId);
                    case "4" -> confirmShopping(customerId);
                    case "5" -> depositYourAccount(customerId);
                    case "6" -> exit = true;
                    default -> throw OnlineShopExceptions.builder()
                            .message("Invalid input command. Your choice must be an integer between 1 to 6.")
                            .errorCode(400).build();
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void depositYourAccount(int customerId) {

    }

    private void confirmShopping(int customerId) {
    }

    private void printAddedItemsToCart(int customerId) {
    }

    private void deleteProductFromCart(int customerId) {
    }

    private void addProductToCart(int customerId) {
        boolean exit = false;
        while (!exit) {
            System.out.println("""
                    There are three types of products:
                    1) Electronic Devices
                    2) Shoes
                    3) Reading Items
                    4) Back to main menu""");
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> addProductToCart(customerId);
                    case "2" -> deleteProductFromCart(customerId);
                    case "3" -> printAddedItemsToCart(customerId);
                    case "4" -> exit = true;
                    default -> throw OnlineShopExceptions.builder()
                            .message("Invalid input command. Your choice must be an integer between 1 to 4.")
                            .errorCode(400).build();
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /*private void showCustomerMenu(int customerId) {
        while (true) {
            System.out.println("""
                    **********Customer Menu**********
                    1) Add product to cart.
                    2) Delete product from cart.
                    3) Print added items to cart.
                    4) Confirm shopping.
                    5) Deposit your account.
                    6) Back to main menu""");
            String choice = scanner.nextLine().trim();
            if (choice.equals("1")) {
                addProductToCart(customerId);
            } else if (choice.equals("2")) {
                deleteProductFromCart(customerId, cart);
            } else if (choice.equals("3")) {
                printAddedItemsWithPriceToCart(cart, totalPrice);
            } else if (choice.equals("4")) {
                confirmShopping(cart, totalPrice, customerId);
            } else if (choice.equals("5")) {
                depositCustomerBalance(customerId);
            } else if (choice.equals("6")) {
                break;
            } else {
                System.out.println("Invalid input command. Your choice must be an integer between 1 to 6.");
            }
        }
    }*/
}
