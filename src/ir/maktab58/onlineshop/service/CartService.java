package ir.maktab58.onlineshop.service;

import ir.maktab58.onlineshop.dao.CartDao;
import ir.maktab58.onlineshop.models.Cart;

/**
 * @author Taban Soleymani
 */
public class CartService {
    private final CartDao cartDao = new CartDao();

    public int saveCart(Cart cart) {
        return cartDao.save(cart);
    }
}
