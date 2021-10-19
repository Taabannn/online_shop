package ir.maktab58.homework8.service;

import ir.maktab58.homework8.models.Cart;

/**
 * @author Taban Soleymani
 */
public interface OnlineShopInterface {
    void addProductToCart(int customerId);

    void deleteProductFromCart(int customerId, Cart cart);

    void printAddedItemsWithPriceToCart(Cart cart, long totalPrice);

    void confirmShopping(Cart cart, long totalPrice, int customerId);

}
