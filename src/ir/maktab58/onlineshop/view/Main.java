package ir.maktab58.onlineshop.view;

import ir.maktab58.onlineshop.exceptions.OnlineShopExceptions;
import ir.maktab58.onlineshop.service.OnlineShop;

import java.util.Scanner;

/**
 * @author Taban Soleymani
 */
public class Main {
    public static void main(String[] args) {
        OnlineShopSys onlineShopSys = new OnlineShopSys();
        onlineShopSys.showMenu();
    }
}
