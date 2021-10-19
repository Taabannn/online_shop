package ir.maktab58.homework8.dataaccess;

import ir.maktab58.homework8.enumation.MagazineType;
import ir.maktab58.homework8.enumation.ShoeType;
import ir.maktab58.homework8.enumation.ShoeTypesInGeneral;
import ir.maktab58.homework8.models.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Taban Soleymani
 */
public class ProductDataBaseAccess extends DataBaseAccess {
    public ProductDataBaseAccess() {
        super();
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = getAllShoes();
        products.addAll(getAllTelevisions());
        products.addAll(getAllRadios());
        products.addAll(getAllBooks());
        products.addAll(getAllMagazines());
        return products;
    }

    public ArrayList<Product> getAllShoes() {
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from shoes");
                ArrayList<Product> productList = new ArrayList<>();
                while (resultSet.next()) {
                    Shoe shoe = new Shoe(resultSet.getInt(1),
                            resultSet.getString(2), resultSet.getInt(3),
                            resultSet.getInt(4), resultSet.getString(5),
                            resultSet.getInt(6), resultSet.getString(7),
                            ShoeTypesInGeneral.NOT_SET.getVal(resultSet.getString(8)),
                            ShoeType.NOT_SET.getVal(resultSet.getString(9)));
                    productList.add(shoe);
                }
                return productList;
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return null;
    }

    public ArrayList<Product> getAllTelevisions() {
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from television");
                ArrayList<Product> productList = new ArrayList<>();
                while (resultSet.next()) {
                    Television television = new Television(resultSet.getInt(1),
                            resultSet.getString(2), resultSet.getInt(3),
                            resultSet.getInt(4), resultSet.getString(5),
                            resultSet.getString(6), resultSet.getInt(7));
                    productList.add(television);
                }
                return productList;
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return null;
    }

    public ArrayList<Product> getAllRadios() {
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from radio");
                ArrayList<Product> productList = new ArrayList<>();
                while (resultSet.next()) {
                    boolean isPortable = false;
                    if (resultSet.getInt(7) == 1)
                        isPortable = true;
                    Radio radio = new Radio(resultSet.getInt(1),
                            resultSet.getString(2), resultSet.getInt(3),
                            resultSet.getInt(4), resultSet.getString(5),
                            resultSet.getString(6), isPortable);
                    productList.add(radio);
                }
                return productList;
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return null;
    }

    public ArrayList<Product> getAllBooks() {
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from book");
                ArrayList<Product> productList = new ArrayList<>();
                while (resultSet.next()) {
                    Book book = new Book(resultSet.getInt(1),
                            resultSet.getString(2), resultSet.getInt(3),
                            resultSet.getInt(4), resultSet.getString(5),
                            resultSet.getString(6), resultSet.getString(7));
                    productList.add(book);
                }
                return productList;
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return null;
    }

    public ArrayList<Product> getAllMagazines() {
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from magazine");
                ArrayList<Product> productList = new ArrayList<>();
                while (resultSet.next()) {
                    Magazine magazine = new Magazine(resultSet.getInt(1),
                            resultSet.getString(2), resultSet.getInt(3),
                            resultSet.getInt(4), resultSet.getString(5),
                            MagazineType.NOT_SET.getVal(resultSet.getString(6)),
                            resultSet.getString(7));
                    productList.add(magazine);
                }
                return productList;
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return null;
    }

    public int updateCountOfProducts(Product product, int count){
        if (product instanceof Shoe)
            return updateCountOfShoeProduct(product, count);
        if (product instanceof Television)
            return updateCountOfTelevisionProduct(product, count);
        if (product instanceof Radio)
            return updateCountOfRadioProduct(product, count);
        if (product instanceof Book)
            return updateCountOfBookProduct(product, count);
        if (product instanceof Magazine)
            return updateCountOfMagazineProduct(product, count);
        return 0;
    }

    private int updateCountOfMagazineProduct(Product product, int count) {
        if (connection != null) {
            try {
                String sqlQuery = String.format("UPDATE magazine SET count = %d WHERE product_id = %d",
                        count, product.getId());
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

    private int updateCountOfBookProduct(Product product, int count) {
        if (connection != null) {
            try {
                String sqlQuery = String.format("UPDATE book SET count = %d WHERE product_id = %d",
                        count, product.getId());
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

    private int updateCountOfRadioProduct(Product product, int count) {
        if (connection != null) {
        try {
            String sqlQuery = String.format("UPDATE radio SET count = %d WHERE product_id = %d",
                    count, product.getId());
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

    private int updateCountOfTelevisionProduct(Product product, int count) {
        if (connection != null) {
            try {
                String sqlQuery = String.format("UPDATE television SET count = %d WHERE product_id = %d",
                        count, product.getId());
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

    private int updateCountOfShoeProduct(Product product, int count) {
        if (connection != null) {
            try {
                String sqlQuery = String.format("UPDATE shoes SET count = %d WHERE product_id = %d",
                        count, product.getId());
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
}
