package ir.maktab58.homework8.models;

import java.util.ArrayList;

/**
 * @author Taban Soleymani
 */
public class Cart {
    private ArrayList<Product> products;
    private Costumer customer;

    public Cart(Costumer customer) {
        this.customer = customer;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void addNewProduct(Product newProduct){
        this.products.add(newProduct);
    }

    public Costumer getCustomer() {
        return customer;
    }

    public void setCustomer(Costumer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "products=" + products +
                ", customer=" + customer +
                '}';
    }
}
