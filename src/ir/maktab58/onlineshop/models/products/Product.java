package ir.maktab58.onlineshop.models.products;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Taban Soleymani
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"productName", "price"})
@ToString
public abstract class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String productName;
    private long price;
    private int count;

    public Product(String productName, long price, int count) {
        this.productName = productName;
        this.price = price;
        this.count = count;
    }
}
