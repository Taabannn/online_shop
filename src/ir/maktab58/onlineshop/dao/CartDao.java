package ir.maktab58.onlineshop.dao;

import ir.maktab58.onlineshop.utils.SessionUtil;
import ir.maktab58.onlineshop.models.Cart;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author Taban Soleymani
 */
public class CartDao extends BaseDaoInterfaceImpl<Cart> {
    public List<Cart> findCartsByCustomerId(int customerId) {
        List<Cart> carts;
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query<Cart> query = session.createQuery("FROM Cart cart where cart.customer.id=:customerId", Cart.class);
        query.setParameter("customerId", customerId);
        carts = query.getResultList();
        transaction.commit();
        session.close();
        return carts;
    }

    public void deleteCartByCustomerId(Cart cart, int customerId) {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete from Cart cart WHERE cart.id=:cartId AND cart.customer.id=:customerId");
        query.setParameter("cartId", cart.getId());
        query.setParameter("customerId", customerId);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
