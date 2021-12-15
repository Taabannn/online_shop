package ir.maktab58.onlineshop.service;

import ir.maktab58.onlineshop.dao.CustomerDao;
import ir.maktab58.onlineshop.models.Customer;

import java.util.ArrayList;

/**
 * @author Taban Soleymani
 */
public class CustomerService {
    private final CustomerDao customerDao = new CustomerDao();

    public ArrayList<Customer> getAllCustomers() {
        return null;
    }
}
