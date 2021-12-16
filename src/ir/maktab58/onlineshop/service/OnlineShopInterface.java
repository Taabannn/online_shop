package ir.maktab58.onlineshop.service;

import ir.maktab58.onlineshop.models.Cart;
import ir.maktab58.onlineshop.models.products.Product;

import java.util.List;

/**
 * @author Taban Soleymani
 */
public interface OnlineShopInterface {
    int addItemToCart(int customerId, int productId, int count);
    void depositCustomerBalance(long charge, int customerId);
    long calculateTotalPrice(List<Cart> customerCarts);
    void deleteAnItemFromCart(List<Cart> customerCarts, int cartId, int customerId);
    void confirmShopping(int customerId);
    List<Product> getNotEnoughProducts(int customerId);
}
