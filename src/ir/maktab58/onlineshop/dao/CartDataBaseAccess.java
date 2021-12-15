package ir.maktab58.onlineshop.dao;

import ir.maktab58.onlineshop.enumation.*;
import ir.maktab58.onlineshop.exceptions.IllegalInput;
import ir.maktab58.onlineshop.models.*;
import ir.maktab58.onlineshop.models.products.*;
import ir.maktab58.onlineshop.models.products.electronicdevices.Radio;
import ir.maktab58.onlineshop.models.products.electronicdevices.Television;
import ir.maktab58.onlineshop.models.products.readingItems.Book;
import ir.maktab58.onlineshop.models.products.readingItems.Magazine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Taban Soleymani
 */
public class CartDataBaseAccess extends DataBaseAccess {
    public CartDataBaseAccess() {
        super();
    }

    public boolean saveCart(Cart cart){
        if (connection != null) {
            try {
                boolean result = false;
                ArrayList<Product> products = cart.getProducts();
                for (Product product : products) {
                    String sqlQuery = String.format("INSERT INTO cart (fk_customer_id, fk_product_id, count, product_type) VALUES (?, ?, ?, ?)");
                    PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
                    pstmt.setInt(2, cart.getCustomer().getId());
                    pstmt.setInt(3, product.getId());
                    pstmt.setInt(4, product.getCount());
                    pstmt.setString(5, getType(product));
                    result = pstmt.execute();
                }
                return !result;
            } catch (SQLException exception) {
                exception.getMessage();
            }
        }
        return false;

    }

    private String getType(Product product){
        if (product instanceof Book)
            return "book";
        if (product instanceof Magazine)
            return "magazine";
        if (product instanceof Radio)
            return "radio";
        if (product instanceof Television)
            return "television";
        if (product instanceof Shoe)
            return "shoe";
        throw new IllegalInput("The type of input is not acceptable", 400);
    }

    public Cart getCart(int customerId){
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from customers where customer_id="+ customerId);
                while (resultSet.next()) {
                    Customer costumer = new Customer(resultSet.getInt(1),
                            resultSet.getString(2), resultSet.getString(3),
                            resultSet.getString(4), resultSet.getInt(5),
                            resultSet.getInt(7), resultSet.getInt(6));
                    Cart cart = new Cart(costumer);
                    ArrayList<Product> products = getProducts(customerId);
                    return cart;
                }
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return null;
    }

    public ArrayList<Product> getProducts(int customerId){
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from cart where fk_customer_id=" + customerId);
                ArrayList<Product> productList = new ArrayList<>();
                while (resultSet.next()) {
                    Product product = findProduct(resultSet.getString(5), resultSet.getInt(3), resultSet.getInt(4));
                    productList.add(product);
                }
                return productList;
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return null;
    }

    private Product findProduct(String type, int productId, int count) {
        Product product;
        if (type.equals(ProductType.SHOE.getType())) {
            product = getShoe(productId);
            product.setCount(count);
            return product;
        }
        else if (type.equals(ElectronicDevicesTypes.RADIO.getType())) {
            product = getRadio(productId);
            product.setCount(count);
            return product;
        }
        else if (type.equals(ElectronicDevicesTypes.TELEVISION.getType())) {
            product = getTelevision(productId);
            product.setCount(count);
            return product;
        }
        else if (type.equals(ReadingItemsTypes.BOOK.getType())) {
            product = getBook(productId);
            product.setCount(count);
            return product;
        }
        else if (type.equals(ReadingItemsTypes.MAGAZINE.getType())) {
            product = getMagazine(productId);
            product.setCount(count);
            return product;
        }
        else
            throw new IllegalInput("The type of product is not valid.", 400);
    }

    private Product getMagazine(int productId) {
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from magazine where product_id" + productId);
                while (resultSet.next()) {
                    Product product = new Magazine(resultSet.getInt(1),
                            resultSet.getString(2), resultSet.getInt(3),
                            resultSet.getInt(4), resultSet.getString(5),
                            MagazineType.NOT_SET.getVal(resultSet.getString(6)), resultSet.getString(7));
                    return product;
                }
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return null;
    }

    private Product getBook(int productId) {
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from book where product_id" + productId);
                while (resultSet.next()) {
                    Product product = new Book(resultSet.getInt(1),
                            resultSet.getString(2), resultSet.getInt(3),
                            resultSet.getInt(4), resultSet.getString(5),
                            resultSet.getString(6), resultSet.getString(7));
                    return product;
                }
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return null;
    }

    private Product getTelevision(int productId) {
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from television where product_id" + productId);
                while (resultSet.next()) {
                    Product product = new Television(resultSet.getInt(1),
                            resultSet.getString(2), resultSet.getInt(3),
                            resultSet.getInt(4), resultSet.getString(5),
                            resultSet.getString(6), resultSet.getInt(7));
                    return product;
                }
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return null;
    }

    private Product getRadio(int productId) {
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from radio where product_id" + productId);
                while (resultSet.next()) {
                    boolean isPortable = false;
                    if (resultSet.getInt(7) == 1)
                        isPortable = true;
                    Product product = new Radio(resultSet.getInt(1),
                            resultSet.getString(2), resultSet.getInt(3),
                            resultSet.getInt(4), resultSet.getString(5),
                            resultSet.getString(6), isPortable);
                    return product;
                }
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return null;
    }

    private Product getShoe(int productId) {
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from shoes where product_id" + productId);
                while (resultSet.next()) {
                    Product product = new Shoe(resultSet.getInt(1),
                            resultSet.getString(2), resultSet.getInt(3),
                            resultSet.getInt(4), resultSet.getString(5),
                            resultSet.getInt(6), resultSet.getString(7),
                            ShoeTypesInGeneral.NOT_SET.getVal(resultSet.getString(8)),
                            ShoeType.NOT_SET.getVal(resultSet.getString(9)));
                    return product;
                }
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return null;
    }

    public void deleteARow(int customerId, int productId){
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate("delete from cart where fk_product_id=" + productId +
                        " and fk_customer_id=" + customerId);
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}


