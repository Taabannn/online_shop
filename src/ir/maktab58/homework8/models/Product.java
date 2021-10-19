package ir.maktab58.homework8.models;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Taban Soleymani
 */
public class Product {
    private int id;
    private String productName;
    private long price;
    private int count;

    public Product(int id, String productName, long price, int count) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && productName.equals(product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
