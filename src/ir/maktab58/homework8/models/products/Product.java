package ir.maktab58.homework8.models.products;

import ir.maktab58.homework8.models.Comment;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Taban Soleymani
 */
@Entity
@DiscriminatorColumn(name = "product_type",
        discriminatorType = DiscriminatorType.STRING)
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
    @OneToMany(mappedBy = "product")
    private List<Comment> commentList = new ArrayList<>();

    public Product(int id, String productName, long price, int count) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.count = count;
    }
}
