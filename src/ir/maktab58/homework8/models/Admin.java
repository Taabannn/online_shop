package ir.maktab58.homework8.models;

import java.util.Scanner;

/**
 * @author Taban Soleymani
 */
public class Admin {
    private final String username = "admin";
    private final String password = "admin";

    public boolean isUserAdmin(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your username.");
        String username = scanner.nextLine();
        System.out.println("Please enter your password.");
        String password = scanner.nextLine();
        return this.username.equals(username) && this.password.equals(password);
    }
}
