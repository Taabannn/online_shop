package ir.maktab58.onlineshop.models.products;

import ir.maktab58.onlineshop.models.Cart;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Taban Soleymani
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"productName", "price"})
@ToString(of = {"id", "productName", "price", "count"})
public abstract class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String productName;
    private long price;
    private int count;
    @ManyToMany(mappedBy = "products")
    private List<Cart> cart = new ArrayList<>();

    public Product(String productName, long price, int count) {
        this.productName = productName;
        this.price = price;
        this.count = count;
    }
}
