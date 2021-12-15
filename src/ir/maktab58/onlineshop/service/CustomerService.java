package ir.maktab58.onlineshop.service;

import ir.maktab58.onlineshop.dao.CustomerDao;
import ir.maktab58.onlineshop.models.Customer;

import java.util.List;

/**
 * @author Taban Soleymani
 */
public class CustomerService {
    private final CustomerDao customerDao = new CustomerDao();

    public List<Customer> getListOfExistedCustomersByThisUsername(String username) {
        return customerDao.findCustomerByUser(username);
    }

    public List<Customer> getListOfExistedCustomersByThisNationalCode(String nationalCode) {
        return customerDao.findCustomerByNationalCode(nationalCode);
    }

    public int saveNewCustomer(Customer newCustomer) {
        return customerDao.save(newCustomer);
    }

    public Customer getCustomerByUserAndPass(String username, String password) {
        return customerDao.findCustomerByUserAndPass(username, password);
    }

    public Customer getCustomerById(int customerId) {
        return customerDao.findCustomerById(customerId);
    }
}
