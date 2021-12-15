package ir.maktab58.homework8.models.products;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

/**
 * @author Taban Soleymani
 */
@Entity
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

    public Product(int id, String productName, long price, int count) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.count = count;
    }
}
