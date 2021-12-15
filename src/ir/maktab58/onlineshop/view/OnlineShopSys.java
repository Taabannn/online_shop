package ir.maktab58.onlineshop.view;

import ir.maktab58.onlineshop.enumation.ReadingItemsTypes;
import ir.maktab58.onlineshop.exceptions.OnlineShopExceptions;
import ir.maktab58.onlineshop.models.products.Product;
import ir.maktab58.onlineshop.models.products.readingItems.Book;
import ir.maktab58.onlineshop.models.products.readingItems.Magazine;
import ir.maktab58.onlineshop.models.products.readingItems.ReadingItems;
import ir.maktab58.onlineshop.service.OnlineShopService;

import java.util.List;
import java.util.Scanner;

/**
 * @author Taban Soleymani
 */
public class OnlineShopSys {
    private final OnlineShopService onlineShop = new OnlineShopService();
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
        System.out.println("Enter amount of charge: ");
        long charge = Long.parseLong(scanner.nextLine().trim());
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
                    case "1" -> showElectronicDevices(customerId);
                    case "2" -> showShoes(customerId);
                    case "3" -> showReadingItems(customerId);
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

    private void showReadingItems(int customerId) {
        System.out.println("Which one would you like to see? Book/Magazine");
        String typeOfReading = scanner.nextLine().trim();
        List<Product> readingItems = onlineShop.getReadingItems(typeOfReading);
        readingItems.forEach(System.out::println);
        addSpecificItemToCart(customerId);
    }

    private void showShoes(int customerId) {
        List<Product> shoes = onlineShop.getShoes();
        shoes.forEach(System.out::println);
        addSpecificItemToCart(customerId);
    }

    private void showElectronicDevices(int customerId) {
        System.out.println("Which one would you like to see? Television/Radio");
        String typeOfDevice = scanner.nextLine().trim();
        List<Product> electronicDevices = onlineShop.getElectronicDevices(typeOfDevice);
        electronicDevices.forEach(System.out::println);
        addSpecificItemToCart(customerId);
    }

    private void addSpecificItemToCart(int customerId) {
        System.out.println("Enter id of Item that you want add it to your cart:\n" +
                "If you are not interested with printed items please enter 0 to back product-type menu.");
        int productId = Integer.parseInt(scanner.nextLine().trim());
        if (productId == 0)
            return;
        else
            System.out.println("Enter count of Items that you want add it to your cart.");
        int count = Integer.parseInt(scanner.nextLine().trim());;
        int result = onlineShop.addItemToCart(customerId, productId, count);
        if (result != 0)
            System.out.println("This item has added successfully to your card.");
        else
            System.out.println("Something during adding item to your cart went wrong. Please try again");
    }
}
