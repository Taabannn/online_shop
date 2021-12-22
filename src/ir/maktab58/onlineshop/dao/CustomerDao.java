package ir.maktab58.onlineshop.dao;

import ir.maktab58.onlineshop.utils.SessionUtil;
import ir.maktab58.onlineshop.models.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * @author Taban Soleymani
 */
public class CustomerDao extends BaseDaoInterfaceImpl<Customer> {

    public List<Customer> findCustomerByUser(String username) {
        List<Customer> customers;
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query<Customer> query = session.createQuery("FROM Customer customer WHERE customer.username=:username", Customer.class);
        query.setParameter("username", username);
        customers = query.getResultList();
        transaction.commit();
        session.close();
        return customers;
    }

    public List<Customer> findCustomerByNationalCode(String nationalCode) {
        List<Customer> customers;
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query<Customer> query = session.createQuery("FROM Customer customer WHERE customer.nationalCode=:nationalCode", Customer.class);
        query.setParameter("nationalCode", nationalCode);
        customers = query.getResultList();
        transaction.commit();
        session.close();
        return customers;
    }

    public Customer findCustomerByUserAndPass(String username, String password) {
        Customer customer;
        try {
            Session session = SessionUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Query<Customer> query = session.createQuery("FROM Customer customer WHERE customer.username=:username AND customer.password=: password", Customer.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            customer = query.getSingleResult();
            transaction.commit();
            session.close();
        } catch (NoResultException e) {
            customer = null;
        }
        return customer;
    }

    public Customer findCustomerById(int customerId) {
        Customer customer;
        try {
            Session session = SessionUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Query<Customer> query = session.createQuery("FROM Customer customer WHERE customer.id=:customerId", Customer.class);
            query.setParameter("customerId", customerId);
            customer = query.getSingleResult();
            transaction.commit();
            session.close();
        } catch (NoResultException e) {
            customer = null;
        }
        return customer;
    }
}
