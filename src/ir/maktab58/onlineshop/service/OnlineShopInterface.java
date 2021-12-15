package ir.maktab58.onlineshop.service;

import ir.maktab58.onlineshop.models.Cart;

/**
 * @author Taban Soleymani
 */
public interface OnlineShopInterface {
    void addProductToCart(int customerId);

    void deleteProductFromCart(int customerId, Cart cart);

    void printAddedItemsWithPriceToCart(Cart cart, long totalPrice);

    void confirmShopping(Cart cart, long totalPrice, int customerId);

}
