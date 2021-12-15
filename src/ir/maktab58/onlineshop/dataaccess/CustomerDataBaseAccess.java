package ir.maktab58.onlineshop.dataaccess;

import ir.maktab58.onlineshop.models.Customer;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Taban Soleymani
 */
public class CustomerDataBaseAccess extends DataBaseAccess {
    public CustomerDataBaseAccess() {
        super();
    }

    public int updateCustomerBalance(int customerId, long balance) {
        if (connection != null) {
            try {
                String sqlQuery = String.format("UPDATE customers SET initial_balance = %d WHERE customer_id = %d",
                        balance, customerId);
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                int result = preparedStatement.executeUpdate(sqlQuery);
                return result;
            }
            catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        return 0;
    }

    public ArrayList<Customer> getAllCustomers() {
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from customers");
                ArrayList<Customer> costumerArrayList = new ArrayList<>();
                while (resultSet.next()) {
                    Customer costumer = new Customer(resultSet.getInt(1),
                            resultSet.getString(2), resultSet.getString(3),
                            resultSet.getString(4), resultSet.getInt(5),
                            resultSet.getInt(7), resultSet.getInt(6));
                    costumerArrayList.add(costumer);
                }
                return costumerArrayList;
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return null;
    }

    public boolean saveCustomer(Customer costumer) {
        if (connection != null) {
            try {
                String sqlQuery = String.format("INSERT INTO customers (customer_id, full_name, username, password, national_code, birth_year, initial_balance) VALUES (?, ?, ?, ?, ?, ?, ?)");
                PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
                pstmt.setInt(1, costumer.getId());
                pstmt.setString(2, costumer.getFullName());
                pstmt.setString(3, costumer.getUsername());
                pstmt.setString(4, costumer.getPassword());
                pstmt.setInt(5, (int) costumer.getNationalCode());
                pstmt.setInt(6, costumer.getBirthYear());
                pstmt.setInt(7, costumer.getInitialBalance());
                boolean result = pstmt.execute();
                return !result;
            } catch (SQLException exception) {
                exception.getMessage();
            }
        }
        return false;
    }
}
