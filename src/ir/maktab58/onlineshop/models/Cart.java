package ir.maktab58.onlineshop.models;

import ir.maktab58.onlineshop.models.products.Product;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Taban Soleymani
 */
@Entity
@Data
@NoArgsConstructor
@ToString
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "fk_customer_id")
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "fk_product_id")
    private Product product;
    private int quantity;

    @Builder(setterPrefix = "with")
    public Cart(Customer customer, Product product, int quantity) {
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
    }
}
