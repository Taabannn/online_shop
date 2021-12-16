package ir.maktab58.onlineshop.service;

import ir.maktab58.onlineshop.dao.CartDao;
import ir.maktab58.onlineshop.models.Cart;

import java.util.List;

/**
 * @author Taban Soleymani
 */
public class CartService {
    private final CartDao cartDao = new CartDao();

    public int saveCart(Cart cart) {
        return cartDao.save(cart);
    }

    public List<Cart> getCustomerCarts(int customerId) {
        return cartDao.findCartsByCustomerId(customerId);
    }

    public void deleteCart(Cart cart, int customerId) {
        cartDao.deleteCartByCustomerId(cart, customerId);
    }
}
