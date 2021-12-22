package ir.maktab58.onlineshop.dao;

import ir.maktab58.onlineshop.utils.SessionUtil;
import ir.maktab58.onlineshop.models.products.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author Taban Soleymani
 */
public class ProductDao<T extends Product> extends BaseDaoImpl<Product> {
    public <E> List<Product> getAllProductsFromThisType(Class<E> cl) {
        List<Product> products;
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query<Product> query = session.createQuery("FROM " + cl.getSimpleName(), Product.class);
        products = query.getResultList();
        transaction.commit();
        session.close();
        return products;
    }
}
